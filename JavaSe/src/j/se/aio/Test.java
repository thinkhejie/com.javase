package j.se.aio;

public class Test {
	/*
	 * JDK7中的java aio新增的类和接口主要有：

	AsynchronousServerSocketChannel ，对应于bio中的ServerSocket和nio中的ServerSocketChannel，用于server端的网络程序。

	AsynchronousSocketChannel，对云关于bio中的Socket和nio中的SocketChannel，用于client端的网络程序。

	CompletionHandler，回调接口，在socket进行accept/connect/read/write等操作时，可以传入一个CompletionHandler的实现，操作执行完毕后，会调用注册的CompletionHandler。

	除了CompletionHandler这种回调方式，aio中还支持返回Future对象，使用Future来设定回调操作。

	 */
}
