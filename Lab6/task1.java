import java.util.*;
import java.io.*;

public class task1 {

    static boolean hasCycle;

    static class Edge {
        int src, dst;

        Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    private static void topSort(int N, ArrayList<ArrayList<Edge>> graph) {
        boolean vis[] = new boolean[N + 1];
        boolean path[] = new boolean[N + 1];
        Stack<Integer> st = new Stack<>();

        for (int i = 1; i <= N; i++) {
            if (!vis[i]) {
                dfs(i, vis, path, st, graph);
            }
        }

        if (hasCycle) {
            System.out.println(-1);
            return;
        }

        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }

    private static void dfs(int i, boolean[] vis, boolean[] path, Stack<Integer> st, ArrayList<ArrayList<Edge>> graph) {
        vis[i] = true;
        path[i] = true;
        for (Edge e : graph.get(i)) {
            int nxt = e.dst;

            if (path[nxt]) {
                hasCycle = true; // since no valid topsort means not DAG, so its only when cycle exist. DCG
                return;
            }
            if (!vis[nxt]) {
                dfs(nxt, vis, path, st, graph);
            }
        }
        path[i] = false;
        st.push(i);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Edge(A, B));
        }

        topSort(N, graph);
    }
}
