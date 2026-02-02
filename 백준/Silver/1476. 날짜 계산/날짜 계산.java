import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int e=1, s=1, m=1;

		String[] userInput = br.readLine().split(" ");
		int ue = Integer.parseInt(userInput[0]);
		int us = Integer.parseInt(userInput[1]);
		int um = Integer.parseInt(userInput[2]);

		int year = 1;

		while(true) {
			if(e == ue && s == us && m == um) {
				System.out.println(year);
				break;
			}
			
			e = e >= 15 ? 1 : e+1;
			s = s >= 28 ? 1 : s+1;
			m = m >= 19 ? 1 : m+1;
			year++;
		}
	}
}
