package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            //ub & lb fo binary search
            int right = upperBound(a, y);
            int left = lowerBound(a, x);

            pw.println(right - left);
        }

        pw.close();
    }

        static int upperBound(long[] a, long y) {
        int left = 0, right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= y)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    static int lowerBound(long[] a, long x) {
        int left = 0, right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] < x)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}