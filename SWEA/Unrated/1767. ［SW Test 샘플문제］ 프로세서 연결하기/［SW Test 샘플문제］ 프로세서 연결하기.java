import java.io.*;
import java.util.*;

public class Solution {
	static int[][] board;
	static int size;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static List<Core> cores;
	static int maxCore;
	static int minCable;
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1;t<=testCase;t++) {
			size = Integer.parseInt(br.readLine());
			board = new int[size][size];
			maxCore = 0;
			minCable = Integer.MAX_VALUE;
			cores = new ArrayList<>();

			for(int i = 0;i<size;i++) {
				StringTokenizer boardRow = new StringTokenizer(br.readLine());
				for(int j = 0;j<size;j++) {
					int nowVal = Integer.parseInt(boardRow.nextToken());
					if(nowVal == 1) {
						board[i][j] = nowVal;
						if(i == 0 || j == 0 || i == size-1 || j == size-1) continue;
						cores.add(new Core(i, j));
					}
				}
			}

			dfs(0, 0, 0);
			System.out.println("#" + t + " " + minCable);
		}
	}

	static void dfs(int index, int coreCnt, int cableCnt) {
		// 모든 코어에 대한 처리가 끝났다면 (index == cores.size())
        if(index == cores.size()) {
			// 연결된 코어가 최대연결코어 개수와 같다면 전선길이로 비교
			if(coreCnt == maxCore) {
				minCable = Math.min(minCable, cableCnt);
			}
            // 연결된 코어가 최대연결코어 개수보다 크다면 값 즉시 갱신 (코어수가 우선)
			if(coreCnt > maxCore) {
				maxCore = coreCnt;
				minCable = cableCnt;
			}
			return;
		}

		Core nowCore = cores.get(index);
        // 4방향으로 연결 시도
		for(int i = 0;i<4;i++) {
            // 연결 불가능하면 해당 방향 continue
			if(!(canConnect(nowCore.x, nowCore.y, i))) continue;

            // 연결 가능하다면 연결하고 재귀
			int cable = connect(nowCore.x, nowCore.y, i, 2);
			dfs(index+1, coreCnt+1, cableCnt + cable);
			connect(nowCore.x, nowCore.y, i, 0);
		}
        // 연결하지 않는 경우의수 재귀
		dfs(index+1, coreCnt, cableCnt);
		
	}

	static boolean canConnect(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
            // 한방향으로 진행하다가 벽을만나면 연결에 성공한것으로 간주한다.
			if(nr < 0 || nc < 0 || nr >= size || nc >= size) return true;
            // 빈공간과 벽 외에 다른물건을 만나면 연결실패로 간주한다.
			if(board[nr][nc] != 0) break;
		}
		return false;
	}

	static int connect(int r, int c, int dir, int val) {
		int nr = r;
		int nc = c;
		int connected = 0;
        // val로 전선을 관리한다. 2를 받으면 전선 연결이고 0을 받으면 전선 제거(백트래킹)
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size) break;
			board[nr][nc] = val;
			connected++;
		}
		return connected;
	}

	static class Core {
		public int x;
		public int y;
		
		Core(){}
		Core(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}