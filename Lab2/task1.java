import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        long[] a = new long[N];
        
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        int left = 0, right = N - 1; boolean found = false;

        while (left < right) {
            long sum = a[left] + a[right];
            if (sum == S) {
                pw.println((left + 1) + " " + (right + 1));
                found = true;
                break;
            } else if (sum < S) {
                left++;
            } else {
                right --;
            }
        }

        if (!found) {
            pw.println("-1");
        }
        pw.close();
    }
}
