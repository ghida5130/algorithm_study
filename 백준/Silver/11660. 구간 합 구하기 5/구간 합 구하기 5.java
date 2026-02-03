import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer valInput = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(valInput.nextToken());
		int m = Integer.parseInt(valInput.nextToken());

		int[][] board = new int[n][n];

		for(int i = 0;i<n;i++) {
			String[] boardRow = br.readLine().split(" ");
			for(int j = 0;j<n;j++) {
				board[i][j] = Integer.parseInt(boardRow[j]);
			}
		}

		int[][] sums = new int[n+1][n+1];
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				sums[i+1][j+1] = board[i][j] + sums[i+1][j] + sums[i][j+1] - sums[i][j];
			}
		}

		for(int i = 0;i<m;i++) {
			String[] inputVal = br.readLine().split(" ");
			int[] stage = Arrays.stream(inputVal).mapToInt(Integer::valueOf).toArray();
			int x1 = stage[0], y1 = stage[1], x2 = stage[2], y2 = stage[3];
			int answer = sums[x2][y2] - sums[x2][y1-1] - sums[x1-1][y2] + sums[x1-1][y1-1];
			System.out.println(answer);
		}
	}
}
