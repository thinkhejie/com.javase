package j.se.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputContent {
	public static void main(String[] args) {
		String x = null;
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(System.getProperty("file.encoding"));
		while (true) {
			try {
				InputStreamReader ISR = new InputStreamReader(System.in, "UTF-8");
				BufferedReader br1 = new BufferedReader(ISR);
				x = br1.readLine();
				System.out.println(x);
			} catch (IOException e) {

			}

		}
	}
}
