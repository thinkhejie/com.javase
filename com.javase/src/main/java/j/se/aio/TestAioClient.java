package j.se.aio;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;

import lion.netio.AsyncIOClient;
import lion.netio.ServerFactory;
import lion.netio.SocketChannelWrap;
import lion.netio.SocketReader;
import lion.netio.codec.LenthFieldDecoder;

public class TestAioClient {
    /*
     * JDK7中的java aio新增的类和接口主要有：

    AsynchronousServerSocketChannel ，对应于bio中的ServerSocket和nio中的ServerSocketChannel，用于server端的网络程序。

    AsynchronousSocketChannel，对云关于bio中的Socket和nio中的SocketChannel，用于client端的网络程序。

    CompletionHandler，回调接口，在socket进行accept/connect/read/write等操作时，可以传入一个CompletionHandler的实现，操作执行完毕后，会调用注册的CompletionHandler。

    除了CompletionHandler这种回调方式，aio中还支持返回Future对象，使用Future来设定回调操作。

     */
    @org.junit.Test
    public void testClientRun() throws IOException {
        ServerFactory factory = new ServerFactory();
        AsyncIOClient aoiClientConnector = factory.createClientAoiService();
        AsynchronousSocketChannel channel = aoiClientConnector.connect("127.0.0.1", 9008);
        int lengthFieldWidth = 1;
        SocketChannelWrap clientChannel = new SocketChannelWrap(channel, new SocketReader(aoiClientConnector, new LenthFieldDecoder(lengthFieldWidth)), lengthFieldWidth);
        try {
            channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
        } catch (IOException e) {
            // ignore
        }
        clientChannel.run(null);
        for (int i = 0; i < 1; i++) {
            clientChannel.writeStringMessage("灰机哥" + Thread.currentThread().getId());
        }
        //clientChannel.writeStringMessage("show me the money");
        //把socket中的数据读取到buffer中  
        /*
        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);
        clientChannel.read(clientBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                buffer.flip();
                try {
                    System.out.println("收到" + channel.getRemoteAddress().toString() + "的消息:" + new String(buffer.array()));
                    buffer.compact();
                } catch (CharacterCodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
            }
        });
        */
    }
}
