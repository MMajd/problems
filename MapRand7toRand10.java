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

    // Rejecting sampling algorithm, Same idea behind the EPI
    class Solution1 extends SolBase {
        public int rand10() {
            int row, col, idx;
            do {
                row = rand7();
                col = rand7();
                idx = col + (row - 1) * 7; // last valid raw is 6
            } while (idx > 40);
            return 1 + (idx - 1) % 10;
        }
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
