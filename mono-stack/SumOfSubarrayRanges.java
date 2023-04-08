/* 
 @link https://leetcode.com/problems/sum-of-subarray-ranges
 @categories (stack/arrays) 

 You are given an integer array nums. 
The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
    Input: nums = [1,2,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
    [1], range = largest - smallest = 1 - 1 = 0 
    [2], range = 2 - 2 = 0
    [3], range = 3 - 3 = 0
    [1,2], range = 2 - 1 = 1
    [2,3], range = 3 - 2 = 1
    [1,2,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:
    Input: nums = [1,3,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
    [1], range = largest - smallest = 1 - 1 = 0
    [3], range = 3 - 3 = 0
    [3], range = 3 - 3 = 0
    [1,3], range = 3 - 1 = 2
    [3,3], range = 3 - 3 = 0
    [1,3,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:
    Input: nums = [4,-2,-3,4,1]
    Output: 59
    Explanation: The sum of all subarray ranges of nums is 59.

Constraints:
    1 <= nums.length <= 1000
    -10^9 <= nums[i] <= 10^9

Follow-up: 
    Could you find a solution with O(n) time complexity? (use MonotonicStack)
*/

import static java.lang.Math.*; 

// O(N), Monotonic stack
class Solution {
    public long subArrayRanges(int[] A) {
        int n = A.length; 
        long ans = 0;
        Deque<Integer> s1 = new ArrayDeque<>(A.length); 
        Deque<Integer> s2 = new ArrayDeque<>(A.length); 

        /**
         * 0  1  2  3  4  5  6 
         * a  b  c  d  e  f  g 
         * k        j     i    
         * No of sub-arrays j participate in [Cartersian Product] : (j - k) * (i - j) = (3-0) * (5-3) = 3 * 2 = 2  
         */ 

        /* min loop */
        for (int i=0; i<=n; i++) { 
            while (!s1.isEmpty() && A[s1.peek()] > (i == n ? Integer.MIN_VALUE : A[i])) {
                int j = s1.pop(); 
                int k = s1.isEmpty() ? -1 : s1.peek();
                ans -= 1L * A[j] * (i-j) * (j-k);
            }
            s1.push(i);
        }

        /* max loop */
        for (int i=0; i<=n; i++) { 
            while (!s2.isEmpty() && A[s2.peek()] < (i == n ? Integer.MAX_VALUE : A[i])) { 
                int j = s2.pop(); 
                int k = s2.isEmpty() ? -1 : s2.peek();
                ans += 1L * A[j] * (i-j) * (j-k);
            }
            s2.push(i);
        }
        return ans; 
   }
}

// O(N^2) 
class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length; 
        long res = 0; 

        for (int i=0; i<n; i++) { 
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE; 
            for (int j=i; j<n; j++) { 
                max = max(max, nums[j]);
                min = min(min, nums[j]);

                res += max-min;
            }
        }

        return res; 
    }
}

