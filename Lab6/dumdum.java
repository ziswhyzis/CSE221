import java.util.*;
import java.io.*;

public class dumdum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] dict = new String[N];

        int K = 26; // a = 1, b = 2, ... z = 26

        for (int i = 0; i < N; i++) dict[i] = br.readLine();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= K; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i], s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());

            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    graph.get(s1.charAt(j) - 'a' + 1).add(s2.charAt(j) - 'a' + 1);
                    break;
                }
            }
            if (s1.length() > s2.length() && s1.startsWith(s2)) {
                System.out.println("-1");
                return;
            }
        }

        List<Integer> topo = topoSort(graph, K);

        if (topo.size() < K) {
        System.out.println("-1");
        return;
        }


        String ans = "";
        for (int i : topo) {
            ans += (char)(i + 'a' - 1);
        }

        System.out.println(ans);
    }

    public static List<Integer> topoSort(ArrayList<ArrayList<Integer>> graph, int K) {
        int inDeg[] = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            for (int it : graph.get(i)) {
                inDeg[it]++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= K; i++) {
            if (inDeg[i] == 0) pq.add(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!pq.isEmpty()) {
            int node = pq.poll();
            topo.add(node);

            for (int i : graph.get(node)) {
                inDeg[i]--; //node is in topo, so remove from inDeg
                if (inDeg[i] == 0) pq.add(i); //alt -> if (--inDeg[v] == 0) pq.add(v)
            }
        }
        return topo;
    }
}
