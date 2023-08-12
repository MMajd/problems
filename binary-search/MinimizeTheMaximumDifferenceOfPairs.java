/**
 @link https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/ 
 @categories (binary-search/greedy)

 You are given a 0-indexed integer array nums and an integer p. 
Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. 
Also, ensure no index appears more than once amongst the p pairs.
Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, 
where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs. 
We define the maximum of an empty set to be zero.

Example 1:
    Input: nums = [10,1,2,7,1,3], p = 2
    Output: 1
    Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
    The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
    Input: nums = [4,2,1,2], p = 1
    Output: 0
    Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.

Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^9
    0 <= p <= (nums.length)/2
*/ 

class Solution {
    public int minimizeMax(int[] A, int p) {
        if (p == 0) return 0; 
        Arrays.sort(A);
        int low = 0; 
        int high = (int) 10E9; 
        int ans = high; 

        while (low <= high) { 
            int mid = low + (high - low) / 2; 

            if (isValid(A, mid, p)) { 
                ans = mid; 
                high = mid - 1; 
            } else { 
                low = mid + 1; 
            }
        }

        return ans; 
    }

    private boolean isValid(int[] A, int t, int p) {
        int n = A.length; 
        for (int i=0, cnt=0; i<n-1; i++) { 
            if (Math.abs(A[i] - A[i+1]) <= t) {
                cnt += 1; 
                i += 1; 
            } 

            if (cnt == p) return true; 
        }

        return false; 
    }
}
