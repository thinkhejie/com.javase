package lion.netio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncIOClient implements ConnectionHolder {

	private static Logger logger = LoggerFactory.getLogger(AsyncIOClient.class);

	private AsynchronousChannelGroup asyncChannelGroup;

	private final Set<AsynchronousSocketChannel> connections = Collections.newSetFromMap(new ConcurrentHashMap<AsynchronousSocketChannel, Boolean>());

	public AsyncIOClient(AsynchronousChannelGroup channelGroup) throws Exception {
		asyncChannelGroup = channelGroup;
	}

	public AsynchronousSocketChannel connect(String ip, int port) {
		AsynchronousSocketChannel channel;
		try {
			channel = AsynchronousSocketChannel.open(asyncChannelGroup);
			Future<Void> future = channel.connect(new InetSocketAddress(ip, port));
			future.get();
			logger.info("successfully connect to {}:{}", ip, port);
			connections.add(channel);
			return channel;
		} catch (IOException | InterruptedException | ExecutionException e) {
			logger.error("", e);
			return null;
		}
	}

	@Override
	public void channelClosed(SocketChannelWrap channelWrap) {
		connections.remove(channelWrap.getChannel());
	}

}
