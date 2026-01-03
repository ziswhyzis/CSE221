package lab6;

import java.util.*;
import java.io.*;

public class task2 {

    static class Edge {
        int src, dst;

        Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    private static int maxNum(ArrayList<ArrayList<Edge>> graph, int N) {
        int[] color = new int[N + 1]; // 0: not visited, 1: one team, 2: other team
        int max = 0;

        for(int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                int c1 = 0, c2 = 0;

                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                color[i] = 1;
                c1++;

                while (!q.isEmpty()) {
                    int u = q.poll();
                    for(Edge e : graph.get(u)) {
                        int v = e.dst;
                        if (color[v] == 0) {
                            if (color[u] == 1) {
                                color[v] = 2; c2++;
                            } else {
                                color[v] = 1; c1++;
                            }
                            q.add(v);
                        }
                    }
                }
                max += Math.max(c1, c2);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Edge(A, B));
            graph.get(B).add(new Edge(B, A));
        }

        System.out.println(maxNum(graph, N));
    }
}
