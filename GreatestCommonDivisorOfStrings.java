/*
 @linkhttps://leetcode.com/problems/greatest-common-divisor-of-strings
 @categories (math/strings)

 For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

Example 1:
    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"

Example 2:
    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"

Example 3:
    Input: str1 = "LEET", str2 = "CODE"
    Output: ""

Constraints:
    1 <= str1.length, str2.length <= 1000
    str1 and str2 consist of English uppercase letters.
*/

class Solution {
    public String gcdOfStrings(String s1, String s2) {
        /**
         * Simple proof 
         * s1 is divisable by s2, that means s2 is a part of s1
         * and s1 is s2 repeated t times 
         * if that's the case 
         * then if we append s2 to s1 (s1s2) and append s1 to s2 (s2s1) we should get the same string 
         * if they produce different string then there's no gcd  
         * if they produce same string then the gcd is the gcd between their lengths 
         * why? because of s1, and s2 are repeation of some other string s3, we should find s3 length 
         * and that's the greatest common divisor  
         */

         if (!(s1+s2).equals(s2+s1)) return ""; 
         int x = Math.max(s1.length(), s2.length());
         int y = Math.min(s1.length(), s2.length());

         int len = gcd(x, y);

         return s1.substring(0, len);
    }

    private int gcd(int x, int y) {
        if (y == 0) return x; 
        return gcd(y, x%y);
    }
}

