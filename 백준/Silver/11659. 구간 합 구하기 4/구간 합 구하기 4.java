import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer valInput = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(valInput.nextToken());
		int m = Integer.parseInt(valInput.nextToken());

		String[] numbersInput = br.readLine().split(" ");
		int[] numbers = Arrays.stream(numbersInput).mapToInt(Integer::valueOf).toArray();
		int[] sums = new int[n+1];

		for(int i = 1;i<=n;i++) {
			sums[i] = sums[i-1] + numbers[i-1];
		}

		for(int i = 0;i<m;i++) {
			StringTokenizer stage = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(stage.nextToken());
			int right = Integer.parseInt(stage.nextToken());

			System.out.println(sums[right] - sums[left-1]);
		}
	}
}
