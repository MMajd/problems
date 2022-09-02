/*  
  @link https://leetcode.com/problems/longest-substring-without-repeating-characters/
  
  Given a string s, find the length of the longest substring without repeating characters.

Example 1:
    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.

Example 2:
    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

Example 3:
    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.
 */ 

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[127];
        int i = 0; 
        int j = 0; 
        int ans = 0; 
        
        while(i<s.length()) { 
            int code = (int) s.charAt(i);
            if (chars[code] != 0) { 
                j = Math.max(chars[code], j);
            }
            
            ans = Math.max(ans, i-j+1);
            chars[code] = ++i; 
        }
        
        return ans; 
    }
}

/** With HashMap */
public class Solution { 
    public int lengthOfLongestSubstring(String s) { 
        Map<Character, Integer> map = new HashMap<>(); 
        int maxlen = 0; 
        int j = 0; 

        for (int i=0; i<s.length(); i++) { 
            if (map.containsKey(s.charAt(i)) { 
                j = Math.max(j, map.get(s.charAt(i)));
            }

            maxlen = Math.max(maxlen, i-j+1); 
            map.put(s.charAt(i), i+1); 
        }


        return maxlen; 
    }
}
