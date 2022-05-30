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
