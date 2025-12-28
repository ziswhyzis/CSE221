import java.io.*;
import java.util.*;

public class task2 {

    public static void DFS(ArrayList<Integer>[] graph, int cur, boolean vis[]) {
        Stack<Integer> st = new Stack<>();
        st.push(cur);

        while (!st.isEmpty()) {
            int node = st.pop();
            if (vis[node]) continue;

            System.out.print(node + " ");
            vis[node] = true;

            for (int i = 0; i < graph[node].size(); i++) {
            int nxt = graph[node].get(i);
            if (!vis[nxt]) {
                st.push(nxt);
            }
        }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int[] u = new int[M];
        for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] v = new int[M];
        for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            graph[u[i]].add(v[i]);
            graph[v[i]].add(u[i]);
        }

        DFS(graph, 1, new boolean[N+1]);
    }
}