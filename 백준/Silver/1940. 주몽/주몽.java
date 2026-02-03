import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		int target = Integer.parseInt(br.readLine());
		String[] numsArr = br.readLine().split(" ");
		int[] nums = Arrays.stream(numsArr).mapToInt(Integer::valueOf).toArray();

		Arrays.sort(nums);

		int left = 0, right = total - 1;
		int sum = 0;
		int answer = 0;

		while(left < right){
			sum = nums[left] + nums[right];
			if(sum >= target) {
				if(sum == target) answer++;
				right--;
			} else {
				left++;
			}
		}

		System.out.println(answer);
	}
}