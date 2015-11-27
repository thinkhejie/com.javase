package j.se.systeminfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NumberT {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// float f = 11.1f;
		System.out.println(String.valueOf(Float.MIN_VALUE));// 1.4E-45
		System.out.println(String.valueOf(Float.MAX_VALUE));// 3.4028235E38
		Runtime rt = Runtime.getRuntime();
		// Process proc = rt.exec("ls -al");
		Process proc = rt.exec("crf_test -m ./CRFmodel/model3./Temp/tmp.txt");
		proc.getOutputStream().close();
		InputStream stdin = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(stdin));
		String currentLine = "";
		while ((currentLine = br.readLine()) != null) {
			// System.out.println("enter!");
			System.out.println(currentLine);
		}
		br.close();
		//bw.close();

	}
	// Float��Double����Сֵ�����ֵ�����Կ�ѧ�������ʽ����ģ�
	// ��β�ġ�E+���֡���ʾE֮ǰ������Ҫ����10�Ķ��ٱ���
	// ����3.14E3����3.14��1000=3140��3.14E-3����3.14/1000=0.00314��
}
