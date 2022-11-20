/*
 @link https://leetcode.com/problems/basic-calculator
 @categories (stack/recusion/string/math)

 Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
    Input: s = "1 + 1"
    Output: 2

Example 2:
    Input: s = " 2-1 + 2 "
    Output: 3

Example 3:
    Input: s = "(1+(4+5+2)-3)+(6+8)"
    Output: 23

Constraints:
    1 <= s.length <= 3 * 10^5
    s consists of digits, '+', '-', '(', ')', and ' '.
    s represents a valid expression.
    '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
    '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
    There will be no two consecutive operators in the input.
    Every number and running calculation will fit in a signed 32-bit integer.
*/

class Solution {
    public int calculate(String S) {
        int n = S.length(); 

        int sum = 0;
        int sign = 1;

        char[] s = S.toCharArray();
        Stack<Integer> st = new Stack<>();

        for (int i = 0;i<n;i++) {
            if (Character.isDigit(s[i])) {
                int val = 0;
                while(i < n && Character.isDigit(s[i])) {
                    val = val * 10 + (s[i++] - '0');
                }
                i--; // back one step, will added by the loop 
                val = val * sign;
                sum += val;   
                sign = 1; // default sign 
            }
            else if(s[i] == '('){
                st.push(sum);
                st.push(sign);
                sum = 0;
                sign = 1;
            }
            else if(s[i] == ')') {
                sum *= st.pop();
                sum += st.pop();
            }
            else if(s[i] == '-') {
                sign*= -1;
            }
        }
        return sum;
    }
}
