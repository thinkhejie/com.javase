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

public class MyClient2 extends JFrame {
	////设置服务器的简单界面及与SOCKET相关的一些声明
	JTextArea txtArea;
	JTextField txtMsg;
	JButton btn1, btn2;
	Socket client;
	Container cp = getContentPane();
	PrintWriter writer;
	BufferedReader read;
	Thread thread1;
	boolean done = false;

	//构造函数
	public MyClient2() {
		try {
			////建立线程
			thread1 = new ThreadListener();
			thread1.start();
			//调用初始化函数 
			jbInit();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		try {
			//打开SOCKET
			InetAddress addr = InetAddress.getByName(null);
			client = new Socket(addr, MyServer.PORT);
			//若成功连接服务器
			txtArea.append("\n已经连接到服务器:" + client.getPort() + "\n");
			//创建输入/输出数据流
			read = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			//给客户机上的按钮1创建事件（点击客户机上的发送按钮）
			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String msg = txtMsg.getText();
					writer.println(msg);
					writer.flush();
					txtArea.append("MyClient 发送：" + msg + "\n");
					txtMsg.setText("");
				}
			});
		} catch (Exception e1) {
			System.err.println(e1.toString());
		}

		//给客户机上的按钮2创建事件（点击客户机上的关闭按钮）
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event5) {
				try {
					setVisible(false);
					done = true;
					writer.close();
					read.close();
					System.exit(0);
				} catch (Exception er) {
					System.out.println("无法关闭" + er.getMessage());
				}
			}
		});
	}

	//初始化函数 
	public void jbInit() {
		setTitle("客户机");
		setSize(400, 200);
		JPanel p = new JPanel();
		txtArea = new JTextArea(20, 50);
		txtArea.append("客户端已经启动\n");
		JScrollPane jsp = new JScrollPane(txtArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		p.add(jsp);
		cp.add(p, BorderLayout.CENTER);

		JLabel la = new JLabel("消息");
		txtMsg = new JTextField(20);
		btn1 = new JButton("发送");
		btn2 = new JButton("关闭");
		p.setLayout(new FlowLayout());
		p.add(la);
		p.add(txtMsg);
		p.add(btn1);
		p.add(btn2);
		cp.add(p, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		//创建关闭窗口事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					setVisible(false);
					done = true;
					read.close();
					writer.close();
					System.exit(0);
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
		new MyClient2();
	}

	class ThreadListener extends Thread {
		//线程程序负责接收服务器传来的数据，并且显示
		@Override
		public void run() {
			while (!done) {
				try {
					String msg = read.readLine();
					txtArea.append("收到MyServer：" + msg + "\n");
				} catch (Exception e3) {

				}
			}
		}
	}

}
