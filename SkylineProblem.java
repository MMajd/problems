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


*************************************************
Intiution: 
    * Main point is to process buildings starting/ending point as an independent event 
    * Every given building will be converted to 2 events, starting and ending one 
    * Sort events according to their appearance on x-axis 
    * When Sorting events there're some edge cases, 
        1. When having two starting events with the same x-point, but with different height, 
        the one with higher hight must appear first, Ex: {2,4,start},{2,5,start} -> {2,5,start},{2,4,start}

        2. When having two events with same x-point but with diferent types 
        the first is ending event and the seconding is starting event, 
        the starting event must always appear first in this case, Ex: {2,3,end},{2,2,start} -> {2,2,start},{2,3,end}

        3. When having two ending events with the same x-point, but with different height, 
        the one with lower hight must appear first, Ex: {2,4, end},{2,5, end} -> {2,4,end},{2,5,end}

    * When processing events we must use some sort of ordered data structure to be able to get highest event in x-point of time, 
    also this DS should be able to have multiple events of the same value
    * Perfect match for the before-mentioned properties is Multi-Orderd Set (TreeMap in java/ multiset in C++)
*/

class Solution {
    private static enum Type { 
        start, 
        end; 
    }
    
    private static final class Point {
        int x, y; 
        Type type;
        
        public Point(int x, int y, Type t) {
            this.x=x; 
            this.y=y; 
            this.type=t;
        }
        
        public String toString() { 
            return "{ x: " + x 
                + ", y: " + y 
                + ", type: " + type
                + " }"; 
        }
    }
    
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        Point[] points = new Point[2*n]; 
        
        for (int i=0, j=0; i<n; i++) { 
            int x1 = buildings[i][0];
            int x2 = buildings[i][1];
            int y = buildings[i][2];
            
            points[j++] = new Point(x1, y, Type.start); 
            points[j++] = new Point(x2, y, Type.end); 
        }
        
        Arrays.sort(points, (a, b) -> {
            if (a.x != b.x) return Integer.compare(a.x, b.x);
            return Integer.compare(
                    a.type == Type.start ? -a.y : a.y, 
                    b.type == Type.start ? -b.y : b.y
                );
        });
        
        List<List<Integer>> ans = new ArrayList<>(2*n);
        TreeMap<Integer, Integer> multiSet = new TreeMap<>(); 
        
        multiSet.put(0, 1);
        Integer prevHeight = 0; 
        
        for (Point p : points) { 
            multiSet.compute(p.y, (k, v) -> { 
                if (p.type == Type.start) { 
                    return v==null ? 1 : ++v; 
                }
                
                return v > 1 ? --v : null; 
            });
            
            Integer currHeight = multiSet.lastKey(); 
            
            if (prevHeight != currHeight) { 
                ans.add(
                    new ArrayList<>(2){{
                    add(p.x); 
                    add(currHeight);
                }});
                prevHeight = currHeight; 
            }
        }
        
        return ans; 
    }
}





