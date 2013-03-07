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

public class MyServer2 extends JFrame {
	//设置服务器的简单界面及与SOCKET相关的一些声明

	JTextArea txtArea;
	JTextField txtMsg;
	JButton btn1, btn2;
	ServerSocket myServer;
	Socket s;
	Container cp = getContentPane();
	PrintWriter writer;
	BufferedReader read;
	Thread thread1;
	boolean done = false;
	public static final int PORT = 9000;

	//构造函数
	public MyServer2() {
		try {
			//打开SOCKET
			myServer = new ServerSocket(PORT);
			//若有客户机
			//s=myServer.accept();
			txtArea.append("已经连接到客户机" + s.toString() + ",\n端口为:" + PORT + "\n");
			//创建输入/输出数据流
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (Exception e4) {
			System.err.println(e4.toString());
		}
		try {
			//建立线程
			thread1 = new MyThread();
			thread1.start();
			//调用初始化函数  
			jbInit();
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		//给服务器上的按钮2创建事件（点击服务器上的关闭按钮）
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
				} catch (Exception e5) {
					System.err.println(e5.getMessage());
				}
			}
		});
	}

	//初始化函数 
	public void jbInit() {
		//创建服务器的界面
		setTitle("服务器");
		setSize(400, 200);
		JPanel p = new JPanel();
		txtArea = new JTextArea(20, 50);
		txtArea.append("服务器已经启动\n");
		//添加一个文本区域
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
			public void windowClosing(WindowEvent event3) {
				try {
					setVisible(false);
					done = true;
					writer.close();
					read.close();
					System.exit(0);
				} catch (Exception e5) {
					System.err.println(e5.getMessage());
				}
			}
		});

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception eve) {

		}
		new MyServer2();
	}

	class MyThread extends Thread {
		//线程程序负责接收客户机传来的数据，并且显示
		@Override
		public void run() {
			while (!done) {
				try {
					String msg = read.readLine();
					txtArea.append("收到MyClient：" + msg + "\n");
					//给服务器上的按钮1创建事件（点击服务器上的发送按钮）
					btn1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent event) {
							try {
								s = myServer.accept();
								String msg = txtMsg.getText();
								writer.println(msg);
								writer.flush();
								txtArea.append("MyServer 发送: " + msg + "\n");
								txtMsg.setText("");
							} catch (Exception e2) {
								System.err.println(e2.getMessage());
							}
						}
					});
				} catch (Exception e4) {

				}
			}
		}
	}
}
