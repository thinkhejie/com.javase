package ailk.oa.tools;

import java.util.regex.Pattern;

public class StringOperate {
	public static String GbkToIso88591(String paramString) {
		String str;
		if (paramString == null)
			str = "";
		while (true) {
			//return str;
			try {
				byte[] arrayOfByte = paramString.getBytes("GB2312");
				str = new String(arrayOfByte, "ISO-8859-1");
			} catch (Exception localException) {
				localException.printStackTrace();
				str = "";
			}
		}
	}

	public static String IsNull(String paramString1, String paramString2) {
		if (paramString1 == null)
			;
		for (String str = paramString2;; str = paramString1)
			return str;
	}

	public static String Iso88591ToGBK(String paramString) {
		String str;
		if (paramString == null)
			str = "";
		while (true) {
			//return str;
			try {
				byte[] arrayOfByte = paramString.getBytes("ISO-8859-1");
				str = new String(arrayOfByte, "GB2312");
			} catch (Exception localException) {
				localException.printStackTrace();
				str = "";
			}
		}
	}

	public static boolean isContainsChinese(String paramString) {
		boolean bool = Pattern.compile("[涓�").matcher(paramString).find();
		if (bool)
			bool = true;
		while (true) {
			return bool;
			//Object localObject = null;
		}
	}

	public static boolean isNumeric(String paramString) {
		boolean bool = Pattern.compile("[0-9]*").matcher(paramString).matches();
		Object localObject;
		if (!bool)
			localObject = null;
		while (true) {
			//return localObject;
			int i = 1;
		}
	}
}
