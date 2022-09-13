/*
  
 @link https://leetcode.com/problems/largest-number/
  
 Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Example 1:
    Input: nums = [10,2]
    Output: "210"

Example 2:
    Input: nums = [3,30,34,5,9]
    Output: "9534330"

Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 10^9

*/


class Solution {
    public String largestNumber(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        
        for (int i : nums) list.add(i);
        
        Collections.sort(list, (a, b) -> greater(a, b));
        
        if (list.get(0) == 0) return "0"; 
        
        StringBuilder sb = new StringBuilder(); 
        
        for (Integer i : list) sb.append(Integer.toString(i));

        return sb.toString();
    }
    
    
    public int greater(Integer x, Integer y) {
        String o1 = Integer.toString(x);
        String o2 = Integer.toString(y);
        
        String other1 = o1+o2; 
        String other2 = o2+o1; 
        
        return other2.compareTo(other1);
    }
}
