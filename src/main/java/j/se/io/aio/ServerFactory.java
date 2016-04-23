package j.se.io.aio;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;

import j.se.io.aio.codec.LenthFieldDecoder;

public class ServerFactory {

	private static Logger logger = LoggerFactory.getLogger(ServerFactory.class);

	private AsynchronousChannelGroup asyncChannelGroup;

	private static final ThreadLocal<LinkedBuffer> buffers = new ThreadLocal<LinkedBuffer>();

	public ServerFactory() throws IOException {
		asyncChannelGroup = AsynchronousChannelGroup.withFixedThreadPool(AsyncIOOptions.CHANNEL_GROUP_THREAD_SIZE, Executors.defaultThreadFactory());
	}

	public AsyncIOServer createServerAoi(int port, LenthFieldDecoder decoder, int lengthFieldWidth) {
		AsyncIOServer server = null;
		try {
			server = new AsyncIOServer(port, asyncChannelGroup, decoder, lengthFieldWidth);
		} catch (IOException e) {
			logger.error("", e);
		}
		return server;
	}

	public AsyncIOClient createClientAoiService() {
		AsyncIOClient server = null;
		try {
			server = new AsyncIOClient(asyncChannelGroup);
		} catch (Exception e) {
			logger.error("", e);
		}
		return server;
	}

	public void shutdown() throws InterruptedException, IOException {
		asyncChannelGroup.shutdownNow();
		asyncChannelGroup.awaitTermination(1, TimeUnit.SECONDS);
	}

	/**
	 * 每个线程有自己的buffer，可以共享
	 * 
	 * @return
	 */
	public static LinkedBuffer getProtoBuffer() {
		LinkedBuffer buffer = buffers.get();
		if (buffer == null) {
			buffer = LinkedBuffer.allocate(65536);
			buffers.set(buffer);
		}
		buffer.clear();
		return buffer;
	}

	public static void main(String[] args) throws Exception {
		final ServerFactory factory = new ServerFactory();
		int port = 8653;
		AsyncIOServer aoiServer = factory.createServerAoi(port, new LenthFieldDecoder(1), 1);
		aoiServer.run();
		logger.info("aoi server listening on port:{}", port);
		Thread.sleep(2000);
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				AsyncIOClient aoiClientConnector = factory.createClientAoiService();
				AsynchronousSocketChannel channel = aoiClientConnector.connect("127.0.0.1", 8653);
				int lengthFieldWidth = 1;
				SocketChannelWrap clientChannel = new SocketChannelWrap(channel, new SocketReader(aoiClientConnector, new LenthFieldDecoder(lengthFieldWidth)), lengthFieldWidth);
				try {
					channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
				} catch (IOException e) {
					// ignore
				}
				clientChannel.run(null);
				for (int i = 0; i < 200; i++) {
					clientChannel.writeStringMessage("灰机哥" + Thread.currentThread().getId());
				}
				clientChannel.writeStringMessage("show me the money");
			}
		};
		threadPool.submit(task);
		threadPool.submit(task);
		// wait
		System.in.read();
		threadPool.shutdownNow();
		threadPool.awaitTermination(1, TimeUnit.SECONDS);
		factory.shutdown();
	}

}
