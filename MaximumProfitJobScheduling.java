/*
 @link https://leetcode.com/problems/maximum-profit-in-job-scheduling
 @categories (binary-search/dp/ordered-set)

 We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:
    Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
    Output: 120
    Explanation: The subset chosen is the first and fourth job. 
    Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
    Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
    Output: 150
    Explanation: The subset chosen is the first, fourth and fifth job. 
    Profit obtained 150 = 20 + 70 + 60.

Example 3:
    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
    Output: 6

Constraints:
    1 <= startTime.length == 
        endTime.length == 
        profit.length <= 5 * 10^4
    1 <= startTime[i] < endTime[i] <= 10^9
    1 <= profit[i] <= 10^4
*/

class Solution {
    public int jobScheduling(int[] S, int[] E, int[] P) {
        int N = S.length; 
        int[][] jobs = new int[N][3];

        for (int i=0; i<N; i++) { 
            jobs[i] = new int[]{S[i], E[i], P[i]}; 
        }

        // sort according to the one that finish earlier comes first
        Arrays.sort(jobs, (a, b) -> a[1]-b[1]); 

        // using treemap as its built upon binary search tree (has binary-search built-in)
        TreeMap<Integer, Integer> dp = new TreeMap<>(); 
        dp.put(0, 0);

        for (int[] job : jobs) { 
            // get max profit up to this time + the profit of current job
            int curr = dp.floorEntry(job[0]).getValue() + job[2];

            // if current profit is bigger than max profit till now, then  update dp 
            if (curr > dp.lastEntry().getValue()) { 
                dp.put(job[1], curr);
            }
        }

        return dp.lastEntry().getValue();
    }
}

