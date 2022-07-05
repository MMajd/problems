
/**
 *
 * @link https://leetcode.com/problems/reverse-string/
 *
 *
 */

class Solution {
    public void reverseString(char[] s) {
        int i = 0; 
        int j = s.length-1; 
        
        while(i < j) { 
            char x = s[i]; 
            s[i]  = s[j]; 
            s[j] = x; 
            i++; 
            --j; 
        }
    }
}
