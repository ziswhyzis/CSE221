import java.io.*;
import java.util.*;

public class task1 {
    static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr = mergeSort(arr);

        pw.println(count);
        for (int i = 0; i < n; i++) {
            pw.print(arr[i]);
            if (i + 1 < n) {
                pw.print(" ");
            }
        }
        pw.println();
        br.close();
        pw.close();
    }

    public static int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] tempArr = new int[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                tempArr[k++] = a[i++];
            } else {
                tempArr[k++] = b[j++];
                count += (a.length - i);
            }
        }

        while (i < a.length) {
            tempArr[k++] = a[i++];
        }
        while (j < b.length) {
            tempArr[k++] = b[j++];
        }

        return tempArr;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int mid = arr.length / 2;

        int[] a1 = new int[mid];
        int[] a2 = new int[arr.length - mid];

        for (int i = 0; i < mid; i++) {
            a1[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            a2[i - mid] = arr[i];
        }

        a1 = mergeSort(a1);
        a2 = mergeSort(a2);

        return merge(a1, a2);
    }
}
