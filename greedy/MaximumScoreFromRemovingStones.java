/*
  
 @link https://leetcode.com/problems/maximum-score-from-removing-stones/
 @categories (greedy/priority-queue/math) 
  
 You are playing a solitaire game with three piles of stones of sizes a, b, c, take one stone from each, and add 1 point to your score. The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).

Given three integers a, b, c, return the maximum score you can get.


Example 1:
    Input: a = 2, b = 4, c = 6
    Output: 6
    Explanation: The starting state is (2, 4, 6). One optimal set of moves is:
    - Take from 1st and 3rd piles, state is now (1, 4, 5)
    - Take from 1st and 3rd piles, state is now (0, 4, 4)
    - Take from 2nd and 3rd piles, state is now (0, 3, 3)
    - Take from 2nd and 3rd piles, state is now (0, 2, 2)
    - Take from 2nd and 3rd piles, state is now (0, 1, 1)
    - Take from 2nd and 3rd piles, state is now (0, 0, 0)
    There are fewer than two non-empty piles, so the game ends. Total: 6 points.


Example 2:
    Input: a = 4, b = 4, c = 6
    Output: 7
    Explanation: The starting state is (4, 4, 6). One optimal set of moves is:
    - Take from 1st and 2nd piles, state is now (3, 3, 6)
    - Take from 1st and 3rd piles, state is now (2, 3, 5)
    - Take from 1st and 3rd piles, state is now (1, 3, 4)
    - Take from 1st and 3rd piles, state is now (0, 3, 3)
    - Take from 2nd and 3rd piles, state is now (0, 2, 2)
    - Take from 2nd and 3rd piles, state is now (0, 1, 1)
    - Take from 2nd and 3rd piles, state is now (0, 0, 0)
    There are fewer than two non-empty piles, so the game ends. Total: 7 points.

Example 3:
    Input: a = 1, b = 8, c = 8
    Output: 8
    Explanation: One optimal set of moves is to take from the 2nd and 3rd piles for 8 turns until they are empty.
    After that, there are fewer than two non-empty piles, so the game ends.

Constraints:
    1 <= a, b, c <= 105
  
*/

/*
 -------------------
 | Greedy solution |
 -------------------

 Here we trying to keep the game running as long as possible 
 to do that we must use the largest pile in combination with other smaller piles 
 if the sum of the to other piles is greater than the largest pile count
 thats means that we guarantee to make greater pile count moves + moves remaing of the other sum divide by two  

Ex: a: 6, b:4, c:4
- sum = 14, max = 6, twoSum = sum - max = 14 - 6 = 8
- here twoSum >= Max, then answer is max + ((twoSum-max) / 2); as remaing moves in two sum after making max moves is to be made by the other two piles

Ex: a:6, b:1, c:1
- sum = 8, max = 6, twoSum = 2 
- here twoSum < max, then the moves to be made is equal to twoSumMoves

*/ 

class Solution {
    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c; 
        int max = Math.max(Math.max(a, b), c);
        int r = sum - max; 
        
        return max > r ? r : (max + ((r-max)>>1)); 
    }
}


