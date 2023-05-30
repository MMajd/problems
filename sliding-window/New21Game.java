/*
 @link https://leetcode.com/problems/new-21-game/description/
 @categories (dp/sliding-window/probability)

 Alice plays the following game, loosely based on the card game "21".

 Alice starts with 0 points and draws numbers while she has less than k points. 
During each draw, she gains an integer number of points randomly from the range [1, maxPts], 
where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets k or more points.
Return the probability that Alice has n or fewer points.
Answers within 10-5 of the actual answer are considered accepted.

Example 1:
    Input: n = 10, k = 1, maxPts = 10
    Output: 1.00000
    Explanation: Alice gets a single card, then stops.

Example 2:
    Input: n = 6, k = 1, maxPts = 10
    Output: 0.60000
    Explanation: Alice gets a single card, then stops.
    In 6 out of 10 possibilities, she is at or below 6 points.

Example 3:
    Input: n = 21, k = 17, maxPts = 10
    Output: 0.73278

Constraints:
    0 <= k <= n <= 10^4
    1 <= maxPts <= 10^4
*/
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts) {
            return 1; 
        }

        double[] dp = new double[n + 1];
        dp[0] = 1;
        double window = 1;
        double result = 0; 
        for (int i = 1; i <= n; i++) {
            dp[i] = window / maxPts; // we will always be dividng by w / maxPts
            if (i < k) { // as k is our stop point, if i less than k, then we can add its probability to the window 
                window += dp[i];
            } else {
                result += dp[i];
            }
            if (i - maxPts >= 0) { // always remove the last element in the window
                window -= dp[i - maxPts];
            }
        }
        return result;
    }
}


/** 
 * Dp without sliding window, just for illustration
 */ 
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        double dp[] = new double[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxPts; j++) {
                if (i - j < 0 || i - j >= k) {
                    continue; 
                }
                dp[i] += dp[i - j] / maxPts;
            }
        }
        
        double ans = 0;
        for (int i = k; i <= n; i++) {
            ans += dp[i];
        }
        return ans;
    }
}
