/** 
 *
 */

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        Set<String> op = new HashSet<>(
            Arrays.asList(new String[]{"+", "-", "*", "/"}));
        
        for (String c : tokens) { 
            if (op.contains(c)) { 
                int p2 = s.pop(); 
                int p1 = s.pop();
                s.push(execOp(p1, p2, c));
            } else { 
                s.push(Integer.parseInt(c));
            }
        }
        
        return s.pop();
    }
    
    private int execOp(int p1, int p2, String op) { 
        switch(op) { 
            case "+": return p1 + p2;  
            case "-": return p1 - p2;  
            case "*": return p1 * p2;  
            case "/": return p1 / p2;  
        }
        
        return 0; 
    }
}
