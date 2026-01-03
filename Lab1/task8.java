package lab1;

import java.util.*;

public class task8 {
    static int compare(String nameA, int timeA, int idxA,
            String nameB, int timeB, int idxB) {
        int c = nameA.compareTo(nameB);
        if (c != 0)
            return c;
        if (timeA != timeB) {
            return timeB - timeA;
        }
        return idxA - idxB;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String[] lines = new String[n];
        String[] names = new String[n];
        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            lines[i] = sc.nextLine();

            String[] parts = lines[i].split(" ");
            names[i] = parts[0];
            String[] t = parts[6].split(":");
            times[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }

        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        for (int i = 1; i < n; i++) {
            int key = idx[i];
            for (int j = i - 1; j >= 0; j--) {
                if (compare(names[idx[j]], times[idx[j]], idx[j],
                        names[key], times[key], key) > 0) {
                    idx[j + 1] = idx[j];
                    idx[j] = key;
                } else {
                    break;
                }
            }
        }

        for (int id : idx) {
            System.out.println(lines[id]);
        }
        sc.close();
    }
}