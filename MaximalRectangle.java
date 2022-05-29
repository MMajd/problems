
/** 
 * This code is the solution for leetcode no: 85 MaximalRectangle
 * Solution require us to build the histogram found in problem 84
 * ourselfs 
 * Largest rectangle in a histogram: 
 * @link: https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * This problem link: 
 * @link: https://leetcode.com/problems/maximal-rectangle/
 *
 * */


public class MaximalRectangle { 

}

private class Solution {
    private int largestRectangleArea(int[] heights) {
        int area = 0;
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
            area = Math.max(area , temp);
        }
        
        return area;
    }
    
    public int maximalRectangle(char[][] matrix) {
        int area = 0;  
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] nums = new int[rows][cols];
        
        // convert to integers 
        for (char i=0; i<rows; i++) {
            for (char j=0; j<cols; j++) { 
                nums[i][j] = matrix[i][j] - '0';
            }
        }
         
        for (int i=0; i<rows; i++) {
            for (int j=0; i > 0 && j<cols; j++) { 
                if (nums[i][j] == 1) { 
                    nums[i][j] += nums[i-1][j]; // sweepRow[j] += 1
                }
                else nums[i][j] = 0; 
            }
            
            int temp = largestRectangleArea(nums[i]); 
            System.out.println(temp);
            area = Math.max(temp, area);
        }
        
        
        return area;
    }
}
