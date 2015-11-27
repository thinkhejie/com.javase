package j.se.aio;

import lion.netio.AsyncIOServer;
import lion.netio.ServerFactory;
import lion.netio.codec.LenthFieldDecoder;

public class TestAioServer {

    @org.junit.Test
    public void test() throws Exception {
        final ServerFactory factory = new ServerFactory();
        int port = 8653;
        AsyncIOServer aoiServer = factory.createServerAoi(port, new LenthFieldDecoder(1), 1);
        aoiServer.run();
        Thread.sleep(2000);
        // wait
        System.in.read();
        factory.shutdown();
    }
}
