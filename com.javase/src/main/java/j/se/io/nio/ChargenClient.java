package j.se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
	public static int DEFAULT_PORT = 8000;

	public static void main(String[] args) {
		int port = DEFAULT_PORT;
		try {
			SocketAddress address = new InetSocketAddress("localhost", port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			// 通道会从Socket读取数据，填充到缓冲区，返回成功读取并存储在缓冲区的字节数  
			// 设置为非阻塞时，当没有字节返回时会立即返回0，  
			// 此时程序应写成:  
			//	      client.configureBlocking(false);  
			//	      while (true) {  
			//	        int n = client.read(buffer);  
			//	        if (n > 0) {  
			//	          buffer.flip();   
			//	          out.write(buffer);  
			//	          buffer.clear();  
			//	        } else if (n == -1)  
			//	          break; // 服务器故障了  
			//	      }  
			while (client.read(buffer) != -1) {
				buffer.flip(); // 回绕 limit = position, position = 0  
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
