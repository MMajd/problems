/* 
 @link https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon
 @categories (dp/prefix-sum/array)


 In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.

You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to magician (i + k). This process will be repeated until you reach the magician where (i + k) does not exist.

In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the magicians' sequence, absorbing all the energy during the journey.

You are given an array energy and an integer k. Return the maximum possible energy you can gain.
 
Example 1:
    Input: energy = [5,2,-10,-5,1], k = 3
    Output: 3
    Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.

Example 2:
    Input: energy = [-2,-3,-1], k = 2
    Output: -1
    Explanation: We can gain a total energy of -1 by starting from magician 2.


Constraints:
    1 <= energy.length <= 10^5
    -1000 <= energy[i] <= 1000
    1 <= k <= energy.length - 1
 */ 


/**
 * Obeservation: you can start from any point from any point in the magicians array but you have to stick with that point till the end 
 *
 * dp prefix sum 
 */
class Solution {

    public int maximumEnergy(int[] A, int k) {
        final int n = A.length; 
        final int[] prefix = new int[n]; 
        int ans = -1000; 

        // end point has to be one of the kth points in the array
        for (int i=n-1; i>=n-k; --i) { 
            prefix[i] = A[i];
            ans = Math.max(ans, prefix[i]);
        }

        // go back keeping ref of max ans 
        // we can start from any point that's why we are doing that
        for (int i = n-k-1; i >=0; --i) {
            prefix[i] += prefix[i+k] + A[i];
            ans = Math.max(ans, prefix[i]);
        }

        return ans; 
    }
}


