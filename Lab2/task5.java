import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        int left = 0;
        long sum = 0;
        int max = 0;
        for (int right = 0; right < N; right++) {
            sum += a[right];
            while (left <= right && sum > K) {
                sum -= a[left];
                left++;
            }
            
            int length = right - left + 1; //inclusive !!
            if (length > max) {
                max = length;
            }
        }

        pw.println(max);
        pw.close();
    }
}
