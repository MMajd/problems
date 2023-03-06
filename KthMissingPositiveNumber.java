/*
 @link https://leetcode.com/problems/kth-missing-positive-number
 @categories (binary-search) 

 Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Return the kth positive integer that is missing from this array.

Example 1:
    Input: arr = [2,3,4,7,11], k = 5
    Output: 9
    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
    Input: arr = [1,2,3,4], k = 2
    Output: 6
    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

Constraints:
    1 <= arr.length <= 1000
    1 <= arr[i] <= 1000
    1 <= k <= 1000
    arr[i] < arr[j] for 1 <= i < j <= arr.length
 
Follow up:
    Could you solve this problem in less than O(n) complexity?
*/ 

/**
 * Idea behind the solution is as such 
 * If we have all numbers from 1 to n in the array without any one missing, and no repeation [strictly increasing] 
 * this equation will be statisified for every index arr[i] - i - 1 = 0, as arr[0] starts from 1
 * to dedicate missing till some index x we apply the equation arr[x] - x - 1 = number of missing numbers inside the array 
 * we use this previous idea to do binary search on number of missing numbers inside the sorted array 
 */ 

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int left = 0; 
        int right = arr.length-1; 

        while (left <= right) { 
            int mid = left + (right - left) / 2; 

            if (arr[mid] - mid - 1 < k) { // missing number less the k increase left 
                left = mid + 1; 
            }
            else { // missing numbers eq/bigger than k decrease right boundary 
                right = mid - 1; 
            }
        }

        return left + k; 
    }
}
