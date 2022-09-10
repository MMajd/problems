/*
  
 @link https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
  
 You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
    Input: k = 2, prices = [2,4,1]
    Output: 2
    Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
    Input: k = 2, prices = [3,2,6,5,0,3]
    Output: 7
    Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Constraints:
    0 <= k <= 100
    0 <= prices.length <= 1000
    0 <= prices[i] <= 1000
 */


/*
Explanation:
DP is all about asking the right question, In this particular problem the right question is whether to make a transaction at the ith day or not, thus the problem at hand falls into the knapsack pattern, in the following solution we are taking the bottom-up approach using a profit 2d array as memoization array with dimensions equal to K+1 x N K+1 transactions and N days,
and our maximization equation is

profit[t][i] = Max { profit[t][i-1], max of prices[i]-prices[j] + profit[t-1][j] were 1 <= j < i }

t: no of transactions allowed, t = 0, ..., K
i: day to make the t-th transaction, i = 1, ..., N

and here is the translation of previous equation in plain English, we are taking the maximum between whether making no transaction on the ith day hence the profit is equal to the profit of the previous day when making t transactions profit[t][i-1] and the maximum of making a transction on the ith day buying the stock on the day jth were j is a day before the ith day, starting form day 1, plus the profit made of t-1 transactions on that day.

In this solution the loop to calculate the max of prices[i]-prices[j] + profit[t-1][j-1] were 1 <= j < i
is removed by rewriting the equation to be prices[i] + max of profit[t-1][j] - prices[j] , were 1<= j < i as i is constant durying this calcuation we can take it out of the max function, hence we can keep track of the previous calculated max diff seen up to, but not including, (i-1)th day and taking the maximum between these two values then adding it to the price of selling on the ith day.

*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2 || k <= 0) return 0;
        
        int[][] profit = new int[k+1][n];
        
        for (int t=1; t<=k; t++) { 
            
            int maxDiffSeen = Integer.MIN_VALUE; 
            
            for (int i=1; i<n; i++) { 
                maxDiffSeen = Math.max(profit[t-1][i-1]-prices[i-1], maxDiffSeen);
                profit[t][i] = Math.max(prices[i] + maxDiffSeen, profit[t][i-1]);
            }
        }
        
        return profit[k][n-1]; 
    }
}

