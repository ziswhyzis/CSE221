import java.util.*;
import java.io.*;

public class dum1 {

    public static void topSort(ArrayList<ArrayList<Integer>> graph, int N, int[] inDeg) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            ans.add(u);
            for (int v : graph.get(u)) {
                inDeg[v]--;
                if (inDeg[v] == 0) q.add(v);
            }
        }

        if (ans.size() != N) {
            System.out.println(-1);
            return;
        }

        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        int[] inDeg = new int[N + 1];
        
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            inDeg[v]++;
        }
        topSort(graph, N, inDeg);
    }
}
