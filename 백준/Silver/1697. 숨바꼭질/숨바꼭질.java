import java.io.*;
import java.util.*;

public class Main {
	static int[] visited;
	static ArrayList<Integer>[] edges;
	static int total;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 0;

		int[] visited = new int[100001];
		visited[N] = 1;

		ArrayDeque<Pos> queue = new ArrayDeque<>();
		queue.addLast(new Pos(N, 0));

		while(!queue.isEmpty()) {
			Pos nowValue = queue.pollFirst();
			int nowDepth = nowValue.depth;

			if(nowValue.x == K) {
				answer = nowDepth;
				break;
			}

			int walkNext = nowValue.x + 1;
			int walkPrev = nowValue.x - 1;
			int teleport = nowValue.x * 2;
			if(walkNext <= 100000 && visited[walkNext] == 0) {
				visited[walkNext] = 1;
				queue.addLast(new Pos(walkNext, nowDepth + 1));
			}
			if(walkPrev >= 0 && visited[walkPrev] == 0) {
				visited[walkPrev] = 1;
				queue.addLast(new Pos(walkPrev, nowDepth + 1));
			}
			if(teleport >= 0 && teleport <= 100000 && visited[teleport] == 0) {
				visited[teleport] = 1;
				queue.addLast(new Pos(teleport, nowDepth + 1));
			}
		}

		System.out.println(answer);
	}

	static class Pos {
		int x;
		int depth;

		Pos(){}
		Pos(int x, int depth){
			this.x = x;
			this.depth = depth;
		}
	}
}