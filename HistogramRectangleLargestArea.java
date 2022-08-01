/*

 @link https://leetcode.com/problems/largest-rectangle-in-histogram/

 Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 Example 1:
    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
    Input: heights = [2,4]
    Output: 4

Constraints:
    1 <= heights.length <= 105
    0 <= heights[i] <= 104
 */


/** Divide & Conquer w/out seg tree */ 
class Solution {
    private int maxCombineArea(int[] height, int s, int m, int e) {
        // Expand from the middle to find the max area containing height[m] and height[m+1]
        int i = m, j = m+1;
        int area = 0, h = Math.min(height[i], height[j]);
        while(i >= s && j <= e) {
            h = Math.min(h, Math.min(height[i], height[j]));
            area = Math.max(area, (j-i+1) * h);

            if (i == s) {
                ++j;
            }

            else if (j == e) {
                --i;
            }

            else {
                // if both sides have not reached the boundary,
                // compare the outer bars and expand towards the bigger side
                // choose largerh 
                if (height[i-1] > height[j+1]) {
                    --i;
                }
                else {
                    ++j;
                }
            }
        }
        return area;
    }

    private int maxArea(int[] height, int s, int e) {
        // if the range only contains one bar, return its height as area
        if (s == e) {
            return height[s];
        }
        // otherwise, divide & conquer, the max area must be among the following 3 values
        int m = s + (e-s)/2;
        // 1 - max area from left half
        int area = maxArea(height, s, m);
        // 2 - max area from right half
        area = max(area, maxArea(height, m+1, e));
        // 3 - max area across the middle
        area = max(area, maxCombineArea(height, s, m, e));
        return area;
    }

    public int largestRectangleArea(int[] height) {
        if (height.length == ) return 0;
        return maxArea(height, 0, height.length-1);
    }
};




/** Segement Tree Solution */
class Solution {
    private int[] seg, hts; 
    private int n; 
    
    public int largestRectangleArea(int[] heights) {
        n = heights.length; 
        seg = new int[n*4]; 
        hts = heights.clone();

        build(1, 0, n-1); 
        
        return maxArea(n, 0, n-1);
    }
    
    private void build(int pos, int start, int end) { 
        if (start == end) { 
            seg[pos] = start; 
            return; 
        }
        
        int mid = start+(end-start)/2; 
        
        build(2*pos, start, mid); 
        build(2*pos+1, mid+1, end); 
        
        seg[pos] = findMinValIdx(seg[2*pos], seg[2*pos+1]); 
    }
    
    private int getLargestRect(int pos, int start, int end, int left, int right) { 
        if (start>=left && right>=end) return seg[pos]; 
        if (start>right || end<left) return -1; 
        
        int mid = start+(end-start)/2; 
        
        return findMinValIdx(
                    getLargestRect(2*pos, start, mid, left, right), 
                    getLargestRect(2*pos+1, mid+1, end, left, right)
                );
    }
    
    private int maxArea(int n, int start, int end) { 
        if (start>end) return Integer.MIN_VALUE; 
        if (start==end) return hts[start]; 
        
        int minHeightIdx = getLargestRect(1, 0, n-1, start, end); 
        
        return max(
                maxArea(n, start, minHeightIdx-1), 
                maxArea(n, minHeightIdx+1, end), 
                (end-start+1)*hts[minHeightIdx]
            );
    }

    private int findMinValIdx(int i, int j) { 
        if (i == -1) return j; 
        if (j == -1) return i; 
        
        return hts[i]<hts[j]? i : j; 
    }
        
    private int max(int x, int y, int z) { 
        return Math.max(Math.max(x, y), z);
    }
}


/** Monotonic Stack I, find previous, and next least min element
 * then find area by calc rect base by taking the sub between indices of (next least element - previous least element - 1) * height
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


/** Monotonic Stack II */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int i = 0; 
        int area = 0; 
        Stack<Integer> stack = new Stack<>();
        
        while(i < heights.length) { 
            if (stack.isEmpty() || 
                    heights[i] >= heights[stack.peek()]) { 
                stack.push(i++);
            } else { 
                int h = heights[stack.pop()]; 
                int w = stack.isEmpty() ? i : i-stack.peek()-1; 
                area = Math.max(h*w, area);
            }
        }
        
        while(!stack.isEmpty()) { 
            int h = heights[stack.pop()]; 
            int w = stack.isEmpty() ? i : i-stack.peek()-1; 
            area = Math.max(h*w, area);
        }
        
        return area;
    }
}




