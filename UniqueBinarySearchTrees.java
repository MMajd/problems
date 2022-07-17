/* 

@link https://leetcode.com/problems/unique-binary-search-trees/


Unique Binary Search Trees
Add to List

Given an integer n, return the number of structurally unique BST's 
(binary search trees) which has exactly n nodes of unique values from 1 to n.

Example 1:
    Input: n = 3
    Output: 5

Example 2:
    Input: n = 1
    Output: 1

Constraints:
    1 <= n <= 19

*/ 

/** TLE */ 
class Solution {
    public int numTrees(int n) {
        return G(1, n);  
    }
    
    private int G(int start, int end) { 
        if (start > end) return 1; 
        
        int ans = 0; 
        
        for (int i=start; i<=end; i++) { 
            ans +=  G(start, i-1) * G(i+1, end); 
        }
        
        return ans; 
    }
}

/** working solution */ 
class Solution {
    public int numTrees(int n) {
        if(n==1) return 1;
        
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        
        return dp[n];
    }

}
