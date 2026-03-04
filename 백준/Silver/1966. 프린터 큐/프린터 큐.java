import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0;t<tc;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 문서 수 
            int M = Integer.parseInt(st.nextToken());   // 인쇄 순번이 궁금한 문서 idx

            Queue<Doc> queue = new ArrayDeque<>();          
            int[] cnt = new int[10];

            st = new StringTokenizer(br.readLine());    // 우선순위 정보

            for (int i = 0; i < N; i++) {
                int p = Integer.parseInt(st.nextToken());
                queue.offer(new Doc(i, p));
                // 특정 우선순위의 개수를 배열에 저장 (최대 우선순위를 계속 찾기위해)
                cnt[p]++;
            }

            // 인쇄횟수 계산
            int printed = 0;

            while (!queue.isEmpty()) {
                Doc now = queue.poll();
                int best = maxPriority(cnt);

                if (now.pri == best) {
                    printed++;
                    // 프린트한 항목의 우선순위 카운팅--
                    cnt[now.pri]--;

                    // 목표 문서가 출력됐을경우 반복문 종료
                    if (now.idx == M) {
                        break;
                    }
                } else {
                    queue.offer(now);
                }
            }
            System.out.printf("%d%n", printed);
        }
    }

    // 높은 idx에서 순차접근하며 최대 우선순위 찾기
    static int maxPriority(int[] cnt) {
        for (int p = 9; p >= 1; p--) {
            if (cnt[p] > 0) return p;
        }
        return 0;
    }

    static class Doc {
        int idx;
        int pri;

        Doc(int idx, int pri) {
            this.idx = idx;
            this.pri = pri;
        }
    }
}