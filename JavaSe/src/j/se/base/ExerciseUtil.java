package j.se.base;

import java.util.Random;

public class ExerciseUtil {

	/**
	 * @param args
	 */

	public static int getNumber() {
		int k;
		Random rd = new Random();
		k = rd.nextInt();
		return k;
	}

}
