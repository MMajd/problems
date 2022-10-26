/* 
 @link https://leetcode.com/problems/minimum-window-substring/
 @categories (sliding-window/hash-table/string) 

 Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Example 1:
    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
    Input: s = "a", t = "a"
    Output: "a"
    Explanation: The entire string s is the minimum window.

Example 3:
    Input: s = "a", t = "aa"
    Output: ""
    Explanation: Both 'a's from t must be included in the window.
    Since the largest window of s only has one 'a', return empty string.

Constraints:
    m == s.length
    n == t.length
    1 <= m, n <= 105
    s and t consist of uppercase and lowercase English letters.
 */

class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(); 
        int n = t.length(); 
        
        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> tmap = new HashMap<>();
        
        for (int i=0; i<n; i++) { 
            tmap.compute(t.charAt(i), (k, v) -> v == null ? 1 : ++v);
        }
        
        int j = 0; 
        int have = 0, need = tmap.size();
        int start=-1, end=-1, minLen = Integer.MAX_VALUE; 
        
        for (int i=0; i<m; i++) {
            char x = s.charAt(i);
            smap.compute(x, (k,v) -> v == null ? 1 : ++v);
            
            
            if (tmap.containsKey(x) && smap.get(x).intValue() == tmap.get(x).intValue()) {
                have += 1; 
            }
            
            while (have == need) { 
                int size = i-j+1; 
                
                if (size < minLen) { 
                    minLen = size; 
                    start = j; 
                    end = i; 
                }
                
                char y = s.charAt(j++);
                smap.compute(y, (k, v) -> --v);
                
                if (tmap.containsKey(y) && smap.get(y).intValue() < tmap.get(y).intValue()) { 
                    have -= 1;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end+1);
    }
}
