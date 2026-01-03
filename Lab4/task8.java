package lab4;

import java.io.*;
import java.util.*;

public class task8 {

    static class Edge {
        int src;
        int dst;

        public Edge(int src, int dst) {
            this.src = src;
            this.dst = dst;
        }
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int x = 1; x <= N; x++) {
            for (int i = 1; i <= N; i++) {
                if (i != x && gcd(i, x) == 1) {
                    graph[x].add(new Edge(x, i));
                }
            }
            graph[x].sort((a, b) -> a.dst - b.dst);
        }

        StringBuilder sb = new StringBuilder();

        for (int qi = 0; qi < Q; qi++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (K <= graph[X].size()) {
                sb.append(graph[X].get(K - 1).dst).append('\n');
            } else {
                sb.append("-1\n");
            }
        }

        System.out.print(sb.toString());
    }
}