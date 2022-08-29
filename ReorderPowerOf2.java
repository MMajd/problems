/** 
 @link https://leetcode.com/problems/reordered-power-of-2/solution/

 You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this so that the resulting number is a power of two.

Example 1:
    Input: n = 1
    Output: true

Example 2:
    Input: n = 10
    Output: false

Constraints:
    1 <= n <= 10^9
*/




/** Using counting to test if there's a  power of 2 that consist of the same digits as the number n under test */
class Solution {
    public boolean reorderedPowerOf2(int n) {
        int[] digits = count(n); 
        for (int i=0; i<32; i++) {
            if (Arrays.equals(digits, count(1<<i))) 
                return true; 
        }
        
        return false; 
    }
    
    private int[] count(int n) {
        int[] ans = new int[10]; 
        
        while(n>0) { 
            ans[n%10] += 1; 
            n/=10; 
        }
        
        return ans; 
    }
}

/** USING Backtracking */
class Solution {
    public boolean reorderedPowerOf2(int N) {
        String S = Integer.toString(N);
        int[] A = new int[S.length()];
        for (int i = 0; i < S.length(); ++i)
            A[i] = S.charAt(i) - '0';
        return permutations(A, 0);
    }

    public boolean isPowerOfTwo(int[] A) {
        if (A[0] == 0) return false;
        int N = 0;
        for (int x: A)
            N = 10 * N + x;

        while (N > 0 && ((N & 1) == 0))
            N >>= 1;

        return N == 1;
    }

    public boolean permutations(int[] A, int start) {
        if (start == A.length)
            return isPowerOfTwo(A);

        for (int i = start; i < A.length; ++i) {
            swap(A, start, i);

            if (permutations(A, start + 1))
                return true;
            swap(A, start, i);
        }

        return false;
    }

    public void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
