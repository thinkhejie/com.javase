package j.reflect;

public enum WeekDay {

	SUN(1), MON(), TUE, WED, THI, FRI, SAT;
	private WeekDay() {
		System.out.println("first");
	}

	private WeekDay(int day) {
		System.out.println("second");
	}
}
