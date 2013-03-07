package j.se.base;

import java.io.File;

public class TestUtil {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("C:");
		sb.append(File.separator);
		sb.append("01");
		sb.append(File.separator);
		sb.append("02");
		//sb.append(File.separator);
		//sb.append("03.txt");
		String s = sb.toString();
		Util.createFile(s, "03.txt");
		//File fi = new File(s);
		try {
			//fi.createNewFile();
		} catch (Exception ex) {

		}
		System.out.println(s);
		String[] strArray = s.split("\\\\");
		for (String element : strArray) {
			System.out.println(element);
		}
		//File fi = new File(sb.toString());
		//fi.isDirectory();
		//System.out.println(fi.isDirectory());		
		//Util.createFile(sb.toString(), "testhejiedir", "testhejiefile");
		//Util.createDir(sb.toString());
		//File fi = new File(sb.toString());
		//fi.deleteOnExit();
		//System.out.println(fi.isDirectory());
		//fi.mkdir();
		//Util.deleteFile(sb.toString());
	}
}
