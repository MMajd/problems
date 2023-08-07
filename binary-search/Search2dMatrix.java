/**
 @link https://leetcode.com/problems/search-a-2d-matrix/description/  
 @categories(binary-search/matrix)

You are given an m x n integer matrix matrix with the following two properties:
Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true

Example 2:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    Output: false
 

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -10^4 <= matrix[i][j], target <= 10^4

*/

/**
 * @NOTE:
 * this solution based on the observation that all elements in the matrix are sorted in non-decreasing fashion
 * from the cell (0,0) to cell (N,M), so we can perform regular binary-search w/ small tweak that we treat the matrix as a vector 
 * by getting the mid and then dividing by m to get the row and getting the reminder of mid by m to get the column
 */ 
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length; 
        int m = matrix[0].length; 

        int low = 0; 
        int high = (n*m)-1; 

        while (low <= high) {
            int mid = low + (high - low) / 2; 
            if (matrix[mid/m][mid%m] == target) return true; 
            if (matrix[mid/m][mid%m] < target) {  
                low = mid + 1; 
            } else { 
                high = mid -1; 
            }
        }

        return false; 
    }
}
