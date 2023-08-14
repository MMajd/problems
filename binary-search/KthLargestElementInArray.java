/**
 @link leetcode.com/problems/kth-largest-element-in-an-array
 @categories(min-heap/quick-select)

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
    1 <= k <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
 */

/** Solution 1: Using quick select */
class Solution {
    public int findKthLargest(int[] A, int k) {
        int n = A.length; 
        return qselect(A, 0, n-1, n-k); 
    }

    private int qselect(int[] A, int left, int right, int k) {
        int part = partition(A, left, right);
        if (part > k) return qselect(A, left, part-1, k);
        if (part < k) return qselect(A, part+1, right, k);
        return A[part]; 
    }

    private int partition(int[] A, int left, int right) {
        int j = left;
        for (int i=left; i<right; i++) { 
            if (A[i] < A[right]) { 
                swap(A, j++, i); 
            }
        }

        swap(A, right, j);
        return j; 
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i]; 
        A[i] = A[j]; 
        A[j] = temp; 
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

