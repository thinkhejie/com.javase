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
import java.net.InetAddress;
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

public class MyClient extends JFrame {
	JTextArea txtArea;
	JTextField txtMsg;
	JButton btn1, btn2;
	Socket client;
	Container cp = getContentPane();
	PrintWriter writer;
	BufferedReader read;
	Thread thread1;
	boolean done = false;

	public MyClient() {
		try {
			thread1 = new ThreadListener();
			thread1.start();
			jbInit();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		try {
			InetAddress addr = InetAddress.getByName(null);
			client = new Socket(addr, MyServer.PORT);
			txtArea.append("\n�Ѿ����ӵ�������:" + client.getPort() + "\n");
			read = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = txtMsg.getText();
					writer.println(msg);
					writer.flush();
					txtArea.append("MyClient ���ͣ�" + msg + "\n");
					txtMsg.setText("");
				}
			});
		} catch (Exception e1) {
			System.err.println(e1.toString());
		}
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event5) {
				try {
					setVisible(false);
					done = true;
					writer.close();
					read.close();
					System.exit(0);
					thread1.destroy();
				} catch (Exception er) {
					System.out.println("�޷��ر�" + er.getMessage());
				}
			}
		});
	}

	public void jbInit() {
		setTitle("�ͻ���");
		setSize(400, 200);
		JPanel p = new JPanel();
		txtArea = new JTextArea(20, 50);
		txtArea.append("�ͻ����Ѿ�����\n");
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
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					setVisible(false);
					done = true;
					read.close();
					writer.close();
					System.exit(0);
					thread1.destroy();
				} catch (Exception e5) {
					System.err.println(e5.toString());
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e3) {
			System.err.println(e3.toString());
		}
		new MyClient();
	}

	class ThreadListener extends Thread {
		/*
		 * public ThreadListener() { try{ read=new BufferedReader(new
		 * InputStreamReader(client.getInputStream())); }catch(IOException io) {
		 * System.err.println("IOException"+io.getMessage()); } }
		 */
		@Override
		public void run() {
			while (!done) {
				try {
					String msg = read.readLine();
					txtArea.append("�յ�MyServer��" + msg + "\n");
				} catch (Exception e3) {

				}
			}
		}
	}

}
