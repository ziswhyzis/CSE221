package lab5;

import java.io.*;
import java.util.*;

public class task6 {

    private static boolean dfsCheck(int node, ArrayList<Integer>[] adj, int[] vis, int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;

        ArrayList<Integer> list = adj[node];
        for (int i = 0; i < list.size(); i++) {
            int it = list.get(i);

            if (vis[it] == 0) {
                if (dfsCheck(it, adj, vis, pathVis)) {return true;}
            }
            else if (pathVis[it] == 1) {
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }

        int[] vis = new int[N + 1];
        int[] pathVis = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (vis[i] == 0) {
                if (dfsCheck(i, adj, vis, pathVis)) {
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");
    }
}