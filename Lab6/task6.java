package lab6;

import java.io.*;
import java.util.*;

public class task6 {

    static int bfs(int s, int t) {
        if (s == t)
            return 0;
        if (s > t)
            return -1;

        boolean[] vis = new boolean[t + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        vis[s] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            for (int k = 0; k < size; k++) {
                int cur = q.poll();
                for (int i = 2; i * i <= cur; i++) {
                    if (cur % i == 0) {

                        if (isPrime(i)) {
                            int next = cur + i;
                            if (next == t) {
                                return steps;
                            }
                            if (next <= t && !vis[next]) {
                                vis[next] = true;
                                q.add(next);
                            }
                        }

                        int other = cur / i;
                        if (other != i && isPrime(other)) {
                            int next = cur + other;
                            if (next == t) {
                                return steps;
                            }
                            if (next <= t && !vis[next]) {
                                vis[next] = true;
                                q.add(next);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    static boolean isPrime(int x) {
        if (x < 2)
            return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            System.out.println(bfs(s, t));
        }
    }
}
