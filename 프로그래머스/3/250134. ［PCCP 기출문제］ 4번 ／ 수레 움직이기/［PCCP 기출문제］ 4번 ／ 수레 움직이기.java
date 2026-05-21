class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int[][] maze;
    static boolean[][] redVisited;
    static boolean[][] blueVisited;

    static int sizeR;
    static int sizeC;
    static int answer;

    static Pos redStart;
    static Pos blueStart;
    static Pos redDest;
    static Pos blueDest;

    public int solution(int[][] maze) {
        Solution.maze = maze;
        sizeR = maze.length;
        sizeC = maze[0].length;

        redVisited = new boolean[sizeR][sizeC];
        blueVisited = new boolean[sizeR][sizeC];

        answer = Integer.MAX_VALUE;

        for (int r = 0; r < sizeR; r++) {
            for (int c = 0; c < sizeC; c++) {
                if (maze[r][c] == 1) {
                    redStart = new Pos(r, c);
                } else if (maze[r][c] == 2) {
                    blueStart = new Pos(r, c);
                } else if (maze[r][c] == 3) {
                    redDest = new Pos(r, c);
                } else if (maze[r][c] == 4) {
                    blueDest = new Pos(r, c);
                }
            }
        }

        redVisited[redStart.r][redStart.c] = true;
        blueVisited[blueStart.r][blueStart.c] = true;

        dfs(
            redStart.r,
            redStart.c,
            blueStart.r,
            blueStart.c,
            0
        );

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    static void dfs(int rr, int rc, int br, int bc, int time) {
        if (time >= answer) return;

        boolean redArrived = rr == redDest.r && rc == redDest.c;
        boolean blueArrived = br == blueDest.r && bc == blueDest.c;

        if (redArrived && blueArrived) {
            answer = Math.min(answer, time);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nrr = rr;
            int nrc = rc;

            if (!redArrived) {
                nrr = rr + dr[i];
                nrc = rc + dc[i];

                if (!isValid(nrr, nrc)) continue;
                if (redVisited[nrr][nrc]) continue;
            }

            for (int j = 0; j < 4; j++) {
                int nbr = br;
                int nbc = bc;

                if (!blueArrived) {
                    nbr = br + dr[j];
                    nbc = bc + dc[j];

                    if (!isValid(nbr, nbc)) continue;
                    if (blueVisited[nbr][nbc]) continue;
                }

                // 이동 후 같은 칸에 있으면 안 됨
                if (nrr == nbr && nrc == nbc) continue;

                // 서로 위치를 바꾸는 이동이면 안 됨
                if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;

                if (!redArrived) {
                    redVisited[nrr][nrc] = true;
                }

                if (!blueArrived) {
                    blueVisited[nbr][nbc] = true;
                }

                dfs(nrr, nrc, nbr, nbc, time + 1);

                if (!redArrived) {
                    redVisited[nrr][nrc] = false;
                }

                if (!blueArrived) {
                    blueVisited[nbr][nbc] = false;
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        if (r < 0 || c < 0 || r >= sizeR || c >= sizeC) return false;
        if (maze[r][c] == 5) return false;
        return true;
    }

    static class Pos {
        int r;
        int c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}