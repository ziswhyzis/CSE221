package lab7;

import java.io.*;
import java.util.*;

public class task6 {

    static class Edge {
        int to;
        int wt;
        Edge(int t, int w) {
            to = t;
            wt = w;
        }
    }
    static class Pair {
        int node;
        int dist;
        Pair(int n, int d) {
            node = n;
            dist = d;
        }
    }

    static int wthdijkstra(ArrayList<ArrayList<Edge>> graph, int N, int S, int D) {

        int[] dist1 = new int[N + 1];
        int[] dist2 = new int[N + 1];

        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist1[S] = 0;
        pq.add(new Pair(S, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;
            int d = cur.dist;

            if (d > dist2[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = d + e.wt;

                if (newDist < dist1[v]) {
                    dist2[v] = dist1[v];
                    dist1[v] = newDist;
                    pq.add(new Pair(v, dist1[v]));
                }
                else if (newDist > dist1[v] && newDist < dist2[v]) {
                    dist2[v] = newDist;
                    pq.add(new Pair(v, dist2[v]));
                }
            }
        }

        return dist2[D] == Integer.MAX_VALUE ? -1 : dist2[D];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        
        System.out.println(wthdijkstra(graph, N, S, D));
    }
}
