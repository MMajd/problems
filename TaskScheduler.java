/*
 @link https://leetcode.com/problems/task-scheduler/
  
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






class Solution {
    class Task {
        int freq = 0; 
        int back = 0;
        
        public Task(int freq) {
            this.freq = freq; 
        }
    }
    
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length; 
        
        int[] chars = new int[26]; 
        Queue<Task> heap = new PriorityQueue<>(26, (a, b) -> b.freq - a.freq);
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
                t.freq -= 1;
                
                if (t.freq > 0) q.add(t);
            }
            
            if (!q.isEmpty() && q.peek().back == time) { 
                heap.add(q.poll());
            }
        }
        
        return time; 
    }
}
