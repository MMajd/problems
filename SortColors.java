
/** 
 *
 * Sort colors is basically
 *
 * @link https://leetcode.com/problems/sort-colors
 *
 */


public class SortColors { 
    class Solution {
        public void sortColors(int[] nums) {
            int pivot = 1; 
            int smaller = 0, equal = 0, larger = nums.length; 
            
            while(equal < larger) { 
                if (pivot > nums[equal]) { 
                    swap(nums, smaller++, equal++);
                }
                else if (pivot == nums[equal]) ++equal; 
                else { 
                    swap(nums, equal, --larger);
                }
            }
        }
        
        private void swap(int[] arr, int i, int j) { 
            int temp = arr[i]; 
            arr[i] = arr[j]; 
            arr[j] = temp; 
        }
    }
}
