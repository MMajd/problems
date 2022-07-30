
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

 Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
    Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    Output: 6
    Explanation: The maximal rectangle is shown in the above picture.

Example 2:
    Input: matrix = [["0"]]
    Output: 0

Example 3:
    Input: matrix = [["1"]]
    Output: 1

Constraints:
    rows == matrix.length
    cols == matrix[i].length
    1 <= row, cols <= 200
    matrix[i][j] is '0' or '1'.
*/


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

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[] dp = new int[matrix[0].length];  // number of columns 
        int area = 0; 
        
        for (int i=0; i<matrix.length; i++) { 
            for (int j=0; j<matrix[0].length; j++) { 
                dp[j] = matrix[i][j] == '1' ? dp[j]+1 : 0; 
            }
            
            area = Math.max(area, largestHistogramRect(dp)); 
        }

        return area; 
    }
    
    private int largestHistogramRect(int[] heights) { 
        int area = 0; 
        Stack<Integer> stack = new Stack<>(); 
        
        int i=0; 
        
        while(i < heights.length || !stack.isEmpty()) { 
            if (i < heights.length 
                && (stack.isEmpty() || heights[i] >= heights[stack.peek()])) {
                stack.push(i++);
            }
            else {
                int h = heights[stack.pop()]; 
                int w = stack.isEmpty() ? i : i - stack.peek() - 1; 
                area = Math.max(h*w, area);
            }
        }
        
        return area;
    }
}
