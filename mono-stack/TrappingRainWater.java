/*
  
 @link https://leetcode.com/problems/trapping-rain-water/
  
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
    Input: height = [4,2,0,3,2,5]
    Output: 9

Constraints:
    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105

    CreateMaximumNumber.java
    FindoriginalArrayFromDoubledArray.java
    MaximumPerformanceOfTeam.java
    MaximumSwap.java
    MaximumUnitsonaTruck.java
    MinimumCostToHireKWorkers.java
    MinimumRoundstoCompleteAllTasks.java


 */


class Solution {
    public int trap(int[] heights) {
        int n = heights.length; 
        int ans = 0; 
        
        Deque<Integer> stack = new ArrayDeque(n);
        
        for (int i=0; i<n; i++) { 
            while(!stack.isEmpty() 
                    && heights[stack.peek()] < heights[i]) {
                int midIdx = stack.pop(); 
                
                if (!stack.isEmpty()) { 
                    int pIdx = stack.peek();
                    int h = Math.min(heights[i], heights[pIdx]) - heights[midIdx];
                    ans += h * (i-pIdx-1);
                }
            }
            
            stack.push(i);
        }
        
        return ans; 
    }
}
