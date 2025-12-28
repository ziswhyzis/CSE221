import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        long[] B = new long[M];
        for (int i = 0; i < M; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        long[] result = new long[N + M];
        int i = 0, j = 0, k = 0;

        while (i < N && j < M) {
            if (A[i] <= B[j]) {
                result[k] = A[i];
                i++;
            } else {
                result[k] = B[j];
                j++;
            }
            k++;
        }

        while (i < N) {
            result[k] = A[i];
            i++;
            k++;
        }
        while (j < M) {
            result[k] = B[j];
            j++;
            k++;
        }

        for (int idx = 0; idx < N + M; idx++) {
            pw.print(result[idx]);
            if (idx != N + M - 1) {
                pw.print(" ");
            }
        }
        pw.println();
        pw.close();
    }
}
