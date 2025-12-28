import java.io.*;
import java.util.*;

public class task1 {

    static void BFS(ArrayList<Integer>[] graph, int N) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[N + 1];

        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.remove();

            if (!vis[cur]) {
                System.out.print(cur + " ");
                vis[cur] = true;

                for (int i = 0; i < graph[cur].size(); i++) {
                    int nxt = graph[cur].get(i);
                    q.add(nxt);
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

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        BFS(graph, N);
    }
}