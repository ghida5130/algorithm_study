import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int sizeC = Integer.parseInt(st.nextToken());
		int sizeR = Integer.parseInt(st.nextToken());

		int[][] board = new int[sizeR][sizeC];
		int[][] visited = new int[sizeR][sizeC];

		int max = 0;

		ArrayDeque<Pos> queue = new ArrayDeque<>();

		for(int i = 0;i<sizeR;i++) {
			StringTokenizer boardRow = new StringTokenizer(br.readLine());
			for(int j = 0;j<sizeC;j++) {
				int nowVal = Integer.parseInt(boardRow.nextToken());
				board[i][j] = nowVal;
				if(nowVal == 1) {
					queue.addLast(new Pos(i, j, 0));
					visited[i][j] = 1;
				}
			}
		}

		while(!queue.isEmpty()) {
			Pos nowPos = queue.pollFirst();
			int nowR = nowPos.r;
			int nowC = nowPos.c;
			int nowDepth = nowPos.depth;
			for(int i = 0;i<4;i++) {
				int nextR = nowR + dr[i];
				int nextC = nowC + dc[i];
				if(nextR >= 0 && nextC >= 0 && nextR < sizeR && nextC < sizeC) {
					if(visited[nextR][nextC] == 0 && board[nextR][nextC] == 0) {
						board[nextR][nextC] = 1;
						visited[nextR][nextC] = 1;
						max = Math.max(max, nowDepth + 1);
						queue.add(new Pos(nextR, nextC, nowDepth+1));
					}
				}
			}
		}

		boolean temp = false;
		for(int i = 0;i<sizeR;i++) {
			boolean temp2 = false;
			for(int j = 0;j<sizeC;j++) {
				if(board[i][j] == 0) {
					temp = true;
					temp2 = true;
					break;
				}
			}
			if(temp2) break;
		}

		if(temp) max = -1;

		System.out.println(max);
	}

	static class Pos {
		public int r;
		public int c;
		public int depth;

		Pos(){}
		Pos(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}
}