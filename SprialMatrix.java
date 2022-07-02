/** 
 *
 * https://leetcode.com/problems/spiral-matrix/
 *
 */ 

class Solution {
    private enum DIR {UP, DOWN, RIGHT, LEFT};
    public List<Integer> spiralOrder(int[][] mat) {
        int m = mat.length; 
        int n = mat[0].length; 
        
        List<Integer> ans = new ArrayList<>(m*n); 
        
        int top, bottom, right, left; 
        top = 0; 
        left = 0; 
        right = n-1; 
        bottom = m-1; 
        
        DIR dir = DIR.RIGHT; 

        while(top <= bottom && left <= right) { 
            switch (dir) { 
                case RIGHT: { 
                    for (int i=left; i<=right; i++) { 
                        ans.add(mat[top][i]);
                    }
                    ++top; 
                    dir = DIR.DOWN;
                    break; 
                }
                case DOWN: { 
                    for (int i=top; i<=bottom; i++) { 
                        ans.add(mat[i][right]);
                    }
                    --right;
                    dir = DIR.LEFT; 
                    break; 
                }
                case LEFT: { 
                    for (int i=right; i>=left; i--) { 
                        ans.add(mat[bottom][i]);
                    }
                    --bottom; 
                    dir = DIR.UP; 
                    break; 
                }
                case UP: { 
                    for (int i=bottom; i>=top; i--) { 
                        ans.add(mat[i][left]);
                    }
                    ++left; 
                    dir = DIR.RIGHT;
                    break;
                }
            }
        }
        
        
        return ans; 
    }
}
