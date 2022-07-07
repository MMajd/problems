/** 
@link https://leetcode.com/problems/check-if-n-and-its-double-exist/

Given an array arr of integers, check if there exists two integers N and M 
such that N is the double of M (i.e. N = 2 * M).
More formally check if there exists two indices i and j such that:

1. i != j
2. 0 <= i, j < arr.length
3. arr[i] == 2 * arr[j]
 

Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.

Example 2:

Input: arr = [7,0,4,11]
Output: false


Example 3:

Input: arr = [0, 0]
Output: true
Explanation: N = 0 is the double of M = 0


*/ 

class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>(); 
        
        for (int i : arr) { 
            if (set.contains(i*2) || (i%2==0 && set.contains(i/2))) return true; 
            set.add(i);
        }
        
        return false; 
    }
}

