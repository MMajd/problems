/** 
 
 @link https://leetcode.com/problems/intersection-of-two-arrays/
  
 Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.
 

Constraints:
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000
  
*/ 







class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length<nums2.length) { 
            int[] temp = nums1; 
            nums1 = nums2;
            nums2 = temp;
        }
        
        int[] ans = new int[nums2.length]; 
        int counter = 0; 
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        for (int t : nums2) { 
            if (bsearch(nums1, nums1.length, t) != -1) {
				// if t doesn't exist in ans then add add it 
                if (bsearch(ans, counter, t) == -1) ans[counter++] = t; 
            }
        }
        
        return Arrays.copyOfRange(ans, 0, counter);
    }
    
    private int bsearch(int[] arr, int length, int target) { 
        int start=0, end=length-1; 
        
        while(start<=end) {
			// skip duplicates if they exist 
            while(start<end && arr[start]==arr[start+1]) start++; 
            while(end>start && arr[end]==arr[end-1]) end--; 
            
            int mid = start+(end-start)/2;
            
            if (target == arr[mid]) return mid;  
            if (target > arr[mid]) start=mid+1; 
            else end=mid-1; 
        }
        
        return -1; 
    }
}
