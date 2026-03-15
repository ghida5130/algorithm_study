import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 노드 수
        int M = Integer.parseInt(br.readLine());    // 간선 수

        List<pos>[] graph = new ArrayList[N+1];
        for(int i = 1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[nodeA].add(new pos(nodeB, weight));
            graph[nodeB].add(new pos(nodeA, weight));
        }

        int[] visited = new int[N+1];
        PriorityQueue<pos> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new pos(1, 0));

        int totalWeight = 0;
        int totalEdges = 0;

        while(!pq.isEmpty()) {
            pos now = pq.poll();
            // now.weight = 현재 값까지의 가중치

            if(visited[now.end] == 1) continue;
            visited[now.end] = 1;
            totalWeight += now.weight;
            totalEdges++;

            if(totalEdges == N+1) break;
            
            for(pos p : graph[now.end]) {
                // p.end = 현재 값에서 갈수있는 목적지
                // p.weight = 현재 값에서 갈수있는 목적지 까지의 가중치
                if(visited[p.end] == 1) continue;
                pq.add(p);
            }
        }

        System.out.println(totalWeight);
        
    }

    static class pos {
        int end;
        int weight;
        pos(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}