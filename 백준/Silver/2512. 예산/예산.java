import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[size];
        int maxVal = 0;
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0;i<size;i++) {
            int value = Integer.parseInt(st.nextToken());
            arr[i] = value;
            if(value > maxVal) maxVal = value;
        }
        
        int firstMax = Integer.parseInt(br.readLine());
        
        int left = 0;
        int right = maxVal;
        while(left<=right) {
            int center = (left + right) / 2;
            long sum = 0;

            for(int i = 0;i<size;i++) sum += Math.min(arr[i], center);
            if(sum > firstMax) {
                right = center - 1;
            } else if (sum <= firstMax) {
                answer = center;
                left = center + 1;
            }
        }
        System.out.println(answer);
    }
}