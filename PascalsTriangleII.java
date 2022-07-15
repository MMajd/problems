/** 
 @link https://leetcode.com/problems/pascals-triangle-ii/

 Pascal's Triangle II

 Solution
 Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

 In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
    Input: rowIndex = 3
    Output: [1,3,3,1]

Example 2:
    Input: rowIndex = 0
    Output: [1]

Example 3:
    Input: rowIndex = 1
    Output: [1,1]
 

Constraints:
    0 <= rowIndex <= 33
*/ 


class Solution {
    public List<Integer> getRow(int row) { 
        int[][] arr = new int[row+1][row+1]; 
        List<Integer> list = new ArrayList<>(); 
        
        for (int i=0; i<=row; i++) {
            list.add(getCell(row, i, arr));
        }
        
        return list; 
    }
    
    private int getCell(int i, int j, int[][] arr) { 
        if (arr[i][j] != 0) return arr[i][j]; 
        if (i == j || j == 0) return 1; 
        arr[i][j] = getCell(i-1,j-1,arr) + getCell(i-1,j,arr);
        return arr[i][j]; 
    }
    
    public List<Integer> getRowIterative(int row) {
        List<Integer> curr, prev = null; 
        
        for (int i=0; i<=row; i++) { 
            curr = new ArrayList<>(); 
            
            for (int j=0; j<= i; j++) { 
                if (j == 0 || j==i) { 
                    curr.add(1);
                } else { 
                    curr.add(prev.get(j-1) + prev.get(j));
                }
            }
            
            prev = curr; 
        }
        
        return prev; 
    }
}
