package lion.netio.codec;

import java.nio.ByteBuffer;

import lion.netio.SocketChannelWrap;

public class LenthFieldDecoder {

	protected int fieldSize = 1;

	public LenthFieldDecoder(int newFieldSize) {
		while (fieldSize < newFieldSize) {
			fieldSize <<= 1;
		}
	}

	public ByteBuffer decode(Integer result, ByteBuffer buffer, SocketChannelWrap client) throws Exception{
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
				byte[] body = new byte[frameLength];
				buffer.get(body);
				buffer.mark();
				client.fireMessageReceived(body);
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

	public int getFrameLength(ByteBuffer buffer) {
		int ret = 0;
		switch (fieldSize) {
		case 1:
			ret = buffer.get();
			break;
		case 2:
			ret = buffer.getShort();
			break;
		case 4:
			ret = buffer.getInt();
			break;
		default:
			break;
		}
		return ret;
	}

}
