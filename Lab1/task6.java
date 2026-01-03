package lab1;

import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String input = sc.nextLine();
        String[] elem = input.split(" ");
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(elem[i]);
        }

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if ((arr[i] % 2 == arr[i + 1] % 2) && arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
        sc.close();
    }
}
