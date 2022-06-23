/** 
 * 
 * @link leetcode.com/problems/course-schedule-iii
 *
 */

class Solution {
    public int scheduleCourse(int[][] courses) {
        int time = 0;
        
        Arrays.sort(courses, (a, b) -> a[1]==b[1] 
                    ? a[0]-b[0]
                    : a[1]-b[1]);
        
        PriorityQueue<Integer> q = 
            new PriorityQueue<>((a, b) -> b-a);
        
        for (int[] c : courses) { 
            if (c[0] + time <= c[1]) { 
                q.add(c[0]);
                time += c[0]; 
            } else { 
                if (!q.isEmpty() && q.peek() > c[0]) { 
                    time -= q.poll(); 
                    time += c[0]; 
                    q.add(c[0]);
                }
            }
        }
        
        return q.size();
    }
}
