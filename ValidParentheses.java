/** 
 *
 * @link leetcode.com/problems/valid-parentheses
 *
 */ 


/** array based solution */ 
class Solution {    
    public boolean isValid(String s) {    
        char[] arr = new char[s.length()];     
        int pos = 0;     
                            
        for (char c : s.toCharArray()) {     
            switch(c) {     
                case '(': arr[pos++] = ')'; break;    
                case '{': arr[pos++] = '}'; break;    
                case '[': arr[pos++] = ']'; break;             
                default: if (pos == 0 || arr[--pos] != c) return false;            
            }                                                                      
        }                                                                
            
        return pos == 0;    
    }                                                                    
}                                                                        

/** stack based solution */ 
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        
        for (int i=0; i<s.length(); i++) { 
            if (map.get(s.charAt(i)) == null) stack.push(s.charAt(i));
            else if (!stack.empty() && stack.peek() == map.get(s.charAt(i))) stack.pop();
            else return false; 
        }
        
        return stack.size() == 0;
    }
}
