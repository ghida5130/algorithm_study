import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int m;
	public static List<Integer> answer = new ArrayList<>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] userInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		n = userInput[0];
		m = userInput[1];

		dfs(0, 0);
		bw.flush();
	}

	public static void dfs (int idx, int depth) throws IOException {
		if(depth == m) {
			for(int i : answer) bw.write(i + " ");
			bw.newLine();
			return;
		}
		for(int i = 1;i<=n;i++) {
			answer.add(i);
			dfs(i + 1, depth + 1);
			answer.remove(answer.size() - 1);
		}
	}
}