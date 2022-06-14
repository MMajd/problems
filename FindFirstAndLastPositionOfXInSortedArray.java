/** 
 *
 * @link https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length; 
        int idx1 = -1; 
        int idx2 = -1; 
        
        if (len == 0) { 
            return new int[]{idx1, idx2}; 
        }
        
        if (len == 1) { 
            idx1 = nums[0] == target ? 0 : -1; 
            return new int[]{idx1, idx1}; 
        }
        
        int left = 0;
        int right = len-1; 

        while (right >= left) { 
            int mid = left + (right - left) / 2; 
            
            if (target == nums[mid]) { 
                left = mid-1;; 
                while (left >= 0 && target == nums[left]) left--;
                
                idx1 = left+1; 
                
                right = mid+1; 
                while (right <= len-1 && target == nums[right]) right++;
                idx2 = right-1; 
                
                break;
            }
            else if (target > nums[mid]) { 
                left = mid + 1; 
            } else { 
                right = mid - 1; 
            }
        }
        
        
        return new int[] {idx1, idx2}; 
    }
}
