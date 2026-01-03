package lab3;

import java.io.*;
import java.util.*;

public class task4 {

    static final long mod = 1000000007L;

    public static long[][] multiply(long[][] A1, long[][] A2) {
        long[][] mult = new long[2][2];

        mult[0][0] = ((A1[0][0] * A2[0][0]) % mod + (A1[0][1] * A2[1][0]) % mod) % mod;
        mult[0][1] = ((A1[0][0] * A2[0][1]) % mod + (A1[0][1] * A2[1][1]) % mod) % mod;
        mult[1][0] = ((A1[1][0] * A2[0][0]) % mod + (A1[1][1] * A2[1][0]) % mod) % mod;
        mult[1][1] = ((A1[1][0] * A2[0][1]) % mod + (A1[1][1] * A2[1][1]) % mod) % mod;

        return mult;
    }

    public static long[][] power(long[][] A, long X) {

        // base cases
        if (X == 1) {
            return A;
        }

        // divide
        long[][] half = power(A, X / 2);

        // conquer
        long[][] result = multiply(half, half);

        // odd case handeling
        if (X % 2 == 1) {
            result = multiply(result, A);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseUnsignedInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            long[][] A = new long[2][2];
            A[0][0] = Long.parseLong(st.nextToken());
            A[0][1] = Long.parseLong(st.nextToken());
            A[1][0] = Long.parseLong(st.nextToken());
            A[1][1] = Long.parseLong(st.nextToken());

            long X = Long.parseLong(br.readLine());

            long[][] res = power(A, X);

            pw.println(res[0][0] + " " + res[0][1]);
            pw.println(res[1][0] + " " + res[1][1]);
        }
        br.close();
        pw.close();
    }
}