package j.se.io.aio.codec;

import java.nio.ByteBuffer;

import com.google.protobuf.MessageLite;

import j.se.io.aio.SocketChannelWrap;

public class ProtobufDecoder extends LenthFieldDecoder {

	private final MessageLite prototype;

	public ProtobufDecoder(MessageLite prototype, int lengthFieldSize) {
		super(lengthFieldSize);
		this.prototype = prototype.getDefaultInstanceForType();
	}

	public ByteBuffer decode(Integer result, ByteBuffer buffer, SocketChannelWrap client) throws Exception {
		if (result < fieldSize) {
			return buffer;
		}
		buffer.flip();
		buffer.mark();
		int frameLength = getFrameLength(buffer);
		while (true) {
			if (buffer.remaining() < frameLength) {
				buffer.reset();
				break;
			}
			if (frameLength > 0) {
				Object resultObj = prototype.newBuilderForType().mergeFrom(buffer.array(), buffer.arrayOffset() + buffer.position(), frameLength).build();
				client.fireMessageReceived(resultObj);
				buffer.position(buffer.position() + frameLength);
				buffer.mark();
			}
			if (buffer.remaining() > fieldSize) {
				frameLength = getFrameLength(buffer);
			} else {
				break;
			}
		}
		buffer.compact();
		return buffer;
	}

}
