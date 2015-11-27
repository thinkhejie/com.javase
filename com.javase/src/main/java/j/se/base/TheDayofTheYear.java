package j.se.base;

public class TheDayofTheYear {
	public int counttheDay(String p_Number) {
		int i = 0;
		String strYear = new String();
		String strMonth = new String();
		String strDay = new String();
		strYear = p_Number.substring(0, 4);
		strMonth = p_Number.substring(4, 6);
		strDay = p_Number.substring(6, 8);
		int iYear = Integer.parseInt(strYear);
		int iMonth = Integer.parseInt(strMonth);
		int iDay = Integer.parseInt(strDay);
		i = iDay;
		if (iMonth == 1) {
			System.out.println(i);
			return i;
		} else if (iMonth >= 2) {
			Month month = new Month();
			int iTimes = iMonth - 1;
			for (int k = 1; k <= iTimes; k++) {
				i = i + month.countDay(iYear, k);
			}
			System.out.println(i);
			return i;
		} else
			return i;
	}
}
