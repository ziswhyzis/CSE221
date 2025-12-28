import java.io.*;
import java.util.*;

public class task3 {

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

    static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist[1] = 0;
        pq.add(new Pair(1, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;

            if (cur.dist > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.wt;

                int dang = Math.max(dist[u], w);

                if (dang < dist[v]) {
                    dist[v] = dang;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer((br.readLine()));
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        int[] dist = dijkstra(graph, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("-1 ");
            else sb.append(dist[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}