/*

 @link leetcode.com/problems/generate-parentheses

 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 This is a catalan number problem
 

Example 1:
    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
    Input: n = 1
    Output: ["()"]

Constraints:
    1 <= n <= 8

*/

class Solution {
    List<String> ans = new LinkedList<>(); 
    
    public List<String> generateParenthesis(int n) {
        char[] str = new char[n*2]; 
        bt(0, 0, n, str, 0);
        return ans; 
    }
    
    private void bt(int left, int right, int n, char[] s, int i) { 
        if (i == 2*n) { 
            ans.add(String.copyValueOf(s));
            return;
        }
        
        if (left < n) { 
            s[i] = '('; 
            bt(left+1, right, n, s, i+1); 
        }
        
        if (right < left) { 
            s[i] = ')'; 
            bt(left, right+1, n, s, i+1); 
        }
    }
}
