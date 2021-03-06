package j.se.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import j.se.io.aio.codec.LenthFieldDecoder;

public class AsyncIOServer implements Runnable, ConnectionHolder {

	private static Logger logger = LoggerFactory.getLogger(AsyncIOServer.class);

	private final Set<SocketChannelWrap> connections = Collections.newSetFromMap(new ConcurrentHashMap<SocketChannelWrap, Boolean>());
	private int port;
	private final AsynchronousServerSocketChannel listener;
	private final AsynchronousChannelGroup channelGroup;
	private final LenthFieldDecoder decoder;
	private final int lengthFieldWidth;

	private AtomicInteger connectorId = new AtomicInteger();

	/**
	 * 
	 * @param port
	 *            to listen to
	 * @throws java.io.IOException
	 *             when failing to start the server
	 */
	public AsyncIOServer(int port, AsynchronousChannelGroup channelGroup, LenthFieldDecoder decoder, int lengthFieldWidth) throws IOException {
		this.channelGroup = channelGroup;
		this.port = port;
		this.decoder = decoder;
		this.lengthFieldWidth = lengthFieldWidth;
		listener = createListener(channelGroup);
	}

	/**
	 * 
	 * @return The socket address that the server is bound to
	 * @throws java.io.IOException
	 *             if an I/O error occurs
	 */
	public SocketAddress getSocketAddress() throws IOException {
		return listener.getLocalAddress();
	}

	/**
	 * Start accepting connections
	 */
	public void run() {

		// call accept to wait for connections, tell it to call our CompletionHandler when there
		// is a new incoming connection
		listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
			@Override
			public void completed(AsynchronousSocketChannel result, Void attachment) {
				// request a new accept and handle the incoming connection
				listener.accept(null, this);
				try {
                    System.out.println("有客户端连接:" +result.getRemoteAddress().toString());
                } catch (IOException e) {
                    logger.error("", e);
                }
				handleNewConnection(result);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				result.write(buffer.put("你好".getBytes()));
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
			}
		});
	}

	/**
	 * Shuts down the server
	 * 
	 * @throws InterruptedException
	 *             if terminated while waiting for shutdown
	 * @throws IOException
	 *             if failing to shutdown the channel group
	 */
	public void shutdown() throws InterruptedException, IOException {
		channelGroup.shutdownNow();
		channelGroup.awaitTermination(1, TimeUnit.SECONDS);
	}

	/*
	 * Creates a listener and starts accepting connections
	 */
	private AsynchronousServerSocketChannel createListener(AsynchronousChannelGroup channelGroup) throws IOException {
		final AsynchronousServerSocketChannel listener = openChannel(channelGroup);
		listener.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		listener.bind(new InetSocketAddress(port));
		return listener;
	}

	private AsynchronousServerSocketChannel openChannel(AsynchronousChannelGroup channelGroup) throws IOException {
		return AsynchronousServerSocketChannel.open(channelGroup);
	}

	/**
	 * Creates a new client and adds it to the list of connections. Sets the clients handler to the initial state of NameReader
	 * 
	 * @param channel
	 *            the newly accepted channel
	 */
	private void handleNewConnection(AsynchronousSocketChannel channel) {
		SocketChannelWrap client = new SocketChannelWrap(channel, new SocketReader(this, decoder), lengthFieldWidth);
		try {
			channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
		} catch (IOException e) {
			// ignore
		}
		connections.add(client);
		client.setId(connectorId.incrementAndGet());
		client.run(null);
	}

	/**
	 * Sends a message to all clients except the source. The method is synchronized as it is desired that messages are sent to all clients in the same order as received.
	 * 
	 * @param client
	 *            the message source
	 * @param message
	 *            the message to be sent
	 */
	public void writeMessageToClients(SocketChannelWrap client, String message) {
		for (SocketChannelWrap clientConnection : connections) {
			if (clientConnection != client) {
				clientConnection.writeStringMessage(message);
			}
		}
	}

	public void removeClient(SocketChannelWrap client) {
		connections.remove(client);
	}

	@Override
	public void channelClosed(SocketChannelWrap channel) {
		connections.remove(channel);
	}

}
