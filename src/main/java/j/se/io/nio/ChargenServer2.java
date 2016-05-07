package j.se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import j.se.io.nio.handler.ServerHandler;

public class ChargenServer2 {

	public static int DEFAULT_PORT = 8000;

	public static void main(String[] args) {
		ServerHandler cs = new ServerHandler();
		int port = DEFAULT_PORT;
		System.out.println("Listening for connections on port " + port);
		ServerSocketChannel serverChannel;
		Selector selector;
		try {
			InetSocketAddress address = new InetSocketAddress(port);
			serverChannel = ServerSocketChannel.open();
			// 获取ServerSocketChannel的对等端(prre)对象
			ServerSocket ss = serverChannel.socket();
			ss.bind(address);
			// 非阻塞状态下，没有连接时，serverChannel.accept()会立即返回null
			serverChannel.configureBlocking(false);
			// 创建一个Selector，使程序能够对所有准备好的连接进行循环处理
			selector = Selector.open();
			// 在监视通道的选择器中进行注册
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		while (true) {
			try {
				// 调用选择器的select()方法，检查是否有就绪的通道，如果没有就绪通道，选择器就会等待（阻塞）
				selector.select(1000);
			} catch (IOException ex) {
				ex.printStackTrace();
				break;
			}
			// 找到了就绪的通道，selectedKeys()方法返回就绪通道的SelectionKey
			// socket空闲时，即为可写，有数据来时，可读
			// Set集合好像链接到服务器的客户端集合
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();
				iterator.remove();
				try {
					// 就绪通道是ServerSocketChannel，程序会接受一个新的SocketChannel并将其添加到选择器
					if (key.isAcceptable()) {
						cs.handleAccept(key);
						// 就绪通道是SocketChannel，程序会向通道写数据
					} else if (key.isWritable()) {
						System.out.println("Server: SelectionKey is writable.");
						cs.handleWrite(key);
					} else if (key.isReadable()) {
						System.out.println("Server: SelectionKey is readable.");  
						cs.handleRead(key);
					}
				} catch (IOException ex) {
					key.cancel();
					try {
						// 取消键后，仍可以得到键的通道
						key.channel().close();
					} catch (IOException cex) {
					}
				}
			}
		}
	}
}
