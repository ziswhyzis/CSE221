package lab8;

import java.io.*;
import java.util.*;

public class task6 {

    static class Task {
        int duration;
        int deadline;
        Task(int duration, int deadline) {
            this.duration = duration;
            this.deadline = deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tasks.add(new Task(a, d));
        }

        tasks.sort((t1, t2) -> Integer.compare(t1.duration, t2.duration));

        long currentTime = 0;
        long maxReward = 0;
        for (Task task : tasks) {
            currentTime += task.duration;
            maxReward += (task.deadline - currentTime);
        }
        System.out.println(maxReward);
    }
}