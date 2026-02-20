import java.io.*;
import java.util.*;

public class Main {
	static final int[] dr = {0,1,0,-1};
	static final int[] dc = {1,0,-1,0};
	static int[] visited[];
	static ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int size;
	static char[][] board;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		board = new char[size][size];
		visited = new int[size][size];

		for(int i = 0;i<size;i++) {
			String boardRow = br.readLine(); 
			for(int j = 0;j<size;j++) {
				board[i][j] = boardRow.charAt(j);
			}
		}

		// 일반 bfs
		int aTotal = 0;
		for(int i = 0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				if(visited[i][j] == 0) {
					visited[i][j] = 1;
					char nowChar = board[i][j];
					aTotal++;
					queue.addLast(new int[]{i, j});
					bfs(nowChar);
				}
			}
		}

		visited = new int[size][size];

		// 적록색약용 배열 만들기 (G를 R로 변경)
		for(int i = 0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				if(board[i][j] == 'G') board[i][j] = 'R';
			}
		}

		// 적록색약 bfs
		int bTotal = 0;
		for(int i = 0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				if(visited[i][j] == 0) {
					visited[i][j] = 1;
					char nowChar = board[i][j];
					bTotal++;
					queue.addLast(new int[]{i, j});
					bfs(nowChar);
				}
			}
		}

		System.out.printf("%d %d", aTotal, bTotal);
	}

	static void bfs (char nowChar) {
		while(!queue.isEmpty()) {
			int[] nowVal = queue.pollFirst();
			int nowR = nowVal[0];
			int nowC = nowVal[1];
			for(int k = 0;k<4;k++) {
				int nextR = nowR + dr[k];
				int nextC = nowC + dc[k];
				if(nextR >= 0 && nextC >= 0 && nextR < size && nextC < size) {
					if(visited[nextR][nextC] == 0 && board[nextR][nextC] == nowChar) {
						visited[nextR][nextC] = 1;
						queue.addLast(new int[]{nextR, nextC});
					}
				}
			}
		}
	}
}