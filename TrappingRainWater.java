/*
  
 @link https://leetcode.com/problems/trapping-rain-water/
  
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
    Input: height = [4,2,0,3,2,5]
    Output: 9

Constraints:
    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105
  
 */




public class TrappingRainWater {
    /** This solution uses monotonic stack */ 
    
    public int trap(int[] height) {
        int area = 0; 
        Stack<Integer> stack = new Stack<>(); 

        for (int i=0; i<height.length; i++) { 
	   // while the stack has no element bigger than the last one
	   // keep pushing 

            while(!stack.isEmpty() && 
                   height[stack.peek()] < height[i]) { 
		// get the last element index 
		// if there's one under it in the stack 
		// then we can accumulate its trapped water 
                int j = stack.pop();  // element under testing
                
		// make sure that there's an element larger 
		// than the one under testing 
                if (!stack.isEmpty()) { 
                    int pidx = stack.peek(); 
                    int minHeight = Math.min(height[i], height[pidx]); 
                    area += (minHeight - height[j]) * (i - pidx - 1); 
                }
            }
            
            stack.push(i);
        }
        
        return area;
        
    }
}
