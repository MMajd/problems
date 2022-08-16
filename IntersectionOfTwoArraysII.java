/*

 @link https://leetcode.com/problems/intersection-of-two-arrays-ii/


 Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

 

Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2,2]

Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [4,9]
    Explanation: [9,4] is also accepted.
 

Constraints:
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000

*/


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length<nums2.length) { 
            int[] temp = nums1; 
            nums1 = nums2;
            nums2 = temp;
        }
        
        int[] dp = new int[1001]; 
        int[] ans = new int[nums2.length]; 
        int counter = 0; 
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        for (int i : nums2) { 
            dp[i]++; 
        }
        
        for (int i : nums1) { 
            if (dp[i] != 0) { 
                ans[counter++] = i; 
                dp[i]--; 
            }
        }
        
        /* binary search 
         * either to use hashmap or to erease 
         * found numbers and re-sort the array which is has high cost 
         */
        
        // for (int t : nums1) { 
        //     int mid = bsearch(nums2, nums1.length, t); 
        //     if (mid != -1) {
        //         if (dp[nums1[mid]] != 0) {
        //             ans[counter++] = t; 
        //             dp[nums1[mid]]--; 
        //         }
        //     }
        // }
        
        return Arrays.copyOfRange(ans, 0, counter);
    }
    
    private int bsearch(int[] arr, int length, int target) { 
        int start=0, end=length-1; 
        
        while(start<=end) {
            while(start<end && arr[start]==arr[start+1]) start+=1; 
            while(end>start&& arr[end]==arr[end-1]) end-=1; 
            
            int mid = start+(end-start)/2;
            
            if (target == arr[mid]) return mid;  
            if (target > arr[mid]) start=mid+1; 
            else end=mid-1; 
        }
        
        return -1; 
    }
}
