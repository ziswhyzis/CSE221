package lab5;

import java.io.*;
import java.util.*;

public class task5 {
    static ArrayList<Integer>[] graph;
    static int[] subtree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        subtree = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(R);

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            int x = Integer.parseInt(br.readLine());
            sb.append(subtree[x]).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int dfs(int node) {
        visited[node] = true;
        subtree[node] = 1;

        for (int nxt : graph[node]) {
            if (!visited[nxt]) {
                subtree[node] += dfs(nxt);
            }
        }
        return subtree[node];
    }
}
