import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.HashMap;

public class task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        HashMap<Long, Integer> freq = new HashMap<>();
        int left = 0;
        int dist = 0;
        int max = 0;

        for (int right = 0; right < N; right++) {
            long V = A[right];

            if (freq.containsKey(V)) {
                freq.put(V, freq.get(V) + 1);
            } else {
                freq.put(V, 1);
                dist++;
            }

            while (dist > K) {
                long dlevel = A[left];
                int count = freq.get(dlevel) - 1;
                if (count == 0) {
                    freq.remove(dlevel);
                    dist--;
                } else {
                    freq.put(dlevel, count);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        pw.println(max);
        pw.close();
    }
}
