package lab8;

import java.util.*;
import java.io.*;

public class task5b {

    static class Task {
        int start;
        int end;

        Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                tasks.add(new Task(start, end));
            }
            Collections.sort(tasks, (a, b) -> Integer.compare(a.end, b.end));
            int[] free_person = new int[M];
            Arrays.fill(free_person, -1);
            int count = 0;

            for (Task task : tasks) {
                int start = task.start;
                int end = task.end;

                int avail = -1;
                int best_time = Integer.MIN_VALUE;

                for (int i = 0; i < M; i++) {
                    if (free_person[i] < start) {
                        if (free_person[i] > best_time) {
                            best_time = free_person[i];
                            avail = i;
                        }
                    }
                }
                if (avail != -1) {
                    count++;
                    free_person[avail] = end;
                }
            }
            System.out.println(count);
        }
    }
}