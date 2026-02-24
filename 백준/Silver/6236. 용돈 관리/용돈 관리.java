import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] uses = new int[N];
        int max = 0;
        int sum = 0;
        for(int i = 0;i<N;i++) {
            uses[i] = Integer.parseInt(br.readLine());
            if (uses[i] > max) max = uses[i];
            sum += uses[i];
        }

        int left = max;
        int right = sum;
        int answer = 0;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int total = 0;
            int nowCur = 0;
            for(int i = 0;i<N;i++) {
                if(nowCur < uses[i]) {
                    total++;
                    nowCur = mid;
                }
                nowCur -= uses[i];
            }
            if(total <= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}