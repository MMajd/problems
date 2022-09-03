/*
 
 @link https://leetcode.com/problems/numbers-with-same-consecutive-differences/

 Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.

Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.

You may return the answer in any order.

Example 1:
    Input: n = 3, k = 7
    Output: [181,292,707,818,929]
    Explanation: Note that 070 is not a valid number, because it has leading zeroes.

Example 2:
    Input: n = 2, k = 1
    Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Constraints:
    2 <= n <= 9
    0 <= k <= 9
*/


class Solution {
    List<Integer> list = new ArrayList<>(); 
    
    public int[] numsSameConsecDiff(int n, int k) {
        solve(new int[n], 0, k);

        int[] arr = new int[list.size()];
        
        for (int i=0; i<arr.length; i++) 
            arr[i] = list.get(i);
        
        return arr; 
    }
    
    private void solve(int[] digits, int idx, int k) { 
        if (digits.length == idx) {
            int num = 0; 
            for (int i=0; i<digits.length; i++) { 
                num = num * 10 + digits[i];
            }
            
            list.add(num);
            return; 
        }
        
        for (int i=0; i<=9; i++) {
            if (idx == 0 && i > 0 
                || idx > 0 && Math.abs(digits[idx-1] - i) == k) { 
                digits[idx] = i; 
                solve(digits, idx+1, k);
            }
        }
    }
}

