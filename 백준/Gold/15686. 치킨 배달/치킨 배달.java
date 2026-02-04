import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static int nowChickenCount = 0;								// 현재 배치된 치킨집의 수
	public static ArrayDeque<int[]> nowChickenPos = new ArrayDeque<>();	// 현재 배치된 치킨집의 좌표
	public static int maxChicken;										// 최대 치킨집의 수
	public static int[][] chickenCandidate;								// 치킨집이 있을 수 있는 위치 리스트
	public static ArrayDeque<int[]> housePos = new ArrayDeque<>();		// 집 위치 배열
	public static int min = Integer.MAX_VALUE;							// 최소 치킨거리
	public static int size;												// 지도 크기 (가로 or 세로)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer info = new StringTokenizer(br.readLine());
		size = Integer.parseInt(info.nextToken());
		maxChicken = Integer.parseInt(info.nextToken());					
		ArrayDeque<int[]> chickenTemp = new ArrayDeque<>();

		for(int i = 0;i<size;i++) {
			StringTokenizer boardLine = new StringTokenizer(br.readLine());
			for(int j = 0;j<size;j++) {
				int nowValue = Integer.parseInt(boardLine.nextToken());
				if(nowValue == 1) {
					housePos.push(new int[]{i, j});
				} else if (nowValue == 2) {
					chickenTemp.add(new int[]{i, j});
				}
			}
		}

		chickenCandidate = chickenTemp.toArray(new int[0][]);

		dfs(0);

		System.out.println(min);
	}

	// dfs로 조합하여 배열값을 변경
	public static void dfs(int idx) {
		// 치킨집이 원하는 개수만큼 배치됐다면, 거리 계산을 시작한다.
		// 계산을 끝낸 후 리턴한다. 백트래킹은 이전 로직에서 처리된다.
		if(nowChickenCount == maxChicken) {
			calc();
			return;
		}

		// 치킨집 개수가 부족할 경우 반복문을 통해 치킨집을 순차적으로 추가한다. 반복문의 시작점은 전달인자로 받는다.
		// nowChickenPos에 치킨집 위치를 좌표값으로 저장한다.
		// 추가한뒤 calc를 호출한다 (재귀)
		// 호출이 종료된 후 백트래킹
		for(int i = idx;i<chickenCandidate.length;i++) {
			int nowR = chickenCandidate[i][0];
			int nowC = chickenCandidate[i][1];
			nowChickenPos.push(new int[]{nowR, nowC});
			nowChickenCount++;
			dfs(i + 1);
			nowChickenPos.pop();
			nowChickenCount--;
		}
	}

	public static void calc() {
		// 도시의 치킨거리 = 모든 집의 치킨거리의 합
		// 치킨거리 = 특정 집에서 최소거리에 있는 치킨집
		// 집 좌표목록을 순회하며 치킨 거리를 계산한다.
		// 현재 집 좌표와 저장된 치킨집 좌표를 거리계산하여 가장 작은값을 sum에 더한다.
		// 모든 연산이 종료된뒤 min과 sum을 비교하여 min을 갱신한다.

		int sum = 0;

		for(int[] house : housePos) {
			int houseR = house[0];
			int houseC = house[1];
			int minDistance = Integer.MAX_VALUE;
			for(int[] pos : nowChickenPos) {
				int chickenR = pos[0];
				int chickenC = pos[1];
				int distance = Math.abs(chickenR - houseR) + Math.abs(chickenC - houseC);
				minDistance = Math.min(minDistance, distance);
			}
			sum += minDistance;
		}

		min = Math.min(min, sum);
	}
}
