import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int N = sc.nextInt();
            sc.nextLine();

            String idLine = sc.nextLine();
            String[] idParts = idLine.split(" ");
            int[] id = new int[N];
            for (int j = 0; j < N; j++) {
                id[j] = Integer.parseInt(idParts[j]);
            }

            String markLine = sc.nextLine();
            String[] markParts = markLine.split(" ");
            int[] mark = new int[N];
            for (int j = 0; j < N; j++) {
                mark[j] = Integer.parseInt(markParts[j]);
            }

            int swap = selectionSort(id, mark, N);

            System.out.println("Minimum swaps: " + swap);
            for (int j = 0; j < N; j++) {
                System.out.println("ID: " + id[j] + " Mark: " + mark[j]);
            }
        }
        sc.close();
    }

    static int selectionSort(int[] id, int[] mark, int N) {
        int swap = 0;

        for (int i = 0; i < N - 1; i++) {
            int maxIdx = i;

            for (int j = i + 1; j < N; j++) {
                if (mark[j] > mark[maxIdx]) {
                    maxIdx = j;
                } else if (mark[j] == mark[maxIdx] && id[j] < id[maxIdx]) {
                    maxIdx = j;
                }
            }

            if (maxIdx != i) {
                swap++;
                int tempMark = mark[i];
                mark[i] = mark[maxIdx];
                mark[maxIdx] = tempMark;
                int tempId = id[i];
                id[i] = id[maxIdx];
                id[maxIdx] = tempId;
            }
        }

        return swap;
    }
}