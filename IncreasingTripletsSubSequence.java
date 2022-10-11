/* 
 @link https://leetcode.com/problems/increasing-triplet-subsequence/
 @categories (arrays/greedy/patience-sort)

 Given an integer array nums, return true if there exists a triple of indices (i, j, k) 
 such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
    Input: nums = [1,2,3,4,5]
    Output: true
    Explanation: Any triplet where i < j < k is valid.

Example 2:
    Input: nums = [5,4,3,2,1]
    Output: false
    Explanation: No triplet exists.

Example 3:
    Input: nums = [2,1,5,0,4,6]
    Output: true
    Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

Constraints:
    1 <= nums.length <= 5 * 105
    -231 <= nums[i] <= 231 - 1
 

Follow up: 
    Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
*/

class IncreasingTripletsSubSequence {
    /** n solution */
    class Solution {
        public boolean increasingTriplet(int[] nums) {
            int left= Integer.MAX_VALUE, right= Integer.MAX_VALUE;
            //take assumption : left < right
            for(int n: nums){
                //Test each number one by one comparing First with left and then with right
                if(n<=left) left= n;
                else if(n<=right) right= n;  // If n is LTET right, means n is surely GT left
                else return true; // If n is GTET left and GTET right that means we got the increasing triplet
            }
            return false;
        }
    }


    /** binary search solution */
     class Solution {
         public boolean increasingTriplet(int[] nums) {
             int[] dp= new int[3];
             int len=0;
             for(int n: nums){
                 // returns -1 * (insertion point - 1) 
                 int index= Arrays.binarySearch(dp,0,len, n);
                 if(index<0){
                     index=-(index+1); // to get insertion index;
                 }
              
                 dp[index]= n;

                 if(index == len)               
                     len++;
                 if(len==3) return true;
             }
             return false;
         }
     }



    public boolean increasingTriplet(int[] nums) {
        int len = nums.length; 
        if (len <= 2) return false; 
        
        TreeSet<Integer> set = new TreeSet<>();
        
        // Patience sort nlogn  
        for (int i=0; i<len; i++) { 
            Integer x = set.ceiling(nums[i]);
            
            if (x != null) { 
                set.remove(x);
            }
            
            set.add(nums[i]);
            
            if (set.size() >= 3) return true;
        }
        
        return false; 
    }
}
