package lab8;

import java.util.*;
import java.io.*;

public class task2 {

    static int par[];
    static int rank[];

    public static void init(int N) {
        par = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.fill(rank, 0);
    }

    public static int find(int x) {
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    public static void union(int a, int b) {
        int parA = find(a), parB = find(b);

        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            rank[parA]++;
        } else if (rank[parA] > rank[parB]) {
            par[parB] = parA;
        } else {
            par[parA] = parB;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static long MST(ArrayList<Edge> edges, int N) {
        Collections.sort(edges);

        long totalCost = 0;
        int edgesCount = 0;

        for (Edge e : edges) {
            int parA = find(e.from);
            int parB = find(e.to);

            if (parA != parB) {
                union(e.from, e.to);
                totalCost += e.cost;
                edgesCount++;
            }

            if (edgesCount == N - 1) break;
        }

        return totalCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        System.out.println(MST(edges, N));
    }
}