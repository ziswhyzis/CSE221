package lab3;

import java.io.*;
import java.util.*;

public class task7 {

    static int[] inorder;
    static int[] preorder;
    static int preIdx;
    static StringBuilder sb = new StringBuilder();

    public static int IOidx(int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void postorder(int start, int end) {
        if (start > end) {
            return;
        }

        int val = preorder[preIdx++];
        
        int rootInIndex = IOidx(start, end, val);

        postorder(start, rootInIndex - 1);
        postorder(rootInIndex + 1, end);

        sb.append(val).append(" ");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int N = Integer.parseInt(br.readLine());
        
        inorder = new int[N];
        preorder = new int[N];
        preIdx = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            preorder[i] = Integer.parseInt(st.nextToken());
        }

        postorder(0, N - 1);
        
        pw.println(sb.toString().trim());
        pw.flush();
        pw.close();
        br.close();
    }
}