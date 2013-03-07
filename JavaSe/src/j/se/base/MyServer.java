package j.se.base;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class MyServer extends JFrame {
	JTextArea txtArea;
	JTextField txtMsg;
	JButton btn1, btn2;
	Socket s;
	Container cp = getContentPane();
	PrintWriter writer;
	BufferedReader read;
	Thread thread1;
	boolean done = false;

	public static final int PORT = 9000;

	public MyServer() {
		try {
			thread1 = new MyThread();
			thread1.start();
			jbInit();
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			ServerSocket myServer = new ServerSocket(PORT);
			s = myServer.accept();
			txtArea.append("�Ѿ����ӵ��ͻ���" + s.toString() + ",\n�˿�Ϊ:" + PORT + "\n");
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						String msg = txtMsg.getText();
						writer.println(msg);
						writer.flush();
						txtArea.append("MyServer ����: " + msg + "\n");
						txtMsg.setText("");
					} catch (Exception e2) {
						System.err.println(e2.getMessage());
					}
				}
			});

		} catch (Exception e4) {
			System.err.println(e4.toString());
		}
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event2) {
				//JOptionPane.showConfirmDialog(this,message, title, optionType)
				try {
					setVisible(false);
					done = true;
					writer.close();
					read.close();
					System.exit(0);
					thread1.destroy();
				} catch (Exception e5) {
					System.err.println(e5.getMessage());
				}
			}
		});
	}

	public void jbInit() {
		setTitle("������");
		setSize(400, 200);
		JPanel p = new JPanel();
		txtArea = new JTextArea(20, 50);
		txtArea.append("�������Ѿ�����\n");
		JScrollPane jsp = new JScrollPane(txtArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		p.add(jsp);
		cp.add(p, BorderLayout.CENTER);

		JLabel la = new JLabel("��Ϣ");
		txtMsg = new JTextField(20);
		btn1 = new JButton("����");
		btn2 = new JButton("�ر�");
		p.setLayout(new FlowLayout());
		p.add(la);
		p.add(txtMsg);
		p.add(btn1);
		p.add(btn2);
		cp.add(p, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event3) {
				try {
					setVisible(false);
					done = true;
					writer.close();
					read.close();
					System.exit(0);
					thread1.destroy();
				} catch (Exception e5) {
					System.err.println(e5.getMessage());
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception eve) {

		}
		new MyServer();
	}

	class MyThread extends Thread {
		/*	public MyThread()
			{
				try{
					read=new BufferedReader(new InputStreamReader(s.getInputStream()));
				}catch(IOException io2)
				{
					System.err.println("IOException"+io2.toString());
				}
			}*/
		@Override
		public void run() {
			while (!done) {
				try {
					String msg = read.readLine();
					txtArea.append("�յ�MyClient��" + msg + "\n");
				} catch (Exception e4) {

				}
			}
		}
	}
}
