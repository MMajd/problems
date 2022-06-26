/**
 *
 * @link https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards
 *
 */

class Solution {
    public int maxScore(int[] points, int k) {
        int n = points.length;
        if (k == 1) { 
            return points[0] >= points[n-1] ? points[0] : points[n-1];
        }
        
        int total = Arrays.stream(points).reduce(0, (a, b) -> a + b);
        
        if (k == n) {
            return total; 
        }
        
        int window = n - k; 
        int curr = 0; 
        int ans = 0; 
        int j = 0; 
        
        for (int i=0; i<n; i++) { 
            curr += points[i]; 
            if (i-j+1 >= window) { 
                ans = Math.max(ans, total - curr);
                curr -= points[j]; 
                j += 1; 
            }
        }
        
        return ans; 
    }
}
