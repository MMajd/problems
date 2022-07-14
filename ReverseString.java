
/**
 *
 * @link https://leetcode.com/problems/reverse-string/
 *
 *
 */

class Solution {
    public void reverseString(char[] s) { 
        reverse(s, 0); 
    }

    public void reverse(char[] s, int i) { 
        if (i >= s.length/2) return;
        reverse(s, i+1); 
        swap(s, s.length-1-i, i); 
    }

    public void reverseStringIterative(char[] s) {
        int i = 0; 
        int j = s.length-1; 
        
        while(i < j) { 
            swap(s, i++, j--);
        }
    }

    public void swap(char[] s, int i, int j) { 
        char x = s[i]; 
        s[i]  = s[j]; 
        s[j] = x; 
    }
}
