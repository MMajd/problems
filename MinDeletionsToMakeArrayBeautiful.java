/** 
 *
 * @link https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/
 *
 */ 
class Solution {
    public int MinDeletion(int[] nums) {
        int ans = 0 ; 
        for (int i=0;i<nums.length-1; i+=2) { 
            if (nums[i]==nums[i+1]) { 
                ans += 1;
                i -= 1; 
            }
        }
        
        return (nums.length-ans)%2==1?++ans:ans;
    }
    
    public int minDeletion(int[] nums) { 
        int ans = 0; 
        
        for (int i=0; i<=nums.length-2; i++) { 
            if (nums[i]==nums[i+1] && (i-ans)%2==0) { 
                ans += 1; 
            }
        }
        return (nums.length-ans)%2==1?++ans:ans; 
    }
}
