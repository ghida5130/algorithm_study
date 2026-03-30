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
            // 1. 각 파이어볼을 순차적으로 이동한다. 이때 count 배열에 값을 누적한다.
            int[][] count = new int[N][N];
            for(Fireball f : fireballs) {
                int nextR = f.r;
                int nextC = f.c;
                for(int j = 0;j<f.speed;j++) {
                    nextR = nextR + dr[f.dir];
                    nextC = nextC + dc[f.dir];

                    // 격자를 빠져나간경우 반대편으로 이동
                    if(nextR < 0) nextR = N-1;
                    if(nextC < 0) nextC = N-1;
                    if(nextR >= N) nextR = 0;
                    if(nextC >= N) nextC = 0;
                }

                f.r = nextR;
                f.c = nextC;
                count[f.r][f.c]++;
            }

            // 2. count 배열에서 2이상인 위치에 대하여 연산한다.
            for(int i = 0;i<N;i++) {
                for(int j = 0;j<N;j++) {
                    if(count[i][j] > 1) {
                        int sumW = 0;
                        int sumS = 0;
                        int total = 0;
                        boolean isFirst = true;
                        boolean isFirstOdd = false;
                        boolean isParity = true;
                        Stack<Integer> tempList = new Stack<>();

                        for(int k = 0;k<fireballs.size();k++) {
                            Fireball f = fireballs.get(k);
                            if(f.r == i && f.c == j) {
                                total++;
                                tempList.push(k);
                                sumW += f.w;
                                sumS += f.speed;
                                if(isFirst) {
                                    isFirst = false;
                                    if(f.dir % 2 != 0) isFirstOdd = true;
                                } else if (isParity == true) {
                                    if(f.dir % 2 != 0 && isFirstOdd == false) isParity = false;
                                    if(f.dir % 2 == 0 && isFirstOdd == true) isParity = false;
                                }
                            }
                        }

                        sumW /= 5;
                        sumS /= total;

                        while(!tempList.isEmpty()) {
                            int nowIdx = tempList.pop();
                            fireballs.remove(nowIdx);
                        }

                        if(sumW != 0) {
                            for(int k = 0;k<8;k+=2) {
                                int dir = k;
                                if(!isParity) dir+=1;
                                fireballs.add(new Fireball(i, j, sumW, sumS, dir));
                            }
                        }
                    }
                }
            }
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