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


/**
 * TLE solution below, more obvious DP solution. 
 */ 

class Solution {
    public int jobScheduling(int[] S, int[] E, int[] P) {
        int N = S.length; 
        int[][] jobs = new int[N][3];

        for (int i=0; i<N; i++) { 
            jobs[i] = new int[]{S[i], E[i], P[i]}; 
        }

        // sorting by end-time, to guarantee non-overlapping intervals
        Arrays.sort(jobs, (a, b) -> a[1]-b[1]); 

        // using treemap as its built upon binary search tree (has binary-search built-in)
        // to start from the last non-overlapping job
        TreeMap<Integer, Integer> dp = new TreeMap<>(); 
        // base-case: in case there're no non-overlapping job before current one
        dp.put(0, 0);

        for (int[] job : jobs) { 
            // get profit of lastest non-overlapping job + our current profit 
            int curr = dp.floorEntry(job[0]).getValue() + job[2];

            // if current solution is better than the last one, added it to our ordered-set
            if (curr > dp.lastEntry().getValue()) { 
                dp.put(job[1], curr);
            }
        }

        return dp.lastEntry().getValue();
    }
}




class Solution {
    public int jobScheduling(int[] S, int[] E, int[] P) {
        int N = S.length;
        int[][] jobs = new int[N][3]; 

        for (int i=0; i<N; i++) jobs[i] = new int[]{S[i], E[i], P[i]};

        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        int[] dp = new int[N]; 

        int max = dp[0]; 

        for (int i=0; i<N; i++) { // 50k
            dp[i] = jobs[i];  

            for (int j=0; j<i; j++) { // 50k 
                if (!overlap(jobs[i], jobs[j])) {
                    dp[i+1] = Math.max(dp[i], dp[j]+jobs[i][2]); 
                }
            }

            max = Math.max(dp[i], max);
        }

        return max; 
    }

    private boolean overlap(int[] a, int[] b) {
        if (b[1] <= a[0] || a[1] <= b[0]) return false;
        return true; 
    }
}


