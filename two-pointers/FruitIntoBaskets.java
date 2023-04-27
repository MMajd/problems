/*
 @link https://leetcode.com/problems/fruit-into-baskets
 @categories(hash-table/sliding-window) 

 You are visiting a farm that has a single row of fruit trees arranged from left to right. 
The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
You want to collect as much fruit as possible. However, 
the owner has some strict rules that you must follow:
 You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Example 1:
    Input: fruits = [1,2,1]
    Output: 3
    Explanation: We can pick from all 3 trees.

Example 2:
    Input: fruits = [0,1,2,2]
    Output: 3
    Explanation: We can pick from trees [1,2,2].
    If we had started at the first tree, we would only pick from trees [0,1].

Example 3:
    Input: fruits = [1,2,3,2,2]
    Output: 4
    Explanation: We can pick from trees [2,3,2,2].
    If we had started at the first tree, we would only pick from trees [1,2].

Constraints:
    1 <= fruits.length <= 10^5
    0 <= fruits[i] < fruits.length
 */

/** 
 * Another statement for the problem is: 
 *  max sub-array with two values; 
 */ 
class Solution {
    private static final int MAX_BASKETS  = 2;

    public int totalFruit(int[] A) {
        Map<Integer, Integer> map = new HashMap<>(); 

        final int n = A.length; 
        int left = 0, right; 
        for (right=0; right<n; ++right) { 
            map.merge(A[right], 1, Integer::sum);
            // move left if we only has more than two elements
            if (map.size() > MAX_BASKETS) { 
                map.merge(A[left], -1, Integer::sum); 
                if (map.get(A[left]) == 0) { 
                    map.remove(A[left]);
                }
                left += 1; // advance the left pointer
            }
        }

        return right - left; 
    }
}
