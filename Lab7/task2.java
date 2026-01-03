package lab7;

import java.util.*;
import java.io.*;

public class task2 {

    static class Edge {
        int to, wt;
        Edge(int t, int w) {
            to = t;
            wt = w;
        }
    }

    static class Pair {
        int node, dist;
        Pair(int n, int d) {
            node = n;
            dist = d;
        }
    }

    public static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int N, int S) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);


        dist[S] = 0;
        pq.add(new Pair(S, dist[S]));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;

            if (cur.dist > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.wt;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
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
        int T = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w));
        }

        int[] distA = dijkstra(graph, N, S);
        int[] distB = dijkstra(graph, N, T);

        int time = Integer.MAX_VALUE;
        int node = -1;

        for(int i = 1; i <= N; i++) {
            if (distA[i] == Integer.MAX_VALUE || distB[i] == Integer.MAX_VALUE) continue;
            int gettime = Math.max(distA[i], distB[i]);

            if (gettime < time || gettime == time && i < node) {
                time = gettime;
                node = i;
            }
        }
        if (node == -1) System.out.println(-1);
        else System.out.println(time + " " + node);
    }
}