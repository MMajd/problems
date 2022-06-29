/** 
 *
 * @link leetcode.com/problems/flood-fill
 *
 * DFS solution / Could be done in BFS, but DFS much easier and concise
 *
 */ 

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (color == image[sr][sc]) return image; 
        
        fill(image, sr, sc,image[sr][sc],color);
        return image;
    }
    public void fill(int[][] image, int sr, int sc,int scolor, int color) {
        if (sr < 0 
            || sr >= image.length
            || sc < 0
            || sc >= image[0].length
            || image[sr][sc] != scolor
           ) {
            return;
        }
        
        image[sr][sc] = color; 
        
        fill (image, sr + 1, sc, scolor, color);
        fill (image, sr - 1, sc, scolor, color);
        fill (image, sr, sc + 1, scolor, color);
        fill (image, sr, sc - 1, scolor, color);
    }
}
