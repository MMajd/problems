/** 
 *
 * @link https://leetcode.com/problems/daily-temperatures/
 *
 */

class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int[] ans =  new int[temp.length];
        Stack<Integer> s = new Stack<>();
        
        for(int i=0; i<temp.length; i++) { 
            while(!s.empty() && temp[i] > temp[s.peek()]) { 
                ans[s.peek()] = i - s.peek();
                s.pop();
            }
            
            s.push(i);
        }
        
        return ans; 
    }
}
