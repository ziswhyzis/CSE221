import java.io.*;
import java.util.*;

public class task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());
        int[][] mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int t = 0; t < k; t++) {
                int nxt = Integer.parseInt(st.nextToken());
                mat[i][nxt] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(mat[i][j]);
                if (j != N - 1) sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
