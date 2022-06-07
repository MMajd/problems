
/** 
 * Problem
 * @link https://leetcode.com/problems/merge-sorted-array/
 *
 * Solution: 
 * nums1 have zero elements after pos m-1, 
 * we can use these places to merge the two arrays 
 *
 * after done comparing, make sure that there's no element remaining in nums2
 * second while loop 
 */

public class MergeSortedArraysInPlace {
    public static void main(String[] args) {} 

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int last = m+n-1;
            m--; n--; 
            
            while(m >= 0 && n >= 0) {
                if (nums2[n] > nums1[m]) nums1[last--] = nums2[n--]; 
                else nums1[last--] = nums1[m--];
            }
            
            while(n >= 0) nums1[last--] = nums2[n--];
        }
    }

}
