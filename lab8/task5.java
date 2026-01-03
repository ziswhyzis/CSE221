package lab8;

import java.util.*;
import java.io.*;

public class task5 {

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
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            ArrayList<Task> tasks = new ArrayList<>();
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                tasks.add(new Task(start, end));
            }
            tasks.sort((a, b) -> Integer.compare(a.end, b.end));
            
            TreeMap<Integer, Integer> freeTimes = new TreeMap<>();
            
            freeTimes.put(-1, M);
            int count = 0;
            
            for (Task task : tasks) {
                int start = task.start;
                int end = task.end;

                Integer bestTime = freeTimes.lowerKey(start); //
                
                if (bestTime != null) {
                    count++;
                    int peopleAtTime = freeTimes.get(bestTime);
                    
                    if (peopleAtTime == 1) {
                        freeTimes.remove(bestTime);
                    } else {
                        freeTimes.put(bestTime, peopleAtTime - 1);
                    }
                    
                    freeTimes.put(end, freeTimes.getOrDefault(end, 0) + 1);
                }
            }
            
            System.out.println(count);
        }
    }
}