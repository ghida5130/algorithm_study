import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int total = Integer.parseInt(st.nextToken());
		int cases = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] graph = new ArrayList[total + 1];
		for(int i = 0;i<=total;i++) graph[i] = new ArrayList<>();

		int[] indegree = new int[total+1];

		for(int i = 0;i<cases;i++) {
			StringTokenizer input = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(input.nextToken());
			int b = Integer.parseInt(input.nextToken());

			graph[a].add(b);
			indegree[b]++;
		}

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i = 1;i<=total;i++) {
			if(indegree[i] == 0) queue.addLast(i);
		}

		while(!queue.isEmpty()) {
			int now = queue.pollFirst();
			sb.append(now + " ");

			for(int nxt : graph[now]) {
				indegree[nxt]--;
				if(indegree[nxt] == 0) queue.addLast(nxt);
			}
		}

		System.out.println(sb);
	}
}