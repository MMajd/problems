/*
 @link https://leetcode.com/problems/toeplitz-matrix
 @categories (array/matrix/hash-table) 

 Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.

A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.


Example 1:
    Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
    Output: true
    Explanation:
    In the above grid, the diagonals are:
    "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
    In each diagonal all elements are the same, so the answer is True.

Example 2:
    Input: matrix = [[1,2],[2,2]]
    Output: false
    Explanation:
    The diagonal "[1, 2]" has different elements.

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 20
    0 <= matrix[i][j] <= 99
 

Follow up:
 - What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 - What if the matrix is so large that you can only load up a partial row into the memory at once?

 */ 




class Solution {
    public boolean isToeplitzMatrix(int[][] M) {
        int m = M.length; 
        int n = M[0].length; 
        
        for (int i=0; i<m-1; i++) { 
            for (int j=1; j<n; j++) {
                if (i + 1 >= m || j-1 < 0) continue; 
                if (M[i][j-1] != M[i+1][j]) return false; 
            }
        }
        
        return true; 
    }
}

// O(N) Space 
class Solution2 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        Map<Integer, Integer> groups = new HashMap();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!groups.containsKey(r-c))
                    groups.put(r-c, matrix[r][c]);
                else if (groups.get(r-c) != matrix[r][c])
                    return False;
            }
        }
        return True;
    }
}


