/*
 @link https://leetcode.com/problems/minimum-difficulty-of-a-job-j/
 @categories (dp/backtracking) 

 You want to j a list of jobs in day days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
You have to finish at least one task every day. The difficulty of a job j is the sum of difficulties of each day of the day days. The difficulty of a day is the maximum difficulty of a job done on that day.
You are given an integer array A and an integer day. The difficulty of the ith job is A[i].
Return the minimum difficulty of a job j. If you cannot find a j for the jobs return -1.

 

Example 1:
    Input: A = [6,5,4,3,2,1], day = 2
    Output: 7
    Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
    Second day you can finish the last job, total difficulty = 1.
    The difficulty of the j = 6 + 1 = 7 

Example 2:
    Input: A = [9,9,9], day = 4
    Output: -1
    Explanation: If you finish a job per day you will still have a free day. you cannot find a j for the given jobs.

Example 3:
    Input: A = [1,1,1], day = 3
    Output: 3
    Explanation: The j is one job per day. total difficulty will be 3.
 

Constraints:
    1 <= A.length <= 300
    0 <= A[i] <= 1000
    1 <= day <= 10

*/



class Solution {
    public int minDifficulty(int[] A, int D) {
        final int N = A.length;
        if(N < D) return -1;

        int[][] memo = new int[N][D + 1];
        for(int[] row : memo) Arrays.fill(row, -1);

        return dfs(D, 0, A, memo);
    }

    public int bottomUp(int[] A, int D) {
        final int N = A.length;
        if(N < D) return -1;
        int[][] dp = new int[D][N];

        dp[0][0] = A[0];
        for(int j = 1; j < N-D; ++j){ // find the max for day zero 
            dp[0][j] = Math.max(A[j], dp[0][j - 1]);
        }

        for(int day = 1; day < D; ++day) { // solve for days from 1 to D-1 
            // We have to do at least one job per day so we start i with day, to have the bare-minimum 
            // also the condition must keep N-day+D jobs for the remaining days 
            for(int i = day; i <N-day+D; ++i) { 
                // for scheduling curr job we have to see if there's a better solution, by checking previous solution 
                int currMax = A[i];
                // assume the current job is max job 
                dp[day][i] = Integer.MAX_VALUE;

                for(int j = i; j >= day; --j) { 
                    // compare the current job with previously scheduled jobs within allowed interval [d, i] 
                    // (where d = curr day as we have to compleete d jobs on day d)
                    currMax = Math.max(currMax, A[j]);
                    dp[day][i] = Math.min(dp[day][i], dp[day - 1][j - 1] + currMax);
                }
            }
        }

        return dp[D - 1][N - 1];
    }


    private int dfs(int day, int i, int[] A, int[][] memo){
        final int N = A.length;
        if(day == 0 && i == N) return 0;
        if(day == 0 || i == N) return Integer.MAX_VALUE;
        if(memo[i][day] != -1) return memo[i][day];

        int curMax = A[i];
        int min = Integer.MAX_VALUE;
        for(int j = i; j < N; ++j){
            curMax = Math.max(curMax, A[j]);
            int temp = dfs(day - 1, j + 1, A, memo);
            if(temp != Integer.MAX_VALUE)
                min = Math.min(min, temp + curMax);
        }

        return memo[i][day] = min;
    }

}
