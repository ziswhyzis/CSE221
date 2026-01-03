package lab5;

import java.io.*;
import java.util.*;

public class task7 {

    static int R, C;
    static char[][] grid;
    static int[][] vis;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    private static int bfs(int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sr, sc });
        vis[sr][sc] = 1;

        int diamonds = 0;

        if (grid[sr][sc] == 'D') {
            diamonds++;
        }

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (grid[nr][nc] == '#') {
                    continue;
                }
                if (vis[nr][nc] == 1) {
                    continue;
                }

                vis[nr][nc] = 1;
                q.add(new int[] { nr, nc });

                if (grid[nr][nc] == 'D') {
                    diamonds++;
                }
            }
        }
        return diamonds;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        vis = new int[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        int answer = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (grid[i][j] != '#' && vis[i][j] == 0) {
                    int collected = bfs(i, j);
                    if (collected > answer)
                        answer = collected;
                }

            }
        }
        System.out.println(answer);
    }
}
