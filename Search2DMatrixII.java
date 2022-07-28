/*

@link https://leetcode.com/problems/search-a-2d-matrix-ii/

My Sol. on LC: https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/2344898/Java-Decrease-and-Conquer-Search-In-Upper-Right-and-Bottom-Left


Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
 

Example 1:
    Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
    Output: true

Example 2:
    Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
    Output: false
 
Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= n, m <= 300
    -109 <= matrix[i][j] <= 109
    All the integers in each row are sorted in ascending order.
    All the integers in each column are sorted in ascending order.
    -109 <= target <= 109
*/


/** Divide and conqure algo */ 

class Solution {
    public boolean searchMatrix(int[][] arr, int target) {
        return search2d(arr, target, new int[]{0, arr.length-1}, 
                        new int[]{0, arr[0].length-1}); 
    }
    
    private boolean search2d(int[][] arr, int target, int[] rows, int[] cols) { 
        if (rows[0] > rows[1] 
            || cols[0] > cols[1]
           ) return false; 
        
        int cp = cols[0] + (cols[1]-cols[0]) / 2;
        
        int rp = rows[0]; 
        
        while(rp <= rows[1])  { 
            if (target == arr[rp][cp]) return true; 
            if (target > arr[rp][cp]) rp++; 
            else break;
        }
        
        return search2d(arr, target, new int[]{rows[0], rp-1}, new int[]{cp+1, cols[1]})
            || search2d(arr, target, new int[]{rp, rows[1]}, new int[]{cols[0], cp-1}); 
    }
}
