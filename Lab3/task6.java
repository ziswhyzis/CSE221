import java.io.*;
import java.util.*;

public class task6 {

    static StringBuilder sb = new StringBuilder();

    public static void OBST(int[] arr, int start, int end) {
        
        if (start > end) {
            return;
        }

        int mid = start + (end - start) / 2;

        sb.append(arr[mid]).append(" ");

        OBST(arr, start, mid - 1);
        OBST(arr, mid + 1, end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        OBST(A, 0, N - 1);
        
        System.out.println(sb.toString().trim());
        br.close();
    }
}