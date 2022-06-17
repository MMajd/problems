
/** 
 *
 */ 

class Solution {
    /** Time: O(n), Storage: O(1) */
    public int productExceptSelf(int[] nums) {
        if (nums.length <= 1) return nums; 
        
        int len = nums.length; 
        int[] ans = new int[len];
        int curr = 1; 
        
        Arrays.fill(ans, 1);
        
        for (int i=1; i<len; i++) { 
            ans[i] = curr * nums[i-1];
            curr *= nums[i-1];
        }
        
        curr = 1; 
        for (int i=len-2; i>=0; i--) { 
            ans[i] *= curr * nums[i+1]; 
            curr *= nums[i+1]; 
        }
        
        return ans;
    }

    /** Time: O(n), Storage: O(n) */
    public int[] productExceptSelf1(int[] nums) {
        if (nums.length <= 1) return nums; 
        
        int len = nums.length; 
        int[] ans = new int[len]; 
        int[] prefix = new int[len]; 
        int[] suffix = new int[len];
        
        prefix[0] = 1; 
        suffix[len-1] = 1; 
        
        for (int i=1; i<len; i++) {
            prefix[i] = nums[i-1] * prefix[i-1];
        }
        
        for (int i=len-2; i>=0; i--) { 
            suffix[i] = nums[i+1] * suffix[i+1]; 
        }
        
        for (int i=0; i<len; i++) { 
            ans[i] = prefix[i] * suffix[i]; 
        }
        
        return ans; 
    }
}
