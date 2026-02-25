import java.io.*;
import java.util.*;

public class Main {
    // x+y+z=k 에서 x+y=k-z 로 풀이했다.
    public static void main(String[] args) throws IOException {
        // 한걸음 : 나가기만 할 수 있음
        // 로마 : 들어오기만 할 수 있음

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 도시의 개수
        int M = Integer.parseInt(br.readLine());    // 간선의 개수

        ArrayList<Edge>[] Nodes = new ArrayList[N + 1];   // 각 노드가 갈 수 있는 노드
        ArrayList<Edge>[] reverseNodes = new ArrayList[N + 1];
        int[] dp = new int[N + 1];  // 각 도시에 도착하는 최장거리 DP
        int[] inDegree = new int[N + 1];    // 각 노드 이전에 지나와야하는 노드 수
        for(int i = 0;i<N+1;i++) dp[i] = -1;

        for(int i = 0;i<=N;i++) Nodes[i] = new ArrayList<>();
        for(int i = 0;i<=N;i++) reverseNodes[i] = new ArrayList<>();

        for(int i = 0;i<M;i++) {
            StringTokenizer row = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(row.nextToken());
            int b = Integer.parseInt(row.nextToken());
            int t = Integer.parseInt(row.nextToken());

            Nodes[a].add(new Edge(b, t));
            reverseNodes[b].add(new Edge(a, t));
            inDegree[b]++;
        }

        StringTokenizer temp = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(temp.nextToken());
        int end = Integer.parseInt(temp.nextToken());
        dp[start] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 1;i<N+1;i++) {
            if(inDegree[i] == 0) queue.addLast(i);
        }

        // 최장경로 구하기
        while(!queue.isEmpty()) {
            int nowNode = queue.pollFirst();
            int nowNodeMaxTime = dp[nowNode];   // 현재 노드까지의 최대 시간
            ArrayList<Edge> destinationNodes = Nodes[nowNode];  // 현재 연산하는 노드의 목적지들
            // System.out.printf("nowNode : %d%n", nowNode);

            for(int i = 0;i<destinationNodes.size();i++) {
                Edge nextEdge = destinationNodes.get(i);    // 현재 연산하는 다음 목적지의 Edge
                int nextNode = nextEdge.endNode;            // 다음 노드 번호
                int spendTime = nextEdge.time;               // 다음 노드까지 걸리는 시간

                if(dp[nowNode] != -1) {
                    // 다음 노드에 대해 dp 연산
                    dp[nextNode] = Math.max(dp[nextNode], nowNodeMaxTime + spendTime);

                    // System.out.printf("nextNode : %d%n", nextNode);
                    // System.out.printf("nextMaxTime : %d%n", dp[nextNode]);
                }

                // 위상정렬로 queue에 다음 연산할 Node 집어넣기
                inDegree[nextNode]--;
                if(inDegree[nextNode] == 0) {
                    queue.addLast(nextNode);
                }

            }
        }

        boolean[] visited = new boolean[N + 1];

        ArrayDeque<Integer> reverseQueue = new ArrayDeque<>();
        reverseQueue.addLast(end);
        visited[end] = true;
        int resEdges = 0;

        // 역방향 그래프로 간선수 계산
        while(!reverseQueue.isEmpty()) {
            int nowNode = reverseQueue.pollFirst();
            ArrayList<Edge> nextNodes = reverseNodes[nowNode];

            for(int i = 0;i<nextNodes.size();i++) {
                int nextNode = nextNodes.get(i).endNode;    // 역방향의 목적지 노드
                int spendTime = nextNodes.get(i).time;      // 역방향의 노드까지의 소요시간

                if(dp[nextNode] != -1 && dp[nowNode] != -1 && dp[nowNode] - spendTime == dp[nextNode]) {
                    resEdges++;
                    if(!visited[nextNode]) {
                        reverseQueue.addLast(nextNode);
                        visited[nextNode] = true;
                    }
                }
                // 현재노드까지의 최대시간에서 역방향그래프의 시간을 뺐을때 역방향노드의 최대시간과 같아야한다.
            }
        }
        
        System.out.println(dp[end]);    // 최장거리 출력
        System.out.println(resEdges);
        
    }

    static class Edge {
        int endNode;
        int time;

        Edge(){}
        Edge(int endNode, int time) {
            this.endNode = endNode;
            this.time = time;
        }
    }
}