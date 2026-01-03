package lab8;

import java.util.*;
import java.io.*;

public class task1 {

    static int par[];
    static int rank[];
    static int size[];

    public static void init(int N) {
        par = new int[N + 1];
        rank = new int[N + 1];
        size = new int[N  + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.fill(rank, 0);
        Arrays.fill(size, 1);
    }

    public static int find(int x) {
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    public static int union(int a, int b) {
        int parA = find(a), parB = find(b);
        
        if (parA == parB) {
            return size[parA];
        }
        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            size[parA] += size[parB];
            rank[parA]++;
            return size[parA];
        }
        else if (rank[parA] > rank[parB]) {
            par[parB] = parA;
            size[parA] += size[parB];
            return size[parA];
        }
        else {
            par[parA] = parB;
            size[parB] += size[parA];
            return size[parB];
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        init(N);

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }
}