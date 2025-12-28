import java.util.Scanner;
import java.util.ArrayList;

public class task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();
        String[] elem = input.split(" ");

        ArrayList<Integer> Arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            Arr.add(Integer.parseInt(elem[i]));

        if (isSorted(Arr)) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }

        if (n == 2) {
            System.out.println("NO");
            return;
        }

        ArrayList<int[]> move = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (Arr.get(j) > Arr.get(j + 1) || Arr.get(j) > Arr.get(j + 2)) {
                    revArr(Arr, j);
                    move.add(new int[] { j + 1, j + 3 });
                }
            }
        }

        if (isSorted(Arr)) {
            System.out.println("YES");
            System.out.println(move.size());
            for (int[] m : move)
                System.out.println(m[0] + " " + m[1]);
        } else {
            System.out.println("NO");
        }
    }

    static boolean isSorted(ArrayList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < arr.get(i - 1))
                return false;
        }
        return true;
    }

    static void revArr(ArrayList<Integer> arr, int i) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(i + 2));
        arr.set(i + 2, tmp);
    }
}