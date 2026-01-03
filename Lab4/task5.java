package lab4;

import java.io.*;
import java.util.*;

public class task5 {

    static class Edge {
        int src;
        int dst;

        Edge(int src, int dst) {
            this.src = src;
            this.dst = dst;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indeg = new int[N + 1];
        int[] outdeg = new int[N + 1];

        int[] u = new int[M];
        int[] v = new int[M];

        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());
        }

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int from = u[i];
            int to = v[i];
            graph[from].add(new Edge(from, to));
            outdeg[from]++;
            indeg[to]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(indeg[i] - outdeg[i]);
            if (i < N) sb.append(' ');
        }

        System.out.println(sb.toString());
    }
}
