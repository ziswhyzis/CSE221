package lab8;

import java.io.*;
import java.util.*;

public class task4 {

    static class Task {
        int start, end;
        Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tasks.add(new Task(s, e));
        }

        tasks.sort((t1, t2) -> Integer.compare(t1.end, t2.end));
        // same as : Collections.sort(tasks, (t1, t2) -> Integer.compare(t1.end, t2.end));

        List<Task> selected = new ArrayList<>();
        int lastEndTime = -1;
        for (Task t : tasks) {
            if (t.start > lastEndTime) {
                selected.add(t);
                lastEndTime = t.end;
            }
        }

        System.out.println(selected.size());
        for (Task t : selected) {
            System.out.println(t.start + " " + t.end);
        }
    }
}