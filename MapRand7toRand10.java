/** 
 * Problem 
 * @link https://leetcode.com/problems/implement-rand10-using-rand7
 *
 * Help
 * @link https://stackoverflow.com/questions/137783/expand-a-random-range-from-1-5-to-1-7 
 */

public class MapRand7toRand10 {
    public static void main(String []args) { 
    }

    class Solution extends SolBase { 
        int vals[][] = {
            { 1, 2, 3, 4, 5, 6, 7 },
            { 8, 9, 10, 1, 2, 3, 4 },
            { 5, 6, 7, 8, 9, 10, 1}, 
            { 2, 3, 4, 5, 6, 7, 8}, 
            { 9, 10, 1, 2, 3, 4, 5}, 
            { 6, 7, 8, 9, 10, 0, 0}, 
            { 0, 0, 0, 0, 0, 0, 0}
        };
        
        public int rand10() {

            
            int result = 0; 
            while(result == 0) { 
                result = vals[rand7()-1][rand7()-1];
            }
            
            return result;
        }
    }

}
