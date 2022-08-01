/*

 @link https://leetcode.com/problems/letter-combinations-of-a-phone-number/


 Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 
Example 1:
    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
    Input: digits = ""
    Output: []

Example 3:
    Input: digits = "2"
    Output: ["a","b","c"]

Constraints:
    0 <= digits.length <= 4
    digits[i] is a digit in the range ['2', '9'].
*/




class Solution {
    private String[] map = new String[]{
        "", "", 
        "abc", "def", "ghi", 
        "jkl", "mno", "prqs", 
        "tuv", "wxyz"
    }; 
    
    private List<String> ans = new ArrayList<>(); 

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return ans; 
        solve(0, digits, new StringBuilder());
        return ans; 
    }
    
    private void solve(int idx, String digits, StringBuilder s) {
        if (idx == digits.length()) { 
            ans.add(s.toString());
            return;
        }
        
        String mapStr = map[digits.charAt(idx)-'0']; 

        for (int j=0; j<mapStr.length(); j++) { 
            s.append(mapStr.charAt(j));
            solve(idx+1, digits, s);
            s.deleteCharAt(s.length()-1);
        }
    }
}

