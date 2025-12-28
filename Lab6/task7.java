import java.io.*;
import java.util.*;

public class task7 {

    static class Edge {
        int src, dst;
        Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    static String bfsTopo(ArrayList<ArrayList<Edge>> graph, int[] indeg, boolean[] present) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (present[i] && indeg[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            res.append((char) (u + 'a'));

            for (Edge e : graph.get(u)) {
                indeg[e.dst]--;
                if (indeg[e.dst] == 0)
                    q.add(e.dst);
            }
        }

        int count = 0;
        for (boolean x : present) if (x) count++;

        if (res.length() != count) return "-1";
        return res.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] word = new String[n];

        boolean[] present = new boolean[26];

        for (int i = 0; i < n; i++) {
            word[i] = br.readLine();
            for (char c : word[i].toCharArray()) {
                present[c - 'a'] = true;
            }
        }

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indeg = new int[26];

        for (int i = 0; i < n - 1; i++) {
            String a = word[i], b = word[i + 1];
            int len = Math.min(a.length(), b.length());
            boolean diff = false;

            for (int j = 0; j < len; j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    int u = a.charAt(j) - 'a';
                    int v = b.charAt(j) - 'a';
                    graph.get(u).add(new Edge(u, v));
                    indeg[v]++;
                    diff = true;
                    break;
                }
            }

            if (!diff && a.length() > b.length()) {
                System.out.println("-1");
                return;
            }
        }

        System.out.println(bfsTopo(graph, indeg, present));
    }
}
