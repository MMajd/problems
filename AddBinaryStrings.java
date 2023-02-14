/* 
 @link https://leetcode.com/problems/add-binary
 @categroies (math/bit-manipluation/simulation) 

 Given two binary strings a and b, return their sum as a binary string.

Example 1:
    Input: a = "11", b = "1"
    Output: "100"

Example 2:
    Input: a = "1010", b = "1011"
    Output: "10101"

Constraints:
    1 <= a.length, b.length <= 10^4
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself.
 */ 

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder s = new StringBuilder(); 
        int m = a.length()-1; 
        int n = b.length()-1; 
        int carry = 0; 
        
        while(m >= 0 || n>= 0) { 
            int sum = carry; 
            
            if (m >= 0) sum += a.charAt(m--) - '0'; 
            if (n >= 0) sum += b.charAt(n--) - '0'; 
            
            s.append(sum % 2); 
            carry = sum / 2; 
        }
        
        if (carry == 1) s.append('1');
        
        return s.reverse().toString(); 
    }
}
