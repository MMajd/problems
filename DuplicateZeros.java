/** 

@link https://leetcode.com/problems/duplicate-zeros

Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.

 

Example 1:

Input: arr = [1,0,2,3,0,4,5,0]
Output: [1,0,0,2,3,0,0,4]
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]


*/ 

/** USING QUEUE */ 
class Solution {
    public void duplicateZeros(int[] arr) {
        Queue<Integer> q = new ArrayDeque<>(); 
        
        for (int i=0; i<arr.length; i++) { 
            q.add(arr[i]);
            
            if (arr[i] == 0) { 
                q.add(0);
            }
            
            arr[i] = q.poll();
        }
    }
}

/** USING TWO POINTERS */
class Solution {
    public void duplicateZeros(int[] arr) {
        int zeros = 0; 
        for (int i : arr) if (i == 0) zeros += 1; 
        
        int i = arr.length-1; 
        int j = arr.length + zeros - 1; 
        
        while (i < j) { 
            duplicate(arr, i, j--); 
            
            if (arr[i] == 0) { 
                duplicate(arr, i, j--); 
            }
            
            i -= 1; 
        }
    }
    
    private void duplicate(int[] arr, int i, int j) {
        if (j >= arr.length) return; 
        
        arr[j] = arr[i]; 
    }
}

