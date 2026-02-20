import java.io.*;
import java.util.*;

public class Main {
	static int[] visited;
	static int[] visitedDepth;
	static ArrayDeque<Pos> queue;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());		// 수빈 위치
		int K = Integer.parseInt(st.nextToken());		// 동생 위치

		visited = new int[100001];
		visitedDepth = new int[100001];
		int min = Integer.MAX_VALUE;
		int count = 0;

		queue = new ArrayDeque<>();
		queue.addLast(new Pos(N, 0));
		visited[N] = 1;

		while(!queue.isEmpty()) {
			Pos now = queue.pollFirst();
			int nowPos = now.pos;
			int nowDepth = now.depth;
			int nextVal = nowPos + 1;
			int prevVal = nowPos - 1;
			int mulVal = nowPos * 2;

			if(nowPos == K) {
				if(nowDepth < min) {
					min = nowDepth;
					count = 1;
				} else if (nowDepth == min) {
					count++;
				}
			}

			if(nextVal <= 100000) calc(nextVal, nowDepth);
			if(prevVal >= 0) calc(prevVal, nowDepth);
			if(mulVal <= 100000) calc(mulVal, nowDepth);
		}

		System.out.println(min);
		System.out.println(count);
	}

	static void calc(int value, int depth) {
		if(visited[value] == 0) {
			visited[value] = 1;
			visitedDepth[value] = depth + 1;
			queue.addLast(new Pos(value, depth + 1));
		} else {
			if (visitedDepth[value] >= depth + 1) {
				visitedDepth[value] = depth + 1;
				queue.addLast(new Pos(value, depth + 1));
			}
		}
	}

	static class Pos {
		int pos;
		int depth;
		Pos(){}
		Pos(int pos, int depth){
			this.pos = pos;
			this.depth = depth;
		}
	}
}