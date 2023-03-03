/*
 @link https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string
 @categories (two-pointers)

 Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.

Constraints:
    1 <= haystack.length, needle.length <= 10^4
    haystack and needle consist of only lowercase English characters.
*/ 

/**
 * At the end letter by letter scan
 * This solution is moving window
 */ 

class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == m - 1) {
                    return windowStart;
                }
            }
        }

        return -1;
    }
}

class Solution {
    public int strStr(String heystack, String needle) {
        int window = needle.length(); 

        for (int i=0; i+window<=heystack.length(); i++) { 
            if (heystack.substring(i, i+window).equals(needle)) return i; 
        }

        return -1; 
    }
}


/** letter by letter */
class Solution {
    public int strStr(String heystack, String needle) {
        int n = heystack.length(); 
        int need = needle.length(); 

        char[] hs = heystack.toCharArray(); 
        char[] nd = new char[128];

        for (char c : needle.toCharArray()) { 
            nd[c] += 1; 
        }

        int left = 0, right = 0; 
        int have = 0; 

        while (right < n) { 
            if (--nd[hs[right]] >= 0) { 
                have += 1; 
            }

            if (have >= need) { 
                int size = right - left + 1; 
                if (size == need) { 
                    if (needle.equals(heystack.substring(left, right+1))) return left;
                }
                if (++nd[hs[left]] > 0) have--; 
                left += 1; 
            }

            right += 1; 
        }

        return -1; 
    }
}
