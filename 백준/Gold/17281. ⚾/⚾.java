import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] order = new int[9];
    static boolean[] visited = new boolean[10];
    static int maxScore = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][10];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int p = 1; p <= 9; p++) {
                arr[i][p] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번선수 4번위치에 고정
        order[3] = 1;
        visited[1] = true;

        dfs(0);
        System.out.println(maxScore);
    }

    static void dfs(int idx) {
        // 순열이 만들어졌다면 시뮬레이션 연산
        if (idx == 9) {
            maxScore = Math.max(maxScore, simulate());
            return;
        }

        // 4번타자 스킵
        if (idx == 3) {
            dfs(idx + 1);
            return;
        }

        // 현재 남은선수(visited를 통해 판단)를 순차적으로 현재 idx에 넣고 dfs 수행
        for (int p = 2; p <= 9; p++) {
            if (visited[p]) continue;
            visited[p] = true;
            order[idx] = p;
            dfs(idx + 1);
            visited[p] = false;
        }
    }

    static int simulate() {
        int score = 0;
        int batterIdx = 0;

        for (int inning = 0; inning < N; inning++) {
            int outs = 0;

            boolean on1 = false;
            boolean on2 = false;
            boolean on3 = false;

            while (outs < 3) {
                int player = order[batterIdx];
                int res = arr[inning][player];

                if (res == 0) {
                    outs++;
                } else if (res == 1) { // 안타
                    if (on3) score++;
                    on3 = on2;
                    on2 = on1;
                    on1 = true;
                } else if (res == 2) { // 2루타
                    if (on3) score++;
                    if (on2) score++;
                    on3 = on1;
                    on2 = true;
                    on1 = false;
                } else if (res == 3) { // 3루타
                    if (on3) score++;
                    if (on2) score++;
                    if (on1) score++;
                    on3 = true;
                    on2 = false;
                    on1 = false;
                } else { // 홈런
                    if (on3) score++;
                    if (on2) score++;
                    if (on1) score++;
                    score++;
                    on3 = on2 = on1 = false;
                }

                batterIdx++;
                if (batterIdx == 9) batterIdx = 0;
            }
        }

        return score;
    }
}