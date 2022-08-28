/*
 @link https://leetcode.com/problems/contains-duplicate-ii/
   
 Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

 
Example 1:
    Input: nums = [1,2,3,1], k = 3
    Output: true

Example 2:
    Input: nums = [1,0,1,1], k = 1
    Output: true

Example 3:
    Input: nums = [1,2,3,1,2,3], k = 2
    Output: false

Constraints:
    1 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    0 <= k <= 10^5
*/

class Solution {
    public boolean containsNearbyDuplicate(int[] arr, int k) {
        Set<Integer> set = new HashSet<>(); 
        
        for (int i=0; i<arr.length; i++) { 
            if (i > k) set.remove(arr[i-k-1]);
            // add returns false if element already exist in the set 
            if (!set.add(arr[i])) return true;  
        } 
        
        return false; 
    }
    

    public boolean containsNearbyDuplicate2(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>(); 
        
        for (int i=0; i<arr.length; i++) {
            if (map.containsKey(arr[i])) { 
                int idx = map.get(arr[i]); 
                if (i-idx <= k) return true; 
            }
            map.put(arr[i], i);
        }
        
        return false; 
    }
}
