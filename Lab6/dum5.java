import java.util.*;
import java.io.*;

public class dum5 {

    public static int[] bfs(ArrayList<ArrayList<Integer>> graph, int N, int[] src) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int s : src) {
            q.add(s);
            dist[s] = 0;
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int src[] = new int[S];
        st = new StringTokenizer(st.nextToken());
        for (int i = 0; i < S; i++) src[i] = Integer.parseInt(st.nextToken());

        int dest[] = new int[Q];
        st = new StringTokenizer(st.nextToken());
        for (int i = 0; i < Q; i++) dest[i] = Integer.parseInt(st.nextToken());

        int dist[] = bfs(graph, N, src);
        
        for (int i = 0; i < Q; i++) {
            System.out.print(dist[dest[i]] + " ");
        }
    }
}
