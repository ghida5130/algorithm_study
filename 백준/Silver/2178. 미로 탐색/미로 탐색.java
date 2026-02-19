import java.io.*;
import java.util.*;

public class Main {
	static final int[] dr = {0,1,0,-1};
	static final int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		StringTokenizer st = new StringTokenizer(br.readLine());
		int rSize = Integer.parseInt(st.nextToken());
		int cSize = Integer.parseInt(st.nextToken());
		int[][] board = new int[rSize][cSize];
		int[][] visited = new int[rSize][cSize];
		visited[0][0] = 1;
		int answer = 0;

		for(int i = 0;i<rSize;i++) {
			int[] boardRow = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
			for(int j = 0;j<cSize;j++) {
				board[i][j] = boardRow[j];
			}
		}

		ArrayDeque<Pos> queue = new ArrayDeque<>();
		queue.addLast(new Pos(0, 0, 0));

		while(!queue.isEmpty()) {
			Pos nowPos = queue.pollFirst();
			int nowR = nowPos.r;
			int nowC = nowPos.c;
			int nowDepth = nowPos.depth;
			if(nowR == rSize-1 && nowC == cSize-1) {
				answer = nowDepth + 1;
				break;
			}
			for(int i = 0;i<4;i++) {
				int nr = nowR + dr[i];
				int nc = nowC + dc[i];
				if(nr >= 0 && nc >= 0 && nr < rSize && nc < cSize) {
					if(visited[nr][nc] == 0 && board[nr][nc] == 1) {
						visited[nr][nc] = 1;
						queue.addLast(new Pos(nr, nc, nowDepth + 1));
					}
				}
			}
		}
		System.out.println(answer);
	}
	
	static class Pos {
		int r;
		int c;
		int depth;
		
		public Pos(){}
		public Pos(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}
}