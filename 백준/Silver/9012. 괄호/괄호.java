import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		for(int i = 0;i<testCase;i++) {
			ArrayDeque<String> arr = new ArrayDeque<>();
			String[] vps = br.readLine().split("");
			String result = "YES";

			for(String s : vps) {
				if(s.equals("(")) arr.add(s);
				else if(s.equals(")")) {
					if(arr.isEmpty()) {
						result = "NO";
						break;
					}
					String poll = arr.pollLast();
					if(!poll.equals("(")) {
						result = "NO";
						break;
					}
				}
			}
			if(!arr.isEmpty()) result = "NO";

			System.out.println(result);
		}
	}
}
