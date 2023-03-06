/* 
  @link leetcode.com/problems/kth-largest-element-in-an-array

  Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

*********
* NOTE  You must solve it in O(n) time complexity.
*********

Example 1:
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5

Example 2:
    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4
 
Constraints:
    1 <= k <= nums.length <= 105
    -104 <= nums[i] <= 104
  
 */

/** Solution 1: Using quick select */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length-1, nums.length-k);
    }
    
    private int quickselect(int[] nums, int left, int right, int k) {
        int paritionIdx = parition(nums, left, right);
        
        if (paritionIdx > k) {
            return quickselect(nums, left, paritionIdx-1, k);
        }
        
        if (paritionIdx < k) { 
            return quickselect(nums, paritionIdx+1, right, k);
        }
        
        return nums[k]; 
    }
    
    private int parition(int nums[], int left, int right) {
        int pivot = nums[right]; 
        
        int j=left;
        for (int i=left; i<right; i++) { 
            if (pivot > nums[i]) { 
                swap(nums, i, j++);
            }
        }
        
        swap(nums, right, j);
        return j; 
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}


class KthLargestElementInArray { 
    public int findKthLargest(int[] nums, in k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k+1);

        for (int i : nums) { 
            q.add(i);

            if (q.size() > k) q.poll();
        }

        return q.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(
            k,
            (a, b) -> b.compareTo(a)
        );
        
        int counter = 0; 
        
        for (int i : nums) { 
            q.add(i);
        }
        
        for (int i=k-1; i>=1; i--) q.poll();
        
        return q.peek();
    }
}

