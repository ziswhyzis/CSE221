package lab1;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            sc.nextLine(); // consume newline after n (previously mentioned in task2)

            String input = sc.nextLine();
            String[] elem = input.split(" ");
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(elem[j]);
            }

            boolean isNonDecreasing = true;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    isNonDecreasing = false;
                    break;
                }
            }
            System.out.println(isNonDecreasing ? "YES" : "NO");
        }
        sc.close();
    }
}