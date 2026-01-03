package lab3;

import java.io.*;
import java.util.*;

public class task2 {

    static long count = 0;

    public static int findLowerBound(int[] arr, int start, int end, long target) {
        int ans = end + 1; //default
        int low = start;
        int high = end;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void countPairsInRange(int[] arr, int left, int mid, int right) {
        for (int i = left; i <= mid; i++) {
            
            if (arr[i] <= 0) {
                continue;
            }

            double root = Math.sqrt((double) arr[i]);

            long lowerBound = (long)Math.floor(-root) + 1; // We need A[j] > -root
            long upperBound = (long)Math.ceil(root); // We need A[j] < root

            int idx1 = findLowerBound(arr, mid + 1, right, lowerBound); //first index >= lowerBound
            int idx2 = findLowerBound(arr, mid + 1, right, upperBound); //first index >= upperBound

            count += (idx2 - idx1);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void mergeSortAndCount(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSortAndCount(arr, left, mid);
        mergeSortAndCount(arr, mid + 1, right);

        countPairsInRange(arr, left, mid, right);
        merge(arr, left, mid, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
        mergeSortAndCount(A, 0, N - 1);

        pw.println(count);
        pw.close();
        br.close();
    }
}
