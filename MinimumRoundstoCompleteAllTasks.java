/**
 *
 * @link: https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/
 * 
 * */

public class MinimumRoundstoCompleteAllTasks {
    public static void main(String[] args) { }

    private class Solution {
	    public int minRoundsToFinishTaskType(int n) { 
		int[] dp = new int[n + 1]; 
		int[] validTakes = {2, 3};
		
		Arrays.fill(dp, n+1); 
		dp[0] = 0; 
		
		for (int j: validTakes) {
		    for (int i=1; i<= n; i++)  {
			if (i >= j) dp[i] = Math.min(dp[i], dp[i-j]+1);
		    }
		}
		
		return dp[n]>n ? -1 : dp[n];
	    }
	    

	    public int minimumRounds(int[] tasks) {
		int ans = 0; 
		Map<Integer, Integer> hash = new HashMap<>(); 
		
		for(int key: tasks) { 
		    hash.put(key, hash.getOrDefault(key, 0) + 1); 
		}
		
		for (Map.Entry<Integer, Integer> e: hash.entrySet()) { 
		    int x = minRoundsToFinishTaskType(e.getValue());
		    
		    if (x == -1) return x; 
		    
		    ans += x; 
		}
		
		return ans; 
	    }
	}
}
