/**
 *
 * @link https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
 *
 * Array may contain duplicates 
 * Array may have 0 or n rotation, meaning its non-decreasing
 *
 */ 

class Solution {
    public int findMin(int[] arr) {
        int N = arr.length; 
        int start=0, end=N-1; 
        
        if (N == 1) return arr[0]; 
        if (N == 2) return arr[0] > arr[1]? arr[1] : arr[0]; 
        if (arr[start]<arr[start+1] && arr[start]<arr[end]) return arr[0]; 
        
        while(start<end) {
            while(start<end && arr[start]==arr[start+1]) start+=1; 
            while(end>start && arr[end]==arr[end-1]) end-=1; 
            
            int mid = start+(end-start)/2; 
            if (arr[end]>arr[mid]) end=mid; 
            else start=mid+1; 
        }
        
        return arr[end]; 
    }
}
