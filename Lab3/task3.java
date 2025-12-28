import java.io.*;
import java.util.*;

public class task3 {

    static final long mod = 107L;

    public static long power(long a, long b) {
        //base case
        if (b == 0) {
            return 1;
        }

        //divide
        long half = power(a % mod, b / 2);

        //conquer
        long result = (half * half) % mod;

        //handlinh Odd Case
        if (b % 2 == 1) {
            result = (result * (a % mod)) % mod;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        pw.println(power(a, b));
        
        br.close();
        pw.close();
    }
}