package lab6;

import java.io.*;
import java.util.*;

public class task4 {

    static class Edge {
        int src, dst;
        Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    static int bfs(int start, ArrayList<ArrayList<Edge>> graph, int[] dist) {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        int farthest = start;
        while (!q.isEmpty()) {
            int u = q.poll();

            for (Edge e : graph.get(u)) {
                int v = e.dst;
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);

                    if (dist[v] > dist[farthest]) {
                        farthest = v;
                    }
                }
            }
        }
        return farthest;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Edge(A, B));
            graph.get(B).add(new Edge(B, A));
        }

        int[] dist = new int[N + 1];

        int first = bfs(1, graph, dist);
        int second = bfs(first, graph, dist);

        System.out.println(dist[second]);
        System.out.println(first + " " + second);
    }
}
