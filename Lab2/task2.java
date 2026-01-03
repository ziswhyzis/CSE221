package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] A = new long[N];
        long[] B = new long[M];

        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        int first = N - 1;
        int second = 0;
        long minK = Long.MAX_VALUE;
        int targetFirst = 0, targetSecond = 0;

        while (first >= 0 && second < M) {
            long diff2K = Math.abs((A[first] + B[second]) - K);

            if (diff2K < minK) {
                minK = diff2K;
                targetFirst = first;
                targetSecond = second;
            }

            if ((A[first] + B[second]) > K) {
                first--;
            } else {
                second++;
            }
        }

        pw.println((targetFirst + 1) + " " + (targetSecond + 1));
        pw.close();
    }
}
