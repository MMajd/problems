/*
 @link https://leetcode.com/problems/unique-number-of-occurrences
 @categories (arrays/hash-table/mapping) 
 
 Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.

Example 1:
    Input: arr = [1,2,2,1,1,3]
    Output: true
    Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.

Example 2:
    Input: arr = [1,2]
    Output: false

Example 3:
    Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
    Output: true

Constraints:
    1 <= arr.length <= 1000
    -1000 <= arr[i] <= 1000

*/

class Solution {
    public boolean uniqueOccurrences(int[] A) {
        int min = Integer.MAX_VALUE; 
        int max = Integer.MIN_VALUE; 

        for (int i : A) {
            min = Math.min(i, min);
            max = Math.max(i, max); 
        }

        int[] freq = new int[max-min+1]; 
        int[] freq2 = new int[50]; // 1+2+....+50 -> ~1270  

        for (int i : A) { 
            freq[i-min] += 1; 
        }

        for (int i : freq) {
            if (i != 0 && freq2[i] != 0)  return false; 
            freq2[i] += 1; 
        }

        return true; 
    }
}


class Solution {
    public boolean uniqueOccurrences(int[] A) {
        if (A.length == 2) return A[0] == A[1]; 

        Arrays.sort(A);

        int[] freq = new int[51]; 

        int cnt = 0; 

        for (int i=1; i<A.length; i++) { 
            cnt += 1; 

            if (A[i] != A[i-1]) {
                if (freq[cnt] != 0) return false; 
                freq[cnt] = 1; 
                cnt = 0; 
            }
        }

        return true; 
    }
}
