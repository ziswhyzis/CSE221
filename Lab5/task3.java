package lab5;

import java.util.*;
import java.io.*;

public class task3 {

    static void bfs(List<List<Integer>> graph, int S, List<Integer> par, List<Integer> dist) {
        Queue<Integer> q = new LinkedList<>();

        dist.set(S, 0);
        q.add(S);

        while (!q.isEmpty()) {
            int node = q.poll();

            List<Integer> adj = graph.get(node);
            for (int i = 0; i < adj.size(); i++) {
                int neighbor = adj.get(i);

                if (dist.get(neighbor) == Integer.MAX_VALUE) {
                    par.set(neighbor, node);
                    dist.set(neighbor, dist.get(node) + 1);
                    q.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        int[] u = new int[M];
        int[] v = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            graph.get(u[i]).add(v[i]);
            graph.get(v[i]).add(u[i]);
        }

        for (int i = 1; i <= N; i++) Collections.sort(graph.get(i));

        List<Integer> par = new ArrayList<>(Collections.nCopies(N + 1, -1));
        List<Integer> dist = new ArrayList<>(Collections.nCopies(N + 1, Integer.MAX_VALUE));

        bfs(graph, S, par, dist);

        if (dist.get(D) == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int cur = D;
        while (cur != -1) {
            path.add(cur);
            cur = par.get(cur);
        }

        Collections.reverse(path);

        System.out.println(path.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i + 1 < path.size()) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}