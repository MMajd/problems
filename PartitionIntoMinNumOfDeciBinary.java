/**
 *
 * @link https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/ 
 *
 *
 * Look at the input constraints 
 * Constraints:
     * 1 <= n.length <= 105
     * n consists of only digits.
     * n does not contain any leading zeros and 
     * represents a positive integer.
     * 
 */ 

class Solution {
    public int minPartitions(String n) {
        int ans = 0; 
        for (char c : n.toCharArray()) {
            if (c - '0' == 9) return 9; 
            ans = Math.max(ans, c - '0');
        }
        
        return ans; 
    }
}
