import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static char[] ops;
    static int numCnt, opCnt;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        // 총 길이에 따라 숫자, 연산자의 개수가 정해짐
        nums = new int[N/2+1];
        ops = new char[N/2];

        numCnt = 0;

        // 짝수, 홀수에 맞춰 연산자와 숫자를 각 배열에 집어넣기
        opCnt = 0;
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (i % 2 == 0) nums[numCnt++] = c - '0';
            else ops[opCnt++] = c;
        }

        dfs(0, nums[0]);
        System.out.println(ans);
    }

    // 연산자에 따라 연산결과 반환
    static int calc(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }

    static void dfs(int opIdx, int acc) {
        if (opIdx == opCnt) {
            ans = Math.max(ans, acc);
            return;
        }

        // 괄호 없이 바로 연산하고 다음값 진행
        int next1 = calc(acc, nums[opIdx + 1], ops[opIdx]);
        dfs(opIdx + 1, next1);

        // 현재 연산하는 값의 다음 연산에 괄호를 적용하여 미리 연산하고 현재 값과 연산한뒤 다음 단계 진행을 위해 opIdx + 2를 dfs에 사용
        if (opIdx + 1 < opCnt) {
            int bracket = calc(nums[opIdx + 1], nums[opIdx + 2], ops[opIdx + 1]);
            int next2 = calc(acc, bracket, ops[opIdx]);
            dfs(opIdx + 2, next2);
        }
    }
}