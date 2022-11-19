/*
 @link https://leetcode.com/problems/erect-the-fence
 @categories (math/geometery) 

 You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
You are asked to fence the entire garden using the minimum length of rope as it is expensive. 
The garden is well fenced only if all the trees are enclosed.
Return the coordinates of trees that are exactly located on the fence perimeter.


Example 1:
    Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
    Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]

Example 2:
    Input: points = [[1,2],[2,2],[4,2]]
    Output: [[4,2],[2,2],[1,2]]
 

Constraints:
    1 <= points.length <= 3000
    points[i].length == 2
    0 <= xi, yi <= 100
    All the given points are unique.

********************************************************
** IMPORTANT NOTE                                     **
********************************************************
    - This problem relates to these topics 
    - Graham Scan and Jarvis Scan and Convex Hull
*/


public class Solution {
    public int clockwise(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }
    public int[][] outerTrees(int[][] points) {
        Arrays.sort(points, new Comparator<int[]> () {
            public int compare(int[] p, int[] q) {
                return q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0];
            }
        });
        Stack<int[]> hull = new Stack<>();
        for (int i = 0; i < points.length; i++) {
            while (
                hull.size() > 1 
                && clockwise(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0
            ) hull.pop();
            
            hull.push(points[i]);
        }

        hull.pop();

        for (int i = points.length - 1; i >= 0; i--) {
            while (
                hull.size() > 1 && 
                clockwise(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0
            ) hull.pop();

            hull.push(points[i]);
        }

        HashSet<int[]> ret = new HashSet<>(hull);
        return ret.toArray(new int[ret.size()][]);
    }
}
