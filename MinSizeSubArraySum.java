

/** 
 * @link https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Sliding window solution
 *
 */ 

public class MinSizeSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int len = Integer.MAX_VALUE; // curr length is maximum 
        int running = 0;  // running sum 
        int j = 0;  // window start index
        
        for (int i=0; i<nums.length; i++) { 
            running += nums[i];
            
            // if running is greater or equal to target 
            // update current len and select the min ont
            while(running >= target) {
                len = Math.min(len, i-j+1);
                running -= nums[j++];
            }
        }
        
        return (len != Integer.MAX_VALUE) ? len : 0; 
    }
}
