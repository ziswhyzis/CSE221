import java.util.*;
import java.io.*;

public class task1 {
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

    public static int[] dijkstra(int N, int S, ArrayList<ArrayList<Edge>> graph, int[] par) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(par, -1);
        dist[S] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Pair(S, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;
            if (cur.dist > dist[u]) continue;
            for (Edge e : graph.get(u)) {
                int v = e.to, w = e.wt;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    par[v] = u;
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
        int D = Integer.parseInt(st.nextToken());

        int[] u = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) u[i] = Integer.parseInt(st.nextToken());

        int[] v = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) v[i] = Integer.parseInt(st.nextToken());

        int[] w = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) w[i] = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= M; i++) graph.get(u[i]).add(new Edge(v[i], w[i]));

        int[] par = new int[N + 1];
        int[] dist = dijkstra(N, S, graph, par);

        if (dist[D] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int node = D;
        while (node != -1) {
            path.add(node);
            node = par[node];
        }
        Collections.reverse(path);

        System.out.println(dist[D]);
        for (int i : path) System.out.print(i + " ");
    }
}