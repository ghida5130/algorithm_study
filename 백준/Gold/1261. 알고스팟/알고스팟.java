import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeC = Integer.parseInt(st.nextToken());
        int sizeR = Integer.parseInt(st.nextToken());
        int[][] board = new int[sizeR][sizeC];
        
        for(int i = 0;i<sizeR;i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
        }

        int[][] dijk = new int[sizeR][sizeC];
        for(int i = 0;i<sizeR;i++) {
            Arrays.fill(dijk[i], Integer.MAX_VALUE);
        }
        dijk[0][0] = 0;

        PriorityQueue<pos> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.add(new pos(0, 0, 0));
        while(!pq.isEmpty()) {
            pos now = pq.poll();

            if(now.w != dijk[now.r][now.c]) continue;

            for(int i = 0;i<4;i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                if(nextR < 0 || nextC < 0 || nextR >= sizeR || nextC >= sizeC) continue;
                int nextW = now.w + board[nextR][nextC];
                if(nextW >= dijk[nextR][nextC]) continue;
                dijk[nextR][nextC] = Math.min(dijk[nextR][nextC], nextW);
                pq.add(new pos(nextR, nextC, nextW));
            }
        }

        System.out.println(dijk[sizeR - 1][sizeC - 1]);
    }

    static class pos {
        int r;
        int c;
        int w;
        pos(int r, int c, int w){
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}