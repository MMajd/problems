/*
 @link https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 @categories (greedy[activity-selection]/sorting/arrays)

 There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
    Input: points = [[10,16],[2,8],[1,6],[7,12]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
    - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
    - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:
    Input: points = [[1,2],[3,4],[5,6],[7,8]]
    Output: 4
    Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:
    Input: points = [[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
    - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
    - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

Constraints:
    1 <= points.length <= 10^5
    points[i].length == 2
    -2^31 <= xstart < xend <= 2^31 - 1
*/

/** 
 * This is an activity selection problem but the logic of the problem statement is inverted
 * in pure activity selection pure statment we want to get max number of events that doesn't overlap
 * also here we want to get that number but the max number of non-overlapping events is the minimum number
 * of errors need to burts the ballons, as all overlapping events will be bursts implicitely 
 */

class Solution {
    public int findMinArrowShots(int[][] A) {
        int n = A.length; 
        Arrays.sort(A, (a, b) -> {
            if (a[1]!=b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[1]-a[0], b[1]-b[0]);
        }); 

        int arrows = 0; 
        long time = Long.MIN_VALUE; 

        for (int[] t : A) { 
            if (time < t[0]) { 
                arrows += 1; 
                time = t[1]; 
            }
        }

        return arrows; 
    }
}

