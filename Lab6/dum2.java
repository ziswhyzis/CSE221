import java.util.*;
import java.io.*;

public class dum2 {

    public static int bipart(ArrayList<ArrayList<Integer>> graph, int src, int[] color) {
        int C1 = 0, C2 = 0;
        color[src] = 1;
        C1++;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (color[v] == 0) {
                    if (color[u] == 1) {
                        color[v] = 2;
                        C2++;
                        q.add(v);
                    } else {
                        color[v] = 1;
                        C1++;
                        q.add(v);
                    }
                }
            }
        }
        return Math.max(C1, C2);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int color[] = new int[N + 1];
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                ans += bipart(graph, i, color);
            }
        }
        System.out.println(ans);
    }
}
