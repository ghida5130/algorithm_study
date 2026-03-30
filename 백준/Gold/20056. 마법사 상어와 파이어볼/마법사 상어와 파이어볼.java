import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1,-1,0,1,1,1,0,-1};
    static int[] dc = {0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 격자 크기
        int M = Integer.parseInt(st.nextToken());   // 파이어볼 개수
        int K = Integer.parseInt(st.nextToken());   // 이동횟수

        List<Fireball> fireballs = new ArrayList<>();

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        for(int z = 0;z<K;z++) {
            // 1. 파이어볼 이동후의 개수를 확인하기위한 count 이차원 배열
            List<Fireball>[][] board = new ArrayList[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    board[i][j] = new ArrayList<>();
                }
            }
            
            // 2. 각 파이어볼 이동하여 board에 추가
            for(Fireball f : fireballs) {
                int move = f.speed % N;
                int nextR = (f.r + dr[f.dir] * move) % N;
                int nextC = (f.c + dc[f.dir] * move) % N;

                if(nextR < 0) nextR += N;
                if(nextC < 0) nextC += N;

                board[nextR][nextC].add(new Fireball(nextR, nextC, f.w, f.speed, f.dir));
            }

            // 2. count 배열에서 2이상인 위치에 대하여 연산한다.
            List<Fireball> nextFireballs = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int size = board[i][j].size();

                    // 해당 칸에 하나도 없을 경우에 continue
                    if(size == 0) continue;

                    // 해당 칸에 한개만 있을경우 새 리스트에 추가하고 continue
                    if(size == 1) {
                        nextFireballs.add(board[i][j].get(0));
                        continue;
                    }

                    // 2개 이상일 경우 연산 수행
                    int sumW = 0;
                    int sumS = 0;
                    boolean allEven = true;
                    boolean allOdd = true;

                    for(Fireball f : board[i][j]) {
                        sumW += f.w;
                        sumS += f.speed;

                        if(f.dir % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    // 합친 질량을 계산하고 질량이 0일경우 추가를 생략
                    int newW = sumW / 5;
                    if(newW == 0) continue;
                    int newS = sumS / size;
                    int startDir = (allEven || allOdd) ? 0 : 1;
                    for(int d = startDir; d < 8; d += 2) {
                        nextFireballs.add(new Fireball(i, j, newW, newS, d));
                    }
                }
            }

            // 새 리스트로 갱신
            fireballs = nextFireballs;
        }

        int answer = 0;
        for(Fireball f : fireballs) {
            answer += f.w;
        }

        System.out.println(answer);
    }

    static class Fireball {
        int r;
        int c;
        int w;
        int speed;
        int dir;
        public Fireball(int r, int c, int w, int speed, int dir) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.speed = speed;
            this.dir = dir;
        }
    }
}