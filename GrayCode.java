/*

 @link https://leetcode.com/problems/gray-code/

An n-bit gray code sequence is a sequence of 2n integers where:

Every integer is in the inclusive range [0, 2n - 1],
  The first integer is 0,
  An integer appears no more than once in the sequence,
  The binary representation of every pair of adjacent integers differs by exactly one bit, and
  The binary representation of the first and last integers differs by exactly one bit.
  Given an integer n, return any valid n-bit gray code sequence.

Example 1:
    Input: n = 2
    Output: [0,1,3,2]
    Explanation:
    The binary representation of [0,1,3,2] is [00,01,11,10].
    - 00 and 01 differ by one bit
    - 01 and 11 differ by one bit
    - 11 and 10 differ by one bit
    - 10 and 00 differ by one bit
    [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
    - 00 and 10 differ by one bit
    - 10 and 11 differ by one bit
    - 11 and 01 differ by one bit
    - 01 and 00 differ by one bit

Example 2:
    Input: n = 1
    Output: [0,1]
   

Constraints:
    1 <= n <= 16

*/

/** 
 * This is solution based on converting from binary to graycode 
 * property that the graycode is just the binary xored with its 
 * right shifted version
 *
 *
 *     abcd  
 * Ex: 0110 , binary 
 * RS: 0011 , right shifted binary 
 * GC: 0111 , graycode encoding
 *
 * and the binary can be generated from gray code
 * for the ith bit by taking xor of all previous bits 
 *
 * ith bit in binary = [(i-1)bit xor (i-2)bit xor ... xor 0bit]
 *
 *
 * */
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();  
        
        for (int i=0; i<(1<<n); i++) { 
            ans.add(i ^ (i>>1));
        }
        
        return ans; 
    }
}

/** 
 * backtracking approach 
 *
 * Here we manipulate every and each bit in the range 
 * of [0,n-1] bits, that our graycode consist of, 
 *
 * We either use the bit as it's or backtrack and 
 * invert it and use it, need to prove the correctness of this algo
 *
 * */
class Solution {
    int num = 0; 
    public List<Integer> grayCode(int n) {
        return grayCodes(n);
    }

    private void grayCodeUtil(List<Integer> res, int n) {
        if (n == 0) {
            System.out.println("Adding: " + num);
            res.add(num);
            return;
        }

        System.out.println("Going IN: " + (n-1) + ", Num: " + num);
        grayCodeUtil(res, n - 1);

        num = num ^ (1 << (n - 1));
        
        System.out.println("Backtracking : " + (n-1) + ", Num: " + num);
        
        grayCodeUtil(res, n - 1);
    }

    private List<Integer> grayCodes(int n) {
        List<Integer> res = new LinkedList<Integer>();
        grayCodeUtil(res, n);
        return res;
    }

}
