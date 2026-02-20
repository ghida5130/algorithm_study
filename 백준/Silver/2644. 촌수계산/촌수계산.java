import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine().trim());
		List<Integer>[] people = new ArrayList[total+1];
		int[] visited = new int[total+1];
		for(int i = 0;i<=total;i++) people[i] = new ArrayList<>();
		int answer = -1;

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int edgeCount = Integer.parseInt(br.readLine());

		for(int i = 0;i<edgeCount;i++) {
			StringTokenizer nodes = new StringTokenizer(br.readLine().trim());
			int X = Integer.parseInt(nodes.nextToken());
			int Y = Integer.parseInt(nodes.nextToken());
			people[X].add(Y);
			people[Y].add(X);
		}

		ArrayDeque<Person> queue = new ArrayDeque<>();
		queue.addLast(new Person(A, 0));
		visited[A] = 1;

		while(!queue.isEmpty()) {
			Person nowVal = queue.pollFirst();
			int nowNum = nowVal.num;
			int nowDepth = nowVal.depth;

			if(nowNum == B) {
				answer = nowDepth;
				break;
			}

			for(int i : people[nowNum]) {
				if(visited[i] == 0) {
					visited[i] = 1;
					queue.addLast(new Person(i, nowDepth+1));
				}
			}
		}

		System.out.println(answer);
	}

	static class Person {
		public int num;
		public int depth;

		Person(){}
		Person(int num, int depth){
			this.num = num;
			this.depth = depth;
		}
	}
}