import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());		// 수빈 위치
		int K = Integer.parseInt(st.nextToken());		// 동생 위치

		int[] visited = new int[100001];
		int[] visitedDepth = new int[100001];
		int min = Integer.MAX_VALUE;
		int count = 0;

		ArrayDeque<Pos> queue = new ArrayDeque<>();
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

			if(nextVal <= 100000) {
				if(visited[nextVal] == 0) {
					visited[nextVal] = 1;
					visitedDepth[nextVal] = nowDepth + 1;
					queue.addLast(new Pos(nextVal, nowDepth + 1));
				} else {
					if (visitedDepth[nextVal] >= nowDepth + 1) {
						visitedDepth[nextVal] = nowDepth + 1;
						queue.addLast(new Pos(nextVal, nowDepth + 1));
					}
				}
			}
			
			if(prevVal >= 0) {
				if(visited[prevVal] == 0) {
					visited[prevVal] = 1;
					visitedDepth[prevVal] = nowDepth + 1;
					queue.addLast(new Pos(prevVal, nowDepth + 1));
				} else {
					if (visitedDepth[prevVal] >= nowDepth + 1) {
						visitedDepth[prevVal] = nowDepth + 1;
						queue.addLast(new Pos(prevVal, nowDepth + 1));
					}
				}
			}

			if(mulVal <= 100000) {
				if(visited[mulVal] == 0) {
					visited[mulVal] = 1;
					visitedDepth[mulVal] = nowDepth + 1;
					queue.addLast(new Pos(mulVal, nowDepth + 1));
				} else {
					if (visitedDepth[mulVal] >= nowDepth + 1) {
						visitedDepth[mulVal] = nowDepth + 1;
						queue.addLast(new Pos(mulVal, nowDepth + 1));
					}
				}
			}
		}

		System.out.println(min);
		System.out.println(count);
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