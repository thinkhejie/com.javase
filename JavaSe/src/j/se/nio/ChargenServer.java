package j.se.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChargenServer {
	public static int DEFAULT_PORT = 19;

	public static void main(String[] args) {
		int port = DEFAULT_PORT;
		System.out.println("Listening for connections on port " + port);
		byte[] rotation = new byte[95 * 2];
		for (byte i = ' '; i <= '~'; i++) {
			rotation[i - ' '] = i;
			rotation[i + 95 - ' '] = i;
		}
		ServerSocketChannel serverChannel;
		Selector selector;
		try {
			InetSocketAddress address = new InetSocketAddress(port);
			serverChannel = ServerSocketChannel.open();
			// 获取ServerSocketChannel的对等端(prre)对象  
			ServerSocket ss = serverChannel.socket();
			ss.bind(address);
			//非阻塞状态下，没有连接时，serverChannel.accept()会立即返回null
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
				// 调用选择器的select()方法，检查是否有就绪的通道，  
				// 如果没有就绪通道，选择器就会等待（阻塞）  
				selector.select();
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
					// 就绪通道是ServerSocketChannel，程序会接受一个新的SocketChannel
					// 并将其添加到选择器
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						// 注册了SelectionKey.OP_ACCEPT，当有accept发生时，Selector会监控到
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client);
						client.configureBlocking(false);
						// 每个SelectionKey都有一个Object类型的“附件”，  
						// 在这里，可以将通道要写入网络的缓冲区存储到这个对象中
						SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
						ByteBuffer buffer = ByteBuffer.allocate(74);
						buffer.put(rotation, 0, 72);
						buffer.put((byte) '\r');
						buffer.put((byte) '\n');
						buffer.flip();
						key2.attach(buffer);
						// 就绪通道是SocketChannel，程序会向通道写数据  
					} else if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						// hasRemaining：如果有没写完的数据，就写入到通道  
						// 否则，同rotation数据中的下一行数据重新填充缓冲区，再写入通道  
						if (!buffer.hasRemaining()) {
							buffer.rewind();
							// 得到上一行的首字符  
							int first = buffer.get();
							// 准备好改变缓冲区中的数据  
							buffer.rewind();
							// 寻找rotation中新的首字符位置  
							int position = first - ' ' + 1;
							// 将数据从rotation复制到缓冲区  
							buffer.put(rotation, position, 72);
							buffer.put((byte) '\r');
							buffer.put((byte) '\n');
							// 准备缓冲区写入  
							buffer.flip();
						}
						client.write(buffer);
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
