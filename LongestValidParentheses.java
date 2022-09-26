/*
 @link https://leetcode.com/problems/longest-valid-parentheses
 @categories (dp/stack/string) 

 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.


Example 1:
    Input: s = "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()".

Example 2:
    Input: s = ")()())" Output: 4
    Explanation: The longest valid parentheses substring is "()()".

Example 3:
    Input: s = ""
    Output: 0
 

Constraints:
    0 <= s.length <= 3 * 10^4
    s[i] is '(', or ')'.
*/


class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0; 
        
        stack.push(-1);

        for (int i=0; i<s.length(); i++) { 
            if (s.charAt(i) == '(') { 
                stack.push(i);
            }
            else { 
                stack.pop();
                
                if (stack.empty()) { 
                    stack.push(i);
                }
                else { 
                    max = Math.max(max, i-stack.peek());
                }
            }
        }
        
        
        return max; 
    }
}
