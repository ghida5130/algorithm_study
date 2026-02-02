import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// bbb

		char[] userInput = br.readLine().toCharArray();
		int size = userInput.length;
		// 3
		int maxIdx = size - 1;
		// 2

		int max = 1;

		for(int i = maxIdx;i >= size/2;i--) { //2, 1을 순회
			int rightIndex = i, leftIndex = i;
			boolean pal = true;
			for(int j = 1;j<=maxIdx - i;j++) {
				if(userInput[rightIndex + j] != userInput[leftIndex -j]) {
					pal = false;
					break;
				}
			}
			if(pal) max = Math.max(max, (maxIdx-i)*2+1);
			

			if(i-1 >= 0) {
				rightIndex = i;
				leftIndex = i-1;
				pal = true;
				for(int j = 0;j<=maxIdx - i;j++) {
					if(leftIndex - j < 0) {
						pal = false;
						break;
					}
					if(userInput[rightIndex + j] != userInput[leftIndex -j]) {
						pal = false;
						break;
					}
				}
				if(pal) max = Math.max(max, (maxIdx-i)*2+2);
			}
		}
		System.out.println(size + (size-max));
	}
}
