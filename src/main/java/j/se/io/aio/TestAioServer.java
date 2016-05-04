package j.se.io.aio;

import j.se.io.aio.codec.LenthFieldDecoder;

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
