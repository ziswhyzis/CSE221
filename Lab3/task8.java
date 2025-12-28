import java.io.*;
import java.util.*;

public class task8 {

    static int[] inorder;
    static int[] postorder;
    static StringBuilder sb = new StringBuilder();

    public static int findIdx(int inStart, int inEnd, int val) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void preorder(int inStart, int inEnd, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd) {
            return;
        }

        int val = postorder[postEnd];

        sb.append(val).append(" ");

        int idx = findIdx(inStart, inEnd, val);
        int LTsize = idx - inStart;

        preorder(inStart, idx - 1, postStart, postStart + LTsize - 1);
        preorder(idx + 1, inEnd, postStart + LTsize, postEnd - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        inorder = new int[N];
        postorder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        preorder(0, N - 1, 0, N - 1);

        pw.println(sb.toString().trim());
        pw.close();
        br.close();
    }
}