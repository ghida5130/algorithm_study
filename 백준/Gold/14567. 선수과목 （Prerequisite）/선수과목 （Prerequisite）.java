import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 과목의 수
        int M = Integer.parseInt(st.nextToken());   // 선수 조건의 수

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);    // 특정과목이 어떤 과목의 선수과목인지 리스트 저장
            indegree[B]++;      // 선수과목 개수 저장
        }

        // 선수과목이 없는 과목을 queue에 추가 (시작 과목)
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                dp[i] = 1;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 모든 선수과목을 들어야 다음과목을 들을 수 있다 = 해당과목에 도달할때 가장 먼거리로 온 과목이 마지막 선수과목
            for (int next : graph[now]) {
                dp[next] = Math.max(dp[next], dp[now] + 1);
                indegree[next]--;
                if (indegree[next] == 0) queue.add(next);
            }
        }

        for(int i = 1;i<=N;i++) {
            System.out.printf("%d ", dp[i]);
        }
    }
}