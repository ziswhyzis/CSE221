import java.util.*;
import java.io.*;

public class dum4 {

    public static int bfs(ArrayList<ArrayList<Integer>> graph, int s, int[] dist) {
        int farthest = s;
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        dist[s] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);

                    if (dist[v] > dist[farthest]) {
                        farthest = v;
                    }
                }
            }
        }
        return farthest;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for (int i = 0; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(v);
        }

        int dist[] = new int[N + 1];

        int first = bfs(graph, 1, dist);
        int second = bfs(graph, first, dist);

        System.out.println(dist[second]);
        System.out.println(first + " " + second);
    }
}
