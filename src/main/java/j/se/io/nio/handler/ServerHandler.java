package j.se.io.nio.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerHandler implements Handler {

	@Override
	public void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		System.out.println("Server: accept client socket " + socketChannel);
		socketChannel.configureBlocking(false);
		socketChannel.register(key.selector(), SelectionKey.OP_READ);
		/*
		SelectionKey key2 = socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
		socketChannel.configureBlocking(false);	
		ByteBuffer buffer = ByteBuffer.allocate(74);
		buffer.put("hello world".getBytes());
		buffer.put((byte) '\r');
		buffer.put((byte) '\n');
		buffer.flip();
		key2.attach(buffer);
		*/
	}

	@Override
	public void handleRead(SelectionKey key) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		SocketChannel socketChannel = (SocketChannel) key.channel();
		while (true) {
			int readBytes = socketChannel.read(byteBuffer);
			if (readBytes > 0) {
				System.out.println("Server: readBytes = " + readBytes);
				System.out.println("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
				byteBuffer.flip();
				//return msg to client
				socketChannel.write(byteBuffer);
				break;
			}
		}
		socketChannel.close();
	}

	@Override
	public void handleWrite(SelectionKey key) throws IOException {
		ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
		System.out.println("Write: data = " + new String(byteBuffer.array(), 0, byteBuffer.capacity()));
		byteBuffer.flip();
		SocketChannel socketChannel = (SocketChannel) key.channel();
		socketChannel.write(byteBuffer);
		if (byteBuffer.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		byteBuffer.compact();
	}
}
