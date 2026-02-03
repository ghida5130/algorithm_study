import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		String[] solsStringArray = br.readLine().split(" ");
		int[] sols = Arrays.stream(solsStringArray).mapToInt(Integer::valueOf).toArray();

		Arrays.sort(sols);

		int left = 0;
		int right = sols.length - 1;
		int leftAnswer = 0;
		int rightAnswer = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;

		while(left < right) {
			sum = sols[left] + sols[right];
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				leftAnswer = sols[left];
				rightAnswer = sols[right];
			}
			if(sum >= 0) {
				if(sum == 0) break;
				right--;
			} else {
				left++;
			}
		}
		System.out.printf("%d %d", leftAnswer, rightAnswer);
	}
}
