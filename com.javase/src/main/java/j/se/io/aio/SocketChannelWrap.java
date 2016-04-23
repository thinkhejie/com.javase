package j.se.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.google.protobuf.MessageLite;

import j.se.io.aio.bean.Foo;

public class SocketChannelWrap {

	private static Logger logger = LoggerFactory.getLogger(SocketChannelWrap.class);

	private AsynchronousSocketChannel channel;

	private SocketReader reader;

	private int id;

	private int writePrefixLength = 1;

	private final Queue<ByteBuffer> queue = new LinkedList<ByteBuffer>();
	private boolean writing = false;

	public SocketChannelWrap(AsynchronousSocketChannel channel, SocketReader reader, int lengthFieldWidth) {
		this.channel = channel;
		this.reader = reader;
		while (writePrefixLength < lengthFieldWidth) {
			writePrefixLength <<= 1;
		}
	}

	public void run(ByteBuffer input) {
		reader.run(input, this);
	}

	public void read(ByteBuffer input, CompletionHandler<Integer, ? super ByteBuffer> completionHandler) {
		if (input == null) {
			input = ByteBuffer.allocate(AsyncIOOptions.MAX_RECEIVE_BUFFER_SIZE);
		}
		if (!channel.isOpen()) {
			return;
		}
		channel.read(input, input, completionHandler);
	}

	/**
	 * Closes the channel
	 */
	public void close() {
		try {
			logger.info("closing channel");
			channel.close();
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private void writeBuffer(ByteBuffer buffer) {
		channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				if (buffer.hasRemaining()) {
					channel.write(buffer, buffer, this);
				} else {
					// Go back and check if there is new data to write
					writeFromQueue();
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				logger.error("write failed!", exc);
			}
		});
	}

	/**
	 * Enqueues a write of the buffer to the channel. The call is asynchronous so the buffer is not safe to modify after passing the buffer here.
	 * 
	 * @param buffer
	 *            the buffer to send to the channel
	 */
	private void writeMessage(final ByteBuffer buffer) {
		boolean threadShouldWrite = false;

		synchronized (queue) {
			queue.add(buffer);
			// Currently no thread writing, make this thread dispatch a write
			if (!writing) {
				writing = true;
				threadShouldWrite = true;
			}
		}

		if (threadShouldWrite) {
			writeFromQueue();
		}
	}

	/**
	 * Sends a message
	 * 
	 * @param string
	 *            the message
	 */
	public void writeStringMessage(String string) {
		try {
			byte[] content = string.getBytes("UTF-8");
			writeLengthFieldMsg(content);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
	}

	public void writeProtobufMessage(MessageLite messageLite) {
		byte[] content = messageLite.toByteArray();
		writeLengthFieldMsg(content);
	}

	public void writeProtobufMessage(MessageLite.Builder messageLiteBuilder) {
		byte[] content = messageLiteBuilder.build().toByteArray();
		writeLengthFieldMsg(content);
	}

	/**
	 * protostuff协议格式:4+x,其中，4位消息号+x位byte的消息内容
	 * 
	 * @param msgCode
	 * @param msgContent
	 * @param schema
	 */
	public <T> void writeProtostuffMessage(int msgCode, T msgContent, Schema<T> schema) {
		byte[] content = ProtostuffIOUtil.toByteArray(msgContent, schema, ServerFactory.getProtoBuffer());
		ByteBuffer byteBuffer = allocateMemoryAndWriteLengthField(content.length + 4);
		byteBuffer.putInt(msgCode);
		byteBuffer.put(content);
		byteBuffer.flip();
		writeMessage(byteBuffer);
	}

	public void writeProtostuffMessage(int msgCode) {
		ByteBuffer byteBuffer = allocateMemoryAndWriteLengthField(4);
		byteBuffer.putInt(msgCode);
		byteBuffer.flip();
		writeMessage(byteBuffer);
	}

	public ByteBuffer allocateMemoryAndWriteLengthField(int contentLength) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(contentLength + writePrefixLength);
		switch (writePrefixLength) {
		case 1:
			byteBuffer.put((byte) contentLength);
			break;
		case 2:
			byteBuffer.putShort((short) contentLength);
			break;
		case 4:
			byteBuffer.putInt(contentLength);
			break;
		default:
			break;
		}
		return byteBuffer;
	}

	/**
	 * 以1字节为长度
	 * 
	 * @param content
	 */
	public void writeLengthFieldMsg(byte[] content) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(content.length + 1);
		byteBuffer.put((byte) content.length);
		byteBuffer.put(content);
		byteBuffer.flip();
		writeMessage(byteBuffer);
	}

	private void writeFromQueue() {
		ByteBuffer buffer;

		synchronized (queue) {
			buffer = queue.poll();
			if (buffer == null) {
				writing = false;
			}
		}

		// No new data in buffer to write
		if (writing) {
			writeBuffer(buffer);
		}
	}

	/**
	 * 收到消息提示
	 * 
	 * @param body
	 */
	public void fireMessageReceived(Object body) {
		try {
			if (body instanceof byte[]) {
				byte[] ret = (byte[]) body;
				logger.info("message received channelId={},msgLength={},content={}", new Object[] { id, ret.length, new String(ret, "UTF-8") });
			} else if (body instanceof Foo.Person) {
				Foo.Person aPerson = (Foo.Person) body;
				logger.info("receive proto message,id={},name={},motto={},gender={}", new Object[] { aPerson.getId(), aPerson.getName(), aPerson.getMotto(), aPerson.getGender() });
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
	}

	public void messageReceived(int msgCode, Message<?> msgContent) {
		if (logger.isDebugEnabled()) {
			logger.debug("receive protostuff message,msgContent={}", msgContent);
		}
		MessageHanderService.dispatchMessage(msgCode, this, msgContent);
	}

	public AsynchronousSocketChannel getChannel() {
		return channel;
	}

	public void setId(int size) {
		id = size;
	}

}
