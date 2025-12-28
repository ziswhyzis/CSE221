import java.util.*;
import java.io.*;

public class dum5 {

    static class Edge {
        int to, wt;
        Edge (int to, int wt) {
            this.to = to;
            this.wt = wt;
        }
    }

    static class Node {
        int node, dist, pairity;
        Node (int n, int d, int p) {
            node = n;
            dist = d;
            pairity = p;
        }
    }

    public static int dijkstra (int N, ArrayList<ArrayList<Edge>> graph) {
        int dist[][] = new int[N + 1][2];
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[1][0] = 0; dist [1][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new Node(1, 0, 0));
        pq.add(new Node(1, 0, 1));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.node;
            if (cur.dist > dist[u][cur.pairity]);
            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.wt;
                int nextParity = w % 2;
                if (nextParity == cur.pairity) continue;

                int newDist = cur.dist + w;
                if (newDist < dist[v][nextParity]) {
                    dist[v][nextParity] = newDist;
                    pq.add(new Node(v, newDist, nextParity));
                }
            }
        }
        int output = Math.min(dist[N][0], dist[N][1]);
        return output == Integer.MAX_VALUE ? -1 : output;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] u = new int[M];
        int[] v = new int[M];
        int[] w = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) w[i] = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) graph.get(u[i]).add(new Edge(v[i], w[i]));

        System.out.println(dijkstra(N, graph));
    }
}