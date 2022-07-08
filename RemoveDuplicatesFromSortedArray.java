/**
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-array
 */
class Solution {
    public int removeDuplicates(int[] arr) {
        int w = 1; 
        
        for (int i=1; i<arr.length; i++) { 
            if (arr[i] != arr[i-1]) { 
                arr[w++] =  arr[i]; 
            }
        }
        
        return w; 
    }

    /** Generalization using sliding window */ 

    public int removeDuplicates(int[] arr) {
        int k = 1
        int w = k; 
        
        for (int i=k; i<arr.length; i++) { 
            if (arr[i] != arr[w-k]) { 
                arr[w++] =  arr[i]; 
            }
        }
        
        return w; 
    }
}

