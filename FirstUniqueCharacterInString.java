/*
  
 @link https://leetcode.com/problems/first-unique-character-in-a-string/
  

 Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

Example 1:
    Input: s = "leetcode"
    Output: 0

Example 2:
    Input: s = "loveleetcode"
    Output: 2

Example 3:
    Input: s = "aabb"
    Output: -1
 
Constraints:
    1 <= s.length <= 10^5
    s consists of only lowercase English letters.
  
 */






class Solution {
    public int firstUniqChar(String s) {
        int ans = s.length(); 
        
        for (char c='a'; c<='z'; c++) { 
            int idx = s.indexOf(c);
            if (idx!=-1 && idx==s.lastIndexOf(c)) {
                ans = Math.min(ans, idx);
            }
        }
        
        return ans < s.length() ? ans : -1; 
    }
    
    public int firstUniqChar2(String s) {
        int[] chars = new int[26]; 
        
        for (int i=0; i<s.length(); i++) { 
            chars[s.charAt(i)-'a']++; 
        }
        
        
        for (int i=0; i<s.length(); i++) { 
            if (chars[s.charAt(i)-'a'] == 1) return i; 
        }
        
        return -1; 
    }
}
