/* 

 @link https://leetcode.com/problems/best-time-to-buy-and-hold-stock-with-cooldown
  
 You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and hold one share of the stock multiple times) with the following restrictions:

After you hold your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must hold the stock before you buy again).

 
Example 1:
    Input: prices = [1,2,3,0,2]
    Output: 3
    Explanation: transactions = [buy, hold, cooldown, buy, hold]

Example 2:
    Input: prices = [1]
    Output: 0

Constraints:
    1 <= prices.length <= 5000
    0 <= prices[i] <= 1000
*/


class Solution { 
    public int maxProfit(int[] prices) {
        // return topdown(prices); 
        return bottomup(prices); 
    }

    private int bottomup(int[] prices) { 
/*
 *        STATE MACHINE SOLUTION 
 *    ---\
 *    \-->REST < -------- COOLDOWN
 *        \                   ^
 *         \                  | 
 *          \                 /  SELL
 *     BUY   \               /
 *            --> HOLD -----/
 *               |   ^
 *               |---|              
 */


        int hold = 0; 
        int rest = Integer.MIN_VALUE; 
        int cooldown = 0; 
        
        for (int i=0; i<prices.length; i++) { 
            int prev = hold; 
            hold = rest + prices[i];
            rest = Math.max(rest, cooldown-prices[i]);
            cooldown = Math.max(cooldown, prev);
        }
        
        return Math.max(hold, cooldown);
    }
    
    private int topdown(int[] prices) { 
        // different states: 
        //  0 - buying
        //  1 - holding
        //  2 - hold & cooling down 
        int[][] dp = new int[prices.length][3]; 
        return solve(prices, 0, 0, dp); 
    }
    
    private int solve(int[] prices, int day, int state, int[][] dp) { 
        if (day == prices.length) return 0; 
        
        if (dp[day][state] != 0) return dp[day][state]; 
        
        // cooldown 
        if (state == 2)  { 
            dp[day][state] = solve(prices, day+1, 0, dp);
            return dp[day][state]; 
        }
        
        // holding
        if (state == 1)  { 
            dp[day][state] = Math.max(
                prices[day] + solve(prices, day+1, state + 1, dp), 
                solve(prices,day+1, state, dp) 
            );
            
            return dp[day][state]; 
        }
        
        // buying
        dp[day][state] = Math.max(
                -prices[day] + solve(prices, day+1, state+1, dp), 
                solve(prices, day+1, state, dp)
            );
        
        return dp[day][state]; 
    }
}
