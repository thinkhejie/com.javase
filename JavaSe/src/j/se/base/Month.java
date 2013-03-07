package j.se.base;

public class Month {

	int iMonth;

	Month() {

	}

	Month(int iMonth) {
		this.iMonth = iMonth;
	}

	public int countDay(int iYear, int iMonth) {
		int iCount = 0;
		switch (iMonth) {
		case 1: {
			iCount = 31;
			break;
		}
		case 2: {
			if (Util.IsLeapYear(iYear)) {
				iCount = 29;
			} else {
				iCount = 28;
			}
			break;
		}
		case 3: {
			iCount = 31;
			break;
		}
		case 4: {
			iCount = 30;
			break;
		}
		case 5: {
			iCount = 31;
			break;
		}
		case 6: {
			iCount = 30;
			break;
		}
		case 7: {
			iCount = 31;
			break;
		}
		case 8: {
			iCount = 31;
			break;
		}
		case 9: {
			iCount = 30;
			break;
		}
		case 10: {
			iCount = 31;
			break;
		}
		case 11: {
			iCount = 30;
			break;
		}
		case 12: {
			iCount = 31;
			break;
		}
		default: {
			break;
		}
		}
		return iCount;
	}
}
