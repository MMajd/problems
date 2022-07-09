/** 
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:

Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.

*/

public class MaxConsecutiveOnesII { 
    public static void main(String ...args) { 
        System.out.println(new Solution().findMaxConsecutiveOnes(new int[]{1,0,1,1,1,0,0,1})); 
    }
}

class Solution { 
    public int findMaxConsecutiveOnes(int[] arr) { 
        int ans = 0, curr = 0, prev = 0; 

        for (int i=0; i<arr.length; i++) { 
            curr += 1; 

            if (arr[i] == 0) { 
                prev = curr; 
                curr = 0; 
            }

            ans = Math.max(ans, curr + prev);
        }

        return ans; 
    }
}
