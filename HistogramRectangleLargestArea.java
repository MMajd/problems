/**
 * Solution for leetcode problem no 84
 * Find largest rectangle area in some histogram 
 *
 * In this solution, we use increasing MonotonicStack 
 *
 * as we want to find the least prev/next least element 
 * for each element in the heights list 
 *
 * */


private class Solution {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = null;
        
        int[] PLE = new int[heights.length];
        stack = new LinkedList<Integer>();
        
        for(int i=0 ; i< heights.length;i++) {
            while(stack.size()>0 && heights[stack.peek()] >= heights[i])
                stack.pop();
            
            if(stack.size()==0) {
                PLE[i] = -1;
            } else {
                PLE[i] = stack.peek();
            }
            
            stack.push(i);
        }
        
        int[] NLE = new int[heights.length];
        stack = new LinkedList<Integer>();
        
        for(int i = heights.length - 1 ; i >=0 ; i--) {
            while(stack.size()>0 && heights[stack.peek()] >= heights[i])
                stack.pop();
            
            if(stack.size()==0) NLE[i] = heights.length;
            else NLE[i] = stack.peek();
            
            stack.push(i);
        }
        
        for(int i = 0 ; i < heights.length ; i++){
            int temp = (NLE[i] - PLE[i] - 1) * heights[i];
            ans = Math.max(ans , temp);
        }
        return ans;
        
    }
}


public class HistogramRectangleLargestArea { 
	public static void main(String[] args) { 
		/** */ 
	}
}
