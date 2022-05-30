class ContainerWithMostWater {
	/** 
	 * Two pointer solution 
	 * @link: https://leetcode.com/problems/container-with-most-water/
	 * */


    public int maxArea(int[] height) {
        int left = 0; 
        int right = height.length - 1; 
        int water = 0; 
        
        while (left < right) { 
            if (height[left] < height[right]) { 
                water = Math.max(
                    water, 
                    height[left] * (right - left)
                );
                
                left += 1; 
            } 
            else { 
                water = Math.max(
                    water, 
                    height[right] * (right - left)
                );
                
                right -= 1; 
            }
        }
        
        return water; 
    }
}
