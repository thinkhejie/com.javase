package j.se.base;

import j.se.jdbc.db.common.ITEST;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util implements ITEST {
	// **********************************
	// **函数名：isNumber
	// **功     能：
	// **参     数：String
	// **返回值：boolean
	// ***********************************
	public boolean isNumber(String s) {
		boolean bl = false;
		// s = "2244434245460KIP";	
		// public static final String number = "\\d+";
		String str = "^[0-9]*[1-9][0-9]*$";
		Pattern p = Pattern.compile(str);
		Matcher match = p.matcher(s);
		if (match.find())
			return !bl;
		else
			return bl;
	}

	// **********************************
	// **函数名：isNumber
	// **功     能：
	// **参     数：String
	// **返回值：boolean
	// ***********************************
	@Override
	public int getIntNumber() {
		int inum;
		Random rd = new Random();
		inum = rd.nextInt();
		return inum;

	}

	// **********************************
	// **函数名：
	// **功     能：
	// **参     数：
	// **返回值：
	// ***********************************
	@Override
	public long getLongNumber() {
		long lnum;
		Random rd = new Random();
		lnum = rd.nextLong();
		return lnum;

	}

	// **********************************
	// **函数名：
	// **功     能：
	// **参     数：
	// **返回值：
	// ***********************************
	@Override
	public float getFloatNumber() {
		float fnum;
		Random rd = new Random();
		fnum = rd.nextFloat();
		return fnum;
	}

	// **********************************
	// **函数名：
	// **功     能：
	// **参     数：
	// **返回值：
	// ***********************************
	public float createArray() {
		float fnum;
		Random rd = new Random();
		fnum = rd.nextFloat();
		return fnum;

	}

	// **********************************
	// **函数名：
	// **功     能：
	// **参     数：
	// **返回值：
	// ***********************************
	public int[] createArray(int s) {
		int[] intarray = new int[s];
		for (int m = 0; m < s; m++) {
			intarray[s] = new Random().nextInt();
		}
		return intarray;
	}

	// **********************************
	// **函数名：
	// **功     能：
	// **参     数：
	// **返回值：
	// ***********************************
	public int convertSrtingtoInt(String s) {
		int i = Integer.parseInt(s);
		return i;
	}

	// **********************************
	// **函数名：createDir
	// **功     能：
	// **参     数：p_strPath
	// **返回值：boolean
	// ***********************************
	public static boolean createDir(String p_strPath) {
		boolean boolmkDir = false;
		File fiPath = new File(p_strPath);
		if (fiPath.isDirectory()) {
			Util.deleteFile(p_strPath);
		}
		fiPath.mkdirs();
		return !boolmkDir;
	}

	// **********************************
	// **函数名：createFile
	// **功     能：
	// **参     数：p_strFilePath   p_strFileName
	// **返回值：boolean
	// ***********************************
	public static boolean createFile(String p_strFilePath, String p_strFileName) {
		boolean i = false;
		try {
			StringBuilder sbFileName = new StringBuilder();
			sbFileName.append(p_strFilePath);
			sbFileName.append(File.separator);
			sbFileName.append(p_strFileName);
			File fiPath = new File(p_strFilePath);
			File fiFileName = new File(sbFileName.toString());
			if (!fiPath.isDirectory()) {
				fiPath.mkdirs();
			}
			if (fiFileName.isFile()) {
				fiFileName.delete();
			}
			fiFileName.createNewFile();
			return !i;
		} catch (Exception ex) {
			ex.printStackTrace();
			return i;
		} finally {

		}
	}

	// *************************************************************************
	// **函数名：deletefile
	// **功     能：
	// --------------  --------------------------------------------------------
	// **参     数： p_strDelPath  需要删除的文件或者目录的路径	
	// **返回值： boolean
	// *************************************************************************
	public static boolean deleteFile(String p_strDelPath) {
		try {
			File file = new File(p_strDelPath);
			if (!file.isDirectory()) {
				file.delete();
				return true;
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (String element : filelist) {
					File delfile = new File(p_strDelPath + File.separator + element);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deleteFile(p_strDelPath + File.separator + element);
					}
				}
				file.delete();
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//判断闰年的条件归纳为:
	//1.能被4整除不能被100整除.
	//2.能被400整除.
	//************************************************************************
	//* 方法名　　    ：IsLeapYear
	//* 处理内容　　　：判断是否为闰年
	//* --------------  ------------------------------------------------------
	//* 参数          ：int                          年份        
	//* 返回值        ：Boolean                      true:是闰年           
	//************************************************************************
	public static boolean IsLeapYear(int year) {
		try {
			//判断是否为闰年 
			return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// *************************************************************************
	// **函数名：ConvertByteToString
	// **功     能：
	// --------------  ---------------------------------------------------------
	// **参     数： byte[] 
	// **返回值： String
	// *************************************************************************
	public static String ConvertByteToString(byte[] p_buff) {
		String strBuff = new String();
		return strBuff;

	}

	// *************************************************************************
	// **函数名：getCurrentTime
	// **功     能：获取系统当前时间
	// --------------  ---------------------------------------------------------
	// **参     数：  
	// **返回值： String
	// *************************************************************************
	public static String getCurrentTime() {
		String strSep = "-";
		String strCurrentTime = new String();
		StringBuilder sbCurrentTime = new StringBuilder();
		Calendar now = Calendar.getInstance();
		int iYear = now.get(Calendar.YEAR);
		int iMonth = 1 + now.get(Calendar.MONTH);
		int iCurrentDay = now.get(Calendar.DAY_OF_MONTH);
		int iHour = now.get(Calendar.HOUR_OF_DAY);
		int iMin = now.get(Calendar.MINUTE);
		int iSec = now.get(Calendar.SECOND);
		String strCurrentYear = Integer.toString(iYear);
		String strCurrentMonth = Integer.toString(iMonth);
		String strCurrentDay = Integer.toString(iCurrentDay);
		String strCurrentHour = Integer.toString(iHour);
		String strCurrentMin = Integer.toString(iMin);
		String strCurrentSec = Integer.toString(iSec);
		sbCurrentTime.append(strCurrentYear);
		sbCurrentTime.append(strSep);
		sbCurrentTime.append(strCurrentMonth);
		sbCurrentTime.append(strSep);
		sbCurrentTime.append(strCurrentDay);
		sbCurrentTime.append(strSep);
		sbCurrentTime.append(strCurrentHour);
		sbCurrentTime.append(strSep);
		sbCurrentTime.append(strCurrentMin);
		sbCurrentTime.append(strSep);
		sbCurrentTime.append(strCurrentSec);
		strCurrentTime = sbCurrentTime.toString();
		return strCurrentTime;
	}

	/**
	* 全角字符转半角字符
	* 
	* @param QJStr
	* @return String
	*/
	public static final String QJToBJChange(String QJStr) {
		char[] chr = QJStr.toCharArray();
		String str = "";
		for (int i = 0; i < chr.length; i++) {
			chr[i] = (char) (chr[i] - 65248);
			str += chr[i];
		}
		return str;
	}

	/**
	 * 将某个日期以固定格式转化成字符串
	 * 
	 * @param date
	 * @return String
	 */
	public static String dateToStr(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 判断任意一个整数是否素数
	 * 
	 * @param n
	 * @return boolean
	 */
	public static boolean isPrimes(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * 获得任意一个整数的阶乘，递归
	 * 
	 * @param n
	 * @return n!
	 */
	public static int factorial(int n) {
		if (n == 1)
			return 1;

		return n * factorial(n - 1);
	}
}
