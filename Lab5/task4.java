package lab5;

import java.io.*;
import java.util.*;

public class task4 {

    static void bfs(List<List<Integer>> graph, int src, int[] par, int[] dist, int n) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(par, -1);

        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.add(src);

        while (!q.isEmpty()) {
            int node = q.poll();

            List<Integer> adj = graph.get(node);
            for (int i = 0; i < adj.size(); i++) {
                int nxt = adj.get(i);

                if (dist[nxt] == Integer.MAX_VALUE) {
                    dist[nxt] = dist[node] + 1;
                    par[nxt] = node;
                    q.add(nxt);
                }
            }
        }
    }

    static List<Integer> buildPath(int[] par, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int cur = end;

        while (cur != -1) {
            path.add(cur);
            if (cur == start) {break;}
            cur = par[cur];
        }

        if (path.get(path.size() - 1) != start) {return null;}

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }

        int[] par1 = new int[N + 1];
        int[] par2 = new int[N + 1];
        int[] dist1 = new int[N + 1];
        int[] dist2 = new int[N + 1];

        bfs(graph, S, par1, dist1, N);
        bfs(graph, K, par2, dist2, N);

        if (dist1[K] == Integer.MAX_VALUE || dist2[D] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        List<Integer> p1 = buildPath(par1, S, K);
        List<Integer> p2 = buildPath(par2, K, D);

        if (p1 == null || p2 == null) {
            System.out.println(-1);
            return;
        }

        p2.remove(0);

        List<Integer> finalPath = new ArrayList<>(p1);
        finalPath.addAll(p2);

        System.out.println(finalPath.size() - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < finalPath.size(); i++) {
            sb.append(finalPath.get(i));
            if (i + 1 < finalPath.size()) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
