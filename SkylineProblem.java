/*

 @link https://leetcode.com/problems/the-skyline-problem
   
 @categories (ordered-set/tree/priorirty-queue/line-sweep)

 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]


Example 1:
    Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
    Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    Explanation:
    Figure A shows the buildings of the input.
    Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.

Example 2:
    Input: buildings = [[0,2,3],[2,5,3]]
    Output: [[0,3],[5,0]]
 
Constraints:
    1 <= buildings.length <= 104
    0 <= lefti < righti <= 231 - 1
    1 <= heighti <= 231 - 1
    buildings is sorted by lefti in non-decreasing order.

*/

class Solution {
    private static class BuilingPoint { 
        int x; 
        int h; 
        boolean isstart; 
        
        public BuilingPoint(int x, int h) {
            this(x, h, false);
        }
        
        public BuilingPoint(int x, int h, boolean s) {
            this.x = x; this.h = h;  this.isstart = s; 
        }
        
        public String toString() {
            return "{ x: " + x + ", h: " + h + ", s: " + isstart + " }";
        }
    }
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length; 
        BuilingPoint[] points = new BuilingPoint[2*n];
        
        for (int i=0, j=0; i<n; i++) { 
            int x1 = buildings[i][0]; 
            int x2 = buildings[i][1]; 
            int h = buildings[i][2]; 
            
            points[j++] = new BuilingPoint(x1, h, true); 
            points[j++] = new BuilingPoint(x2, h); 
        }
        
        Arrays.sort(points, (p1, p2) -> {
            if (p1.x != p2.x) return p1.x - p2.x; 
            // p1 and p2 have the same x-coordinate
            // if a and b are start points, then the one with more height is to appear first [height desc]
            // if a and b are end points, the the one with with less height is to appear first [height asc]
            // if a and b have different types, the the of start type is to appear first 
            return (p1.isstart ? -p1.h : p1.h) - (p2.isstart ? -p2.h : p2.h); 
        });
        
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, Integer> pq = new TreeMap<>(); 
        pq.put(0, 1);
        
        int prevHeight = pq.lastKey(); 
        
        for (BuilingPoint p: points) { 
            if (p.isstart) { 
                pq.compute(p.h, (k, v) -> v==null ? 1 : ++v);
            }
            else { 
                pq.compute(p.h, (k, v) -> v > 1 ? --v : null);
            }
            
            int height = pq.lastKey();
            
            if (height != prevHeight) { 
                ans.add(Arrays.asList(new Integer[]{p.x, height}));
                prevHeight = height; 
            }
        }
        
        return ans; 
    }
}
