import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // row가 col보다 작은지 저장하는 배열
        boolean[][] board = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = true; // a < b
        }

        // 플로이드 워셜로 간접 비교 관계 갱신
        // i : 출발점, k : 중간점, j : 도착점
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {

                // i에서 k로 갈 수 없을 경우 continue
                if (!board[i][k]) continue;
                
                // i에서 k를 거쳐 j로 갈 수 있을 경우 i에서 j로 갈 수 있는것으로 변경
                for (int j = 1; j <= N; j++) {
                    if (board[k][j]) {
                        board[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int count = 0;

            // i와 j가 어떻게든 연결되어있을경우 (비교가 가능 할 경우) count를 증가
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (board[i][j] || board[j][i]) count++;
            }

            // count가 N-1일경우 i는 모든 항목과 연결된것이므로 키가 몇번째인지 판단할 수 있음
            if (count == N - 1) answer++;
        }

        System.out.println(answer);
    }
}