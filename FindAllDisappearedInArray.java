/** 

@link https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

 

Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:

Input: nums = [1,1]
Output: [2]

*/

class Solution {
    public List<Integer> findDisappearedNumbers(int[] arr) {
        List<Integer> list = new LinkedList<>();
        
        int[] count = new int[arr.length+1]; 
        
        for (int i : arr) count[i]+=1; 
            
        
        for (int i=1; i<=arr.length; i++) { 
            if (count[i] == 0) list.add(i);
        }
        
        return list;
    }
}
