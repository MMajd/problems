/*
 @link https://leetcode.com/problems/evaluate-reverse-polish-notation
 @categories (stack/math/string)

 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

Example 1:
    Input: tokens = ["2","1","+","3","*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9

Example 2:
    Input: tokens = ["4","13","5","/","+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6

Example 3:
    Input: tokens = 
        ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    Output: 22
    Explanation: 
        ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5
        = 22
 

Constraints:
    1 <= tokens.length <= 10^4
    tokens[i] is either an operator: 
    "+", "-", "*", or "/", or an integer in the range [-200, 200].
*/

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>(tokens.length);
        int ans = 0; 

        for (int i=0; i<tokens.length; i++) {
            if (!operator(tokens[i])) { 
                stack.push(Integer.parseInt(tokens[i]));
            }
            else { 
                stack.push(exec(stack.pop(), stack.pop(), tokens[i])); 
            }
        }

        return stack.pop().intValue(); 
    }

    private boolean operator(String op) { 
        if (op.equals("+") || op.equals("-") || 
            op.equals("*") || op.equals("/")) return true; 
        return false; 
    }

    private Integer exec(Integer y, Integer x, String op) { 
        if (op.equals("+")) return x + y;
        if (op.equals("-")) return x - y;
        if (op.equals("/")) return x / y;
        if (op.equals("*")) return x * y;
        return 0; 
    }
}
