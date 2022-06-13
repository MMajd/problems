
/** 
@link https://baihuqian.github.io/2018-07-28-3sum-smaller

we don't care if theres a duplicates triplets 

this is a solution page but has the problem statement 
as its on premium package on leetcode 
 
Given an array of n integers nums and a target, find the number of index triplets i, left, right 
with 0 <= i < left < right < n that satisfy the condition nums[i] + nums[left] + nums[right] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
         [-2,0,1]
         [-2,0,3]
 
*/

import java.util.*; 

class ThreeSumSmaller { 

    public static void main(String[] args) { 
        System.out.println(new ThreeSumSmaller().threeSumSmaller(new int[]{-2, 0, 1, 3}, 2) == 2);
    }

    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length <= 3) return 1; 

        Arrays.sort(nums);

        int len = nums.length; 
        int ans = 0;
        int sum = 0; 
        int left=0, right=0; 

        for (int i=0; i<nums.length-2; i++) { 
            left = i+1; 
            right = len-1; 

            while(left < right) { 
                sum = nums[i] + nums[left] + nums[right]; 
                if (sum < target) { 
                    ans += right - left; 
                    left += 1; 
                } else { 
                    right -= 1; 
                }
            }
        }

        return ans; 
    }
}
