/*
  
 @link https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 @categories (greedy) 

 Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
Return the minimum time Bob needs to make the rope colorful.

Example 1:
    Input: colors = "abaac", neededTime = [1,2,3,4,5]
    Output: 3
    Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
    Bob can remove the blue balloon at index 2. This takes 3 seconds.
    There are no longer two consecutive balloons of the same color. Total time = 3.

Example 2:
    Input: colors = "abc", neededTime = [1,2,3]
    Output: 0
    Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.

Example 3:
    Input: colors = "aabaa", neededTime = [1,2,3,4,1]
    Output: 2
    Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
    There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.

Constraints:
    n == colors.length == neededTime.length
    1 <= n <= 105
    1 <= neededTime[i] <= 104
    colors contains only lowercase English letters.

********************************************************
# Solution Intiution: 
#   We need to remove consective group of the same color and keep the only one that has max remove time 
#   We use two pointers to determine the start and end of a group and add all values and substract the max between them 
#   Previous idea can be reduced to using only a max variable and a total sum variable only as shown below 
********************************************************
 */ 


class Solution {
    public int minCost(String c, int[] t) {
        int ans = 0; 
        int max = t[0];
        
        for (int i=1; i<c.length(); i++) { 
            // compare i-th balloon color to its previous balloon 
            int u = c.charAt(i-1); 
            char v = c.charAt(i); 
            
            // if different groups let max be zero
            if (u != v) { 
                max = 0; 
            }
            
            // take the min between curr max and the i-th balloon remove time 
            ans += Math.min(max, t[i]);
            // update the max to be maximum between max so far and the i-th balloon remove time; 
            max = Math.max(max, t[i]);
        }
        
        return ans; 
    }
}
