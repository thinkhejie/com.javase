package lion.netio;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

import lion.netio.codec.LenthFieldDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles a cycle of reading / writing on the {@code Client}.
 */
public class SocketReader {

	private static Logger logger = LoggerFactory.getLogger(SocketReader.class);

	private final ConnectionHolder server;

	private LenthFieldDecoder decoder;

	public SocketReader(ConnectionHolder newServer, LenthFieldDecoder decoder) {
		server = newServer;
		this.decoder = decoder;
	}

	public boolean acceptsMessages() {
		return false;
	}

	/**
	 * Runs a cycle of doing a beforeRead action and then enqueing a new read on the client. Handles closed channels and errors while reading. If the client is still connected a
	 * new round of actions are called.
	 */
	public void run(ByteBuffer input, final SocketChannelWrap client) {
		// callback.beforeRead(client);
		client.read(input, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				// if result is negative or zero the connection has been closed or something gone wrong
				if (result < 1) {
					logger.info("Closing connection to {}", client);
					client.close();
					server.channelClosed(client);
				} else {
					ByteBuffer retBuffer = frameMessageDecoder(result, buffer, client);
					// enqueue next round of actions
					client.run(retBuffer);
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer buffer) {
				client.close();
				server.channelClosed(client);
			}
		});
	}

	/**
	 * 处理帧消息
	 * 
	 * @param result
	 * @param buffer
	 * @param client
	 * @return
	 */
	protected ByteBuffer frameMessageDecoder(Integer result, ByteBuffer buffer, SocketChannelWrap client) {
		if (logger.isDebugEnabled()) {
			logger.debug("receive frame message in frameMessageDecoder");
		}
		ByteBuffer ret = null;
		try {
			ret = decoder.decode(result, buffer, client);
		} catch (Exception e) {
			logger.error("", e);
		}
		return ret;
	}
}
