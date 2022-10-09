/*
 @link https://leetcode.com/problems/partition-to-k-equal-sum-subsets
 @categories (backtracking/dynamic-programming) 

 Given an integer array nums and an integer k, 
return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
    Input: nums = [4,3,2,3,5,2,1], k = 4
    Output: true
    Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

Example 2:
    Input: nums = [1,2,3,4], k = 3
    Output: false

Constraints:
    1 <= k <= nums.length <= 16
    1 <= nums[i] <= 104
    The frequency of each element is in the range [1, 4].

 */ 



/**
 * Two Solutions: 
 *  1. Backtracking
 *  2. Dynamic Programming
 */ 

class Solution { 

    // Backtracking Solution 
    public boolean canPartitionKSubsets(int[] A, int k) {
        if (k > A.length) return false;
        int sum = 0;
        int max = 0; 
        
        for (int num : A) {
            sum += num;
            max = Math.max(max, num);
        }
        
        if (sum % k != 0) return false;
        if (max > sum/k) return false;
            
        Arrays.sort(A);
        
        return dfs(A, 0, A.length - 1, 0, sum/k, k); // visited has to be at least 16-bit integer
    }

    public boolean dfs(int[] A, int sum, int i, int visited, int target, int k) {
        if (k == 1) return true;
        if (sum == target) return dfs(A, 0, A.length - 1, visited, target, k-1);
        
        for (int j = i; j >= 0; --j) {
            if ((visited & (1 << j)) != 0 || sum + A[j] > target) continue; 
            
            visited ^= (1<<j);
            
            if (dfs(A, sum + A[j], j-1, visited, target, k))
                return true;
            
            visited ^= (1<<j);
        }
        
        return false;
    }


    // DP Solution 
    public boolean canPartitionKSubsets2(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return false;
		
		int n = nums.length;
		//result array
		boolean dp[] = new boolean[1<<n];
		int total[] = new int[1<<n];
		dp[0] = true;
		
		int sum = 0;
		for(int num : nums) sum += num;
        
		Arrays.sort(nums);
		
		if (sum%k != 0) return false;
		sum /= k;
		if (nums[n-1] > sum) return false;

		// Loop over power set
		for(int i = 0;i < (1<<n);i++) {
			if(dp[i]) {
				// Loop over each element to find subset
				for(int j = 0;j < n;j++) {
					// set the jth bit 
					int temp = i | (1 << j);
					if(temp != i) {
						// if total sum is less than target store in dp and total array
						if(nums[j] <= (sum- (total[i]%sum))) {
							dp[temp] = true;
							total[temp] = nums[j] + total[i];
						} 
                        else break;
					}
				}
			}
		}
		return dp[(1<<n) - 1];
	}
}



