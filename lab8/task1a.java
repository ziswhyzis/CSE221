package lab8;

import java.io.*;
import java.util.*;

public class task1a {
    public static int[] parent;
    public static int[] size;

    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return size[pa];
        }

        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
            return size[pb];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
            return size[pa];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // number of test cases

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            size = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int circleSize = union(a, b);
                System.out.println(circleSize);
            }
        }
    }
}