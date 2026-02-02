import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] scores = new int[n][3];

		for(int i = 0;i<n;i++) {
			String[] temp = br.readLine().split(" ");
			int num = Integer.parseInt(temp[0]);
			int strike = Integer.parseInt(temp[1]);
			int ball = Integer.parseInt(temp[2]);
			scores[i] = new int[]{num, strike, ball};
		}

		int result = 0;

		for(int i = 100;i<1000;i++) {
			int a = i / 100;
			int b = (i / 10) % 10;
			int c = i % 10;

			// 숫자 0이 들어간 경우 continue
			if (a == 0 || b == 0 || c == 0) continue;
			if (a == b || b == c || a == c) continue;

			boolean ok = true;
			for(int j = 0;j<n;j++) {
				int[] res = calc(i, scores[j][0]);
				if(res[0] != scores[j][1] || res[1] != scores[j][2]) {
					ok = false;
					break;
				}
			}
			if(ok) result++;
		}

		System.out.println(result);
	}

	// 비교용 숫자와 입력된 숫자를 계산하여 스트라이크와 볼 수를 반환
	public static int[] calc (int a, int b) {
		int[] now = String.valueOf(a).chars().map(c -> c - '0').toArray();
		int[] num = String.valueOf(b).chars().map(c -> c - '0').toArray();

		int strike = 0;
		int ball = 0;

		for(int i = 0;i<3;i++) {
			if(now[i] == num[i]) {
				strike++;
			}
		}

		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				if(i != j && now[i] == num[j]) {
					ball++;
				}
			}
		}

		int[] answer = new int[]{strike, ball};

		return answer;
	}
}
