import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer days = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(days.nextToken());
        int m = Integer.parseInt(days.nextToken());

        String[] t = br.readLine().split(" ");
        long[] salary = new long[t.length];

        for(int i = 0;i<t.length;i++) {
            salary[i] = Long.parseLong(t[i]);
        }

        long sum = 0;
        long max = 0;

        for(int i = 0;i<m;i++) {
            sum += salary[i];
        }
        max = sum;

        for(int i = m;i<n;i++) {
            sum += salary[i];
            sum -= salary[i-m];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}