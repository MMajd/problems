/**
 @link https://leetcode.com/problems/guess-number-higher-or-lower
*/

/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        if (guess(n) == 0) return n; 
        
        int start=1, end=n-1; 
        
        while(start<=end) { 
            int mid = start+(end-start)/2; 
            int cmp = guess(mid); 
            if (cmp == 0) return mid; 
            if (cmp == 1) start=mid+1; 
            else end=mid-1; 
        }

        return end;  
    }
}


