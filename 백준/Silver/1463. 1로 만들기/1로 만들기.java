import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        // 입력값 + 1 만큼의 dp배열 만들기
        int[] dp = new int[X + 1];

        // 순차적 연산을 위해 1번 인덱스 미리 0으로 채우기
        dp[1] = 0;

        for (int i = 2; i <= X; i++) {
            // 1을 뺀것을 가정하고 먼저 채우기
            dp[i] = dp[i - 1] + 1;

            // 앞서 연산한 dp[i](i를 1로 만드는 최소연산수)와 dp[i/2]+1 (현재값을 2로나눈 결과를 1로 만드는 최소 연산수 + 1) 두가지를 비교
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // 2와 동일
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        System.out.println(dp[X]);
    }
}