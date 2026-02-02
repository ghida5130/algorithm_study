import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] userInput = br.readLine().split(" ");
		int n = Integer.parseInt(userInput[0]), m = Integer.parseInt(userInput[1]);

		String[] numbers = br.readLine().split(" ");

		int head = 1;
		int tail = 0;
		int sum = Integer.parseInt(numbers[0]);
		int res = 0;

		while (true) {
			if (sum == m) res++;
			if (sum >= m) {
				if(tail >= n) break;
				sum -= Integer.parseInt(numbers[tail++]);
			} else if (sum < m) {
				if(head >= n) break;
				sum += Integer.parseInt(numbers[head++]);

			}
		}

		System.out.println(res);
	}
}
