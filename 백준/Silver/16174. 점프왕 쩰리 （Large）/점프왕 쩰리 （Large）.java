import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int size;
	static int[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		visited = new int[size][size];
		for(int i = 0;i<size;i++) {
			StringTokenizer boardRow = new StringTokenizer(br.readLine());
			for(int j = 0;j<size;j++) {
				board[i][j] = Integer.parseInt(boardRow.nextToken());
			}
		}

		boolean isAvailable = dfs(0, 0);
		System.out.println(isAvailable ? "HaruHaru" : "Hing");
	}

	static boolean dfs(int r, int c) {
		if(board[r][c] == 0) return false;
		if(board[r][c] == -1) return true;

		int nr = r + board[r][c];
		int nc = c + board[r][c];
		boolean a = false;
		boolean b = false;

		if(nr >= 0 && nr < size && visited[nr][c] == 0) {
			visited[nr][c] = 1;
			a = dfs(nr, c);
		}
		if(nc >= 0 && nc < size && visited[r][nc] == 0) {
			visited[r][nc] = 1;
			b = dfs(r, nc);
		}
		return a || b;
	}
}