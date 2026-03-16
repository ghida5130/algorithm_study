import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        // N = 마을의 개수
        // road = 연결 간선 목록
        // K = 최대배달시간

        // 다익스트라 배열
        int[] minDist = new int[N+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;
        
        PriorityQueue<pos> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new pos(1, 0));

        List<pos>[] graph = new ArrayList[N+1];
        for(int i = 0;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<road.length;i++) {
            int start = road[i][0];
            int end = road[i][1];
            int weight = road[i][2];
            graph[start].add(new pos(end, weight));
            graph[end].add(new pos(start, weight));
        }

        while(!pq.isEmpty()) {
            pos now = pq.poll();

            if(minDist[now.end] != now.weight) continue;

            for(pos i : graph[now.end]) {
                int nextW = now.weight + i.weight;
                if(nextW < minDist[i.end]) {
                    minDist[i.end] = nextW;
                    pq.offer(new pos(i.end, nextW));
                }
            }
        }

        int answer = 0;
        for(int i : minDist) {
            if(i <= K) answer++;
        }
        
        return answer;
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

