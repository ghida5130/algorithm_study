import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {0,1,1,1,0,-1,-1,-1};
	static int[] dc = {1,1,0,-1,-1,-1,0,1};
	static int[][] board;
	static boolean[][] visited;
	static int rSize;
	static int cSize;

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cSize = Integer.parseInt(st.nextToken());
			rSize = Integer.parseInt(st.nextToken());
			int answer = 0;

			if(cSize == 0 && rSize == 0) break;

			board = new int[rSize][cSize];
			visited = new boolean[rSize][cSize];

			for(int i = 0;i<rSize;i++) {
				StringTokenizer boardRow = new StringTokenizer(br.readLine());
				for(int j = 0;j<cSize;j++) {
					board[i][j] = Integer.parseInt(boardRow.nextToken());
				}
			}

			for(int i = 0;i<rSize;i++) {
				for(int j = 0;j<cSize;j++) {
					if(!visited[i][j] && board[i][j] == 1) {
						dfs(i, j);
						answer++;
					}
				}
			}

			System.out.println(answer);
		}
	}

	static void dfs (int r, int c) {
		for(int i = 0;i<8;i++){
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nc < 0 || nr >= rSize || nc >= cSize) continue;
			if(!visited[nr][nc] && board[nr][nc] == 1) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}