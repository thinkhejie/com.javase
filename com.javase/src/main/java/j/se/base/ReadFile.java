package j.se.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

	/**
	 * @param args
	 */
	public static void test() {
		try {
			Pattern pa = Pattern.compile("\\a");
			Matcher mat = pa.matcher("abcdedff");
			StringBuilder sb = new StringBuilder();
			sb.append("c:");
			sb.append(File.separator);
			sb.append("123.txt");
			File fi = new File(sb.toString());
			//fi.isFile();
			//fi.createNewFile();
			//FileWriter fw = new FileWriter(fi);
			//fw.write("大家好！");
			//fw.write("本书是《JSP编程技巧》");
			//fw.write("请多多指教！");
			//fw.write("\r\n");
			//fw.write("email:stride@sina.com");
			//fw.close();

			FileReader fr = new FileReader(fi);
			BufferedReader br = new BufferedReader(fr);

			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			//
			// while(!(br.readLine() ==null))
			// do {
			// Line = br.readLine();
			// System.out.println("br.readLine()" + Line);
			// Line = br.readLine();
			// }
			//
			//	String Line;
			//	while ((Line = br.readLine()) != null) 
			//while (br.readLine() != null)
			//	{    //Line = br.readLine();
			//System.out.println(Line);
			//}		
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

}
