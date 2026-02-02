import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for(int t = 1;t<=testCase;t++) {
			String input = br.readLine();
			int[] memory = new int[input.length()];
			int answer = 0;
			int nowBit = 0;

			for(int i = 0;i<input.length();i++) {
				memory[i] = input.charAt(i) - '0';
			}

			for(int bit : memory) {
				if(bit != nowBit) {
					answer++;
					nowBit = 1 - nowBit;
				}
			}

			System.out.printf("#%d %d%n", t, answer);

		}
	}
}