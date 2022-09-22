/*
  @link https://leetcode.com/problems/non-overlapping-intervals/
  @categories (arrays/sort/greedy[activity-selection]/dp) 
  
  Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:
    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
    Input: intervals = [[1,2],[1,2],[1,2]]
    Output: 2
    Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
    Input: intervals = [[1,2],[2,3]]
    Output: 0
    Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
    1 <= intervals.length <= 105
    intervals[i].length == 2
    -5 * 104 <= starti < endi <= 5 * 104
*/


class Solution {
    private static class Interval {
        int start;
        int end; 
        int d; 
        
        public Interval(int start, int end) {
            this.start = start; 
            this.end = end; 
            this.d = end - start; 
        }
    } 
    
    public int eraseOverlapIntervals(int[][] A) {
        int n = A.length; 
        
        Interval[] B = new Interval[n]; 
        
        for (int i=0; i<n; i++) {
            B[i] = new Interval(A[i][0], A[i][1]); 
        }
        
        Arrays.sort(B, (a, b) -> { 
            if (a.end != b.end) return a.end - b.end; 
            return a.d - b.d; 
        });
        
        int time = Integer.MIN_VALUE; 
        int count = 0; 
        
        for (int i=0; i<n; i++) { 
            if (time <= B[i].start) { 
                count += 1; 
                time = B[i].end; 
            }
        }
        
        return n-count; 
    }
}


