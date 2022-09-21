/*
 @link https://leetcode.com/problems/task-scheduler/
 @categories (greedy/math)
  
 Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: 
    A -> B -> idle -> A -> B -> idle -> A -> B
    There is at least 2 units of time between any two same tasks.

Example 2:
    Input: tasks = ["A","A","A","B","B","B"], n = 0
    Output: 6
    Explanation: On this case any permutation of size 6 would work since n = 0.
    ["A","A","A","B","B","B"]
    ["A","B","A","B","A","B"]
    ["B","B","B","A","A","A"]
    ...
    And so on.

Example 3:
    Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    Output: 16
    Explanation: 
    One possible solution is
    A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

Constraints:
    1 <= task.length <= 104
    tasks[i] is upper-case English letter.
    The integer n is in the range [0, 100].
*/


public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }

        /**
         * N = 3, A:4, B:4, C:2 
         * A # # # A # # # A # # # A 
         * A B # # A B # # A B # # A B | => gap len is 2 
         * 
         * gpasCount = max - 1 = 3-1 = 2; 
         * gapLength = n - (maxCount-1) = 3 - 1 = 2; 
         * emptySlots = gapsCount * gapLen = 2 * 2 = 4; 
         * completedTasks = max * maxCount; 
         * remainingTasks = tasks.length - completedTasks;
         * idles = Math.max(0, emptySlots-availableTasks);
         *
         */


        int gapsCount = max - 1;
        int gapLen = n - (maxCount - 1);
        int emptySlots = gapsCount * gapLen;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);
        
        return tasks.length + idles;
    }
}





class Solution {
    class Task {
        int milestones = 0; 
        int back = 0;
        
        public Task(int milestones) {
            this.milestones = milestones; 
        }
    }
    
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length; 
        
        int[] chars = new int[26]; 
        Queue<Task> heap = new PriorityQueue<>(26, (a, b) -> b.milestones - a.milestones);
        Deque<Task> q = new ArrayDeque<>();
        
        for (int i=0; i<tasks.length; i++) { 
            chars[tasks[i]-'A'] += 1; 
        }
        
        for (int i=0; i<26; i++) { 
            if (chars[i] != 0) heap.add(new Task(chars[i])); 
        }
        
        int time = 0; 
        
        while (!heap.isEmpty() || !q.isEmpty()) {
            time += 1; 
            
            if (!heap.isEmpty()) { 
                Task t = heap.poll();
                t.back = time + n;
                t.milestones -= 1;
                
                if (t.milestones > 0) q.add(t);
            }
            
            if (!q.isEmpty() && q.peek().back == time) { 
                heap.add(q.poll());
            }
        }
        
        return time; 
    }
}
