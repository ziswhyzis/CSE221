import java.io.*;
import java.util.*;

public class task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            long[] result = sumNP(a, n, m); //size 2 array that has sum in idx 0 and power in idx 1 
            pw.println(result[0]);
        }

        br.close();
        pw.close();
    }

    public static long[] sumNP(long a, long n, long m) {

        if (n == 0) {
            return new long[] { 0, 1 };
        }

        long[] half = sumNP(a, n / 2, m);
        long sumHalf = half[0];
        long powerHalf = half[1];

        long evenSum = (sumHalf * (1 + powerHalf)) % m;
        long evenPower = (powerHalf * powerHalf) % m;

        long sum;
        long power;

        if (n % 2 == 1) {
            power = (evenPower * (a % m)) % m;
            sum = (evenSum + power) % m;
        } else {
            power = evenPower;
            sum = evenSum;
        }

        return new long[] { (sum + m) % m, (power + m) % m };
    }
}