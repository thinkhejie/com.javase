package j.se.io.nio.bytebuffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

	public static void main(String[] args) {
		File file = new File("/file");
		long len = file.length();
		byte[] ds = new byte[(int) len];
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			MappedByteBuffer mappedByteBuffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, len);
			for (int offset = 0; offset < len; offset++) {
				byte b = mappedByteBuffer.get();
				ds[offset] = b;
			}
			raf.close();
		} catch (Exception e) {

		} finally {

		}
	}
}
