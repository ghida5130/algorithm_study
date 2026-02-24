import java.io.*;
import java.util.*;

public class Main {
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
                if(arr[i] == arr[j]) continue;
                int left = 0;
                int right = N*N;
                while(left <= right) {
                    int mid = left + (right - left) / 2;
                    if(tempArr[mid] == arr[i] - arr[j]) {
                        answer = arr[i];
                        break T;
                    }
                    if(tempArr[mid] > arr[i] - arr[j]) {
                        right = mid - 1;
                    }
                    if(tempArr[mid] < arr[i] - arr[j]) {
                        left = mid + 1;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}