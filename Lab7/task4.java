import java.util.*;
import java.io.*;

public class task4 {

    static class Pair {
        int node, dist;
        Pair(int n, int d) {
            node = n;
            dist = d;
        }
    }

    public static int dijkstra(ArrayList<ArrayList<Integer>> graph, int N, int S, int D, int[] w) {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, Integer. MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist[S] = w[S];
        pq.add(new Pair(S, dist[S]));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;

            if (cur.dist > dist[u]) continue;
            if (u == D) return dist[u];

            for(int v : graph.get(u)) {
                if (dist[u] + w[v] < dist[v]) {
                    dist[v] = dist[u] + w[v];
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        return dist[D] == Integer.MAX_VALUE ? -1 : dist[D];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] w = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) w[i] = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }
        System.out.println(dijkstra(graph, N, S, D, w));
    }
}
