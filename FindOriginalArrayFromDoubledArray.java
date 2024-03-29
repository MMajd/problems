/*
  
 @link https://leetcode.com/problems/find-original-array-from-doubled-array/

 An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.

Example 1:
    Input: changed = [1,3,4,2,6,8]
    Output: [1,3,4]
    Explanation: One possible original array could be [1,3,4]:
    - Twice the value of 1 is 1 * 2 = 2.
    - Twice the value of 3 is 3 * 2 = 6.
    - Twice the value of 4 is 4 * 2 = 8.
    Other original arrays could be [4,3,1] or [3,1,4].

Example 2:
    Input: changed = [6,3,0,1]
    Output: []
    Explanation: changed is not a doubled array.

Example 3:
    Input: changed = [1]
    Output: []
    Explanation: changed is not a doubled array.

Constraints:
    1 <= changed.length <= 105
    0 <= changed[i] <= 105
 */


class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length; 
        
        if (n % 2 != 0) return new int[0]; 
        Map<Integer, Integer> freq = new TreeMap<>(); 
        
        for (int i : changed) freq.put(i, freq.getOrDefault(i, 0) + 1);
        
        int i = 0; 
        int[] ans = new int[n/2]; 
        
       for (int x : freq.keySet()) {
            if (freq.get(x) > freq.getOrDefault(x<<1, 0))
                return new int[0];
            for (int j = 0; j < freq.get(x); ++j) {
                ans[i++] = x;
                freq.put(x<<1, freq.get(x<<1) - 1);
            }
        }
 
        return ans; 
    }
}
