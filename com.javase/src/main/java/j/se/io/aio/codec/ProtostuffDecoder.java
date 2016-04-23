package j.se.io.aio.codec;

import java.nio.ByteBuffer;

import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.ProtostuffIOUtil;

import j.se.io.aio.MessageHanderService;
import j.se.io.aio.SocketChannelWrap;

public class ProtostuffDecoder extends LenthFieldDecoder {

	public ProtostuffDecoder(int newFieldSize) {
		super(newFieldSize);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
				int msgCode = buffer.getInt();
				Message retBean = null;
				int bodyLength = frameLength - 4;
				if (bodyLength > 0) {// 消息内容可以为空
					byte[] body = new byte[bodyLength];
					buffer.get(body);
					retBean = MessageHanderService.getHandlerBean(msgCode);
					ProtostuffIOUtil.mergeFrom(body, retBean, retBean.cachedSchema());
				}
				buffer.mark();
				client.messageReceived(msgCode, retBean);
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
