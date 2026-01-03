package lab8;

import java.util.*;
import java.io.*;

/**
 * PROBLEM UNDERSTANDING:
 * - We have N tasks, each with a start time and end time
 * - We have M people who can work on these tasks
 * - Each person can only do ONE task at a time (no multitasking)
 * - Two tasks cannot overlap for the same person
 * - GOAL: Maximize the number of tasks completed
 * 
 * SOLUTION APPROACH - GREEDY ALGORITHM:
 * 1. Sort tasks by END TIME (finish early = free up people sooner)
 * 2. For each task, assign it to an available person
 * 3. A person is available if they finished their last task BEFORE this task starts
 * 4. Among available people, choose the one who became free most recently
 *    (This keeps people with earlier free times available for tasks that start sooner)
 */
public class task5a {

    // Task class to store start and end time of each activity
    static class Task {
        int start;  // When the task begins
        int end;    // When the task finishes

        Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            // Read N (number of tasks) and M (number of people)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // ArrayList to store all tasks
            ArrayList<Task> tasks = new ArrayList<>();
            
            // Read each task's start and end time
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                tasks.add(new Task(start, end));
            }
            
            // STEP 1: GREEDY STRATEGY - Sort tasks by END TIME (earliest finish first)
            // WHY? If we complete tasks that finish early, we free up people sooner
            // This gives us more opportunities to assign remaining tasks
            Collections.sort(tasks, (a, b) -> Integer.compare(a.end, b.end));
            
            // STEP 2: Track when each person becomes free
            // free_person_time[i] = time when person i finishes their current task
            // Initially all people are free at time -1 (before any task starts)
            int[] free_person_time = new int[M];
            Arrays.fill(free_person_time, -1);
            
            int count = 0;  // Count of tasks completed

            // STEP 3: Process each task in sorted order (by end time)
            for (Task task : tasks) {
                int start = task.start;
                int end = task.end;

                // Find an available person to assign this task
                int available = -1;  // Index of available person (-1 = no one available)
                int best_time = Integer.MIN_VALUE;  // Latest free time found so far

                // Check all M people to find who can do this task
                for (int i = 0; i < M; i++) {
                    // CRITICAL CONDITION: Person must finish their previous task BEFORE this one starts
                    // free_person_time[i] < start means: person i's last task ended before this task begins
                    // This ensures NO OVERLAP (if someone finishes at time 5, new task must start at 6 or later)
                    if (free_person_time[i] < start) {
                        // GREEDY CHOICE: Among all available people, pick the one who became free MOST RECENTLY
                        // Why? Keep people with earlier free times available for tasks that might start sooner
                        // Example: If person A is free at time 2 and person B at time 7,
                        //          and current task starts at 10, pick B (keep A available for tasks starting 3-9)
                        if (free_person_time[i] > best_time) {
                            best_time = free_person_time[i];
                            available = i;
                        }
                    }
                }
                
                // STEP 4: If we found an available person, assign the task
                if (available != -1) {
                    count++;  // One more task completed
                    free_person_time[available] = end;  // This person becomes free when this task ends
                }
                // If available == -1, no one is available, so this task cannot be completed
            }
            
            // Output the maximum number of tasks completed
            System.out.println(count);
        }
    }
}

/*
COMPARISON: Naive Array vs TreeMap Approach

ARRAY (naive approach):
- Data structure: int[] array of size M
- Finding available person: O(M) - must check every person
- Updating availability: O(1) - direct array access
- Total complexity: O(N*M) where N = tasks, M = people
- Space: O(M)
- Pros: Simple, easy to understand, good for small M
- Cons: Slow when M is large (many people)

TREEMAP:
- Data structure: TreeMap<Integer, Integer> (time -> count)
- Finding available person: O(log M) - binary search in sorted keys
- Updating availability: O(log M) - tree operations
- Total complexity: O(N*log M)
- Space: O(M) in worst case
- Pros: Fast even with many people, scales well
- Cons: More complex, harder to understand, TreeMap overhead

WHEN TO USE WHICH:
- Naive: M is small (< 100), simplicity matters, learning/understanding
- TreeMap: M is large (> 1000), performance critical, production code

EXAMPLE WITH M=3, TASK=(5,10):
Naive: Check person[0], person[1], person[2] one by one
TreeMap: Binary search through finish times {-1, 3, 7} -> find 3 instantly

Both give the same answer, but TreeMap is faster for large M!
*/