import java.util.*;
import java.io.*;

public class task3 {

    static class State {
        int x, y, dist;
        State(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, x1, y1, x2, y2));
    }

    static int bfs(int N, int sx, int sy, int tx, int ty) {

        if (sx == tx && sy == ty) return 0; //already in the target

        int[] dx = { 2, 2, -2, -2, 1, 1, -1, -1 };
        int[] dy = { 1, -1, 1, -1, 2, -2, 2, -2 };

        boolean[][] vis = new boolean[N + 1][N + 1];
        Queue<State> q = new LinkedList<>();

        q.add(new State(sx, sy, 0));
        vis[sx][sy] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && !vis[nx][ny]) {
                    if (nx == tx && ny == ty)
                        return cur.dist + 1;

                    vis[nx][ny] = true;
                    q.add(new State(nx, ny, cur.dist + 1));
                }
            }
        }

        return -1;
    }
}
