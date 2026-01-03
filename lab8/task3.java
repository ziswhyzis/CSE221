package lab8;

import java.io.*;
import java.util.*;

public class task3 {
    
    static ArrayList<ArrayList<Edge>> graph;

    static class Edge implements Comparable<Edge> {
        int from, to, cost;
        int index;

        Edge(int f, int t, int c, int i) {
            this.from = f;
            this.to = t;
            this.cost = c;
            this.index = i;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY)
                return false;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    static long kruskal(int n, List<Edge> edges, boolean[] inMST, List<Edge> mstEdges) {
        DSU dsu = new DSU(n);
        long mstCost = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.from, edge.to)) {
                inMST[edge.index] = true;
                mstEdges.add(edge);
                mstCost += edge.cost;
                if (mstEdges.size() == n - 1)
                    break;
            }
        }

        return mstCost;
    }

    static boolean findPath(int curr, int target, int parent, ArrayList<Integer> pathWeights) {
        if (curr == target)
            return true;

        for (Edge edge : graph.get(curr)) {
            int next = (edge.from == curr) ? edge.to : edge.from;
            if (next == parent)
                continue;

            pathWeights.add(edge.cost);
            if (findPath(next, target, curr, pathWeights))
                return true;
            pathWeights.remove(pathWeights.size() - 1);
        }
        return false;
    }

    static long findSecondBestMST(int n, List<Edge> allEdges, boolean[] inMST, long minMstCost) {
        long secondBest = Long.MAX_VALUE;

        for (Edge nonMSTEdge : allEdges) {
            if (inMST[nonMSTEdge.index])
                continue;

            ArrayList<Integer> pathWeights = new ArrayList<>();
            findPath(nonMSTEdge.from, nonMSTEdge.to, -1, pathWeights);

            for (int mstEdgeWeight : pathWeights) {
                if (nonMSTEdge.cost > mstEdgeWeight) {
                    long newCost = minMstCost - mstEdgeWeight + nonMSTEdge.cost;
                    secondBest = Math.min(secondBest, newCost);
                }
            }
        }

        return secondBest;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, weight, i));
        }

        Collections.sort(edges);

        boolean[] inMST = new boolean[m];
        List<Edge> mstEdges = new ArrayList<>();
        long minMstCost = kruskal(n, edges, inMST, mstEdges);

        if (mstEdges.size() != n - 1) {
            System.out.println(-1);
            return;
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (Edge e : mstEdges) {
            graph.get(e.from).add(e);
            graph.get(e.to).add(e);
        }

        long secondBest = findSecondBestMST(n, edges, inMST, minMstCost);

        if (secondBest == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(secondBest);
        }
    }
}