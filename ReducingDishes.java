/*
 @link https://leetcode.com/problems/reducing-dishes
 @categories (dp/greedy)

 A chef has collected data on the satisfaction level of his n dishes. 
Chef can cook any dish in 1 unit of time. Like-time coefficient of a dish is defined as the time taken 
to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].

Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

Example 1:
    Input: satisfaction = [-1,-8,0,5,-9]
    Output: 14
    Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
    Each dish is prepared in one unit of time.

Example 2:
    Input: satisfaction = [4,3,2]
    Output: 20
    Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)

Example 3:
    Input: satisfaction = [-1,-4,-5]
    Output: 0
    Explanation: People do not like the dishes. No dish is prepared.

Constraints:
    n == satisfaction.length
    1 <= n <= 500
    -1000 <= satisfaction[i] <= 1000
*/ 

import static java.lang.Math.*; 

// Greedy approach 
// to be proven later
class Solution {
    public int maxSatisfaction(int[] S) {
        Arrays.sort(S);
        int ans = 0, total = 0, n = S.length; 
        // S[i] > -total, to keep total positive, 
        // if total became negative, will start decreasing the optimal solution 
        for (int i=n-1; i>=0 && S[i] > -total; --i) { 
            total += S[i]; 
            ans += total; // we reach time multiplication by this repitition 
        }
        return ans;
    }
}

// DP Solution
/*
Input: S = [-1,-8,0,5,-7]
Steps: 
    Sort(S) = [-8,-7,-1,0,5] 
    then construct the dp bottom up tree using dp[n+1][n+2] array
    dry run is shown below
---------------------------------------
    TIME|   |  1 | 2  |3  |4  |5  | 6 |
--------|---|----|----|---|---|---|---|
      N |   |    |    |   |   |   |   |
      4 | 0 |  5 | 10 |15 |20 |25 | 0 |
      3 | 0 | 10 | 15 |20 |25 |25 | 0 | 
      2 | 0 | 14 | 18 |22 |25 |25 | 0 | 
      1 | 0 | 14 | 18 |22 |25 |25 | 0 |
      0 | 0 | 14 | 18 |22 |25 |25 | 0 |
---------------------------------------
 */
class Solution {
    public int maxSatisfaction(int[] S) {
        Arrays.sort(S);
        int[][] dp = new int[S.length + 1][S.length + 2];
        
        for (int i = S.length - 1; i >= 0; i--) {
            // populate all posisble like-time coeff of S[i]
            for (int j = 1; j <= S.length; j++) {
                dp[i][j] = Math.max(S[i] * j + dp[i + 1][j + 1], dp[i + 1][j]);
            }
        }
        
        return dp[0][1];
    }
}

