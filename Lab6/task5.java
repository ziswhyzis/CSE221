package lab6;

import java.io.*;
import java.util.*;

public class task5 {

    static class Edge {
        int src, dst;
        Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    static int[] BFS(int N, ArrayList<ArrayList<Edge>> graph, int[] src) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int s : src) {
            dist[s] = 0;
            q.add(s);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph.get(u)) {
                int v = e.dst;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(u, v));
            graph.get(v).add(new Edge(v, u));
        }

        st = new StringTokenizer(br.readLine());
        int[] src = new int[S];
        for (int i = 0; i < S; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] dest = new int[Q];
        for (int i = 0; i < Q; i++) {
            dest[i] = Integer.parseInt(st.nextToken());
        }

        int[] dist = BFS(N, graph, src);

        for (int i = 0; i < Q; i++) {
            System.out.print(dist[dest[i]]);
            if (i + 1 < Q) System.out.print(" ");
        }
        System.out.println();
    }
}
