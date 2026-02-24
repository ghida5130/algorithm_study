import java.io.*;
import java.util.*;

public class Main {
    // x+y+z=k 에서 x+y=k-z 로 풀이했다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int[] tempArr = new int[N*N];
        int tempIdx = 0;

        for(int i = 0;i<N;i++) {
            for(int j = 0;j<N;j++) {
                tempArr[tempIdx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(tempArr);
        int answer = 0;

        T:for(int i = N-1;i>=0;i--) {
            for(int j = N-1;j>=0;j--) {
                // k가 z보다 크거나 같을경우 의미가 없으므로 continue 
                int left = 0;
                int right = N*N;

                // k-z값을 찾기위한 이분탐색
                while(left <= right) {
                    int mid = left + (right - left) / 2;
                    int now = tempArr[mid];
                    int diff = arr[i] - arr[j];
                    if(now == diff) {
                        answer = arr[i];
                        break T;
                    }
                    if(now > diff) right = mid - 1;
                    if(now < diff) left = mid + 1;
                }
            }
        }

        System.out.println(answer);
    }
}