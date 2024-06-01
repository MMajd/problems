/*
 @link https://leetcode.com/problems/maximum-difference-score-in-a-grid
 @categories(dp/matrix)

 You are given an m x n matrix grid consisting of positive integers. You can move from a cell in the matrix to any other cell that is either to the bottom or to the right (not necessarily adjacent). The score of a move from a cell with the value c1 to a cell with the value c2 is c2 - c1.

You can start at any cell, and you have to make at least one move.

Return the maximum total score you can achieve.

Example 1:
    Input: grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
    Output: 9
    Explanation: We start at the cell (0, 1), and we perform the following moves:
    - Move from the cell (0, 1) to (2, 1) with a score of 7 - 5 = 2.
    - Move from the cell (2, 1) to (2, 2) with a score of 14 - 7 = 7.
    The total score is 2 + 7 = 9.

Example 2:
    Input: grid = [[4,3,2],[3,2,1]]
    Output: -1
    Explanation: We start at the cell (0, 0), and we perform one move: (0, 0) to (0, 1). The score is 3 - 4 = -1.

Constraints:
    m == grid.length
    n == grid[i].length
    2 <= m, n <= 1000
    4 <= m * n <= 105
    1 <= grid[i][j] <= 10^5
*/



class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size() + 1; 
        int m = grid.get(0).size() + 1;

        int[][] dp = new int[n][m];
        int[][] mat = new int[n][m]; 

        int x = 1; 
        for (List<Integer> row : grid) {
            int y = 1; 
            for (int val : row) mat[x][y++] = val;
            x += 1;
        }

        for (int i = 0; i < n; i++) mat[i][0] = Integer.MAX_VALUE; 
        for (int i = 0; i < m; i++) mat[0][i] = Integer.MAX_VALUE; 

        int max = Integer.MIN_VALUE; 
        int min = Integer.MIN_VALUE; 

        for (int i = 1; i < n; i++) { 
            for (int j = 1; j < m; j++) {
                int up = dp[i - 1][j] + (mat[i][j] - mat[i - 1][j]);
                int left = dp[i][j - 1] + (mat[i][j] - mat[i][j - 1]);

                dp[i][j] = max(0, left, up);
                max = max(dp[i][j], max);

                if (max(min, up, left) <= 0) { 
                    min = max(up, left, min);
                }
            }
        }
        return max != 0 ? max : min; 
    }

    private int max(int ...list) { 
        int ans = Integer.MIN_VALUE; 
        for (int val : list) ans = Math.max(ans, val);
        return ans; 
    }
}
