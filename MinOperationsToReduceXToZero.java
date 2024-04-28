
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

/** 
 * Using PrefixSum 
 * solution is similar to sliding window 
 * we get size of window through getting the value of target 
 * using the hashmap used to store prefix sum 
 */ 
class Solution {
    public int minOperations(int[] arr, int x) {
        int n = arr.length; 
        int target = -1 * x; // inner arr sum xxx|INNER ARR|xx

        for (int val : arr) { 
            target += val; 
        }

        if (target <= 0) return target == 0 ? n : -1; 

        Map<Long, Integer> map = new HashMap<>(); // prefix map

        map.put(0L, -1); // 0 sum goes to -1 index, to become -(-1) in (a)

        int ans = 0; 
        long sum = 0; 

        for (int i=0; i<n; i++) { 
            sum += arr[i]; 
            long diff = sum - target; 

            if (map.containsKey(diff)) { // if diff exist update ans
                ans = Math.max(ans, i - map.get(diff)); // step-(a)
            }

            map.put(sum, i); // add the sum to prefix map
        }

        return ans == 0 ? -1 : n-ans; 
    }
}
