package j.se.io.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Schema;

import j.se.io.aio.bean.GetCurrentTimeResp;

public class MessageHanderService {

	private static Logger logger = LoggerFactory.getLogger(MessageHanderService.class);

	public static void hanldeTime(SocketChannelWrap channelWrap, GetCurrentTimeResp resp) {
		logger.info("currentTime={}", resp.getTime());
	}

	public static void hanldeTime(SocketChannelWrap channelWrap) {
		long time = System.currentTimeMillis();
		GetCurrentTimeResp resp = new GetCurrentTimeResp(time);
		Schema<GetCurrentTimeResp> schema = resp.cachedSchema();
		channelWrap.writeProtostuffMessage(10002, resp, schema);
	}

	public static void dispatchMessage(int msgCode, SocketChannelWrap channelWrap, Message<?> message) {
		if (msgCode == 10001) {
			hanldeTime(channelWrap);
		} else if (msgCode == 10002) {
			hanldeTime(channelWrap, (GetCurrentTimeResp) message);
		}
	}

	public static Message<?> getHandlerBean(int msgCode) {
		if (msgCode == 10002) {
			return new GetCurrentTimeResp();
		}
		return null;
	}

}
