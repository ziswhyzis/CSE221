import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class task8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long k = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());

            //ub & lb
            long left = 1, right = k * x; //let
            while (left < right) {

                long mid = left + (right - left)/2;
                long count = mid - mid / x;

                if (count >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            pw.println(left);
        }

        pw.close();
    }
}