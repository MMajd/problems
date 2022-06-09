
/** 
 *
 *
 * @link https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 **/

public class TwoSum2InputSortedArray { 
    class Solution {
        public int[] twoSum(int[] arr, int target) {
            if (arr.length == 2) return new int[]{1, 2}; 
            
            int start = 0, end = arr.length-1; 
            
            while(arr[start] + arr[end] != target) { 
                if (arr[start] + arr[end] < target) start ++; 
                else -- end; 
            }

            return new int[] {start+1, end+1};
        }
    }
}
