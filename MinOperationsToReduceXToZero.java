
/** 
 * @link  https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * Sliding window solution
 *
 **/

class Solution {
    /** 
        we convert the question form finding the minimum number of left and/or right most elements that constitute x 
        to finding the subarray with maximum length that equal to target (sum - x) 
        if its then arr.length - subarray.length give us our answer

        NOTE: all numbers in arr are positive 
    */
    public int minOperations(int[] arr, int x) {
        int target = Arrays.stream(arr).sum() - x;  // target subarray with sum equal to sum(nums) - x; 
        
        if (target < 0) return -1; // x > sum(arr) 
        if (target == 0) return arr.length;  // x = sum(arr)
        
        int ws = 0; // window start; 
        int running = 0; // running sum; 
        int subArrayLen = 0; 
        
        for (int i=0; i<arr.length; i++) { 
            running += arr[i]; 
            
            while(running >= target) { 
                if (running == target) { 
                    subArrayLen = Math.max(subArrayLen, i-ws+1);
                }
                running -= arr[ws++]; 
            }
        }
        
        return (subArrayLen == 0) ? -1 : arr.length - subArrayLen;  
    }
}
