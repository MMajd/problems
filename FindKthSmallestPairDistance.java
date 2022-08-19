/*
 
 @link https://leetcode.com/problems/find-k-th-smallest-pair-distance/
  
  
 The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

 

Example 1:
    Input: nums = [1,3,1], k = 1
    Output: 0
    Explanation: Here are all the pairs:
    (1,3) -> 2
    (1,1) -> 0
    (3,1) -> 2
    Then the 1st smallest distance pair is (1,1), and its distance is 0.

Example 2:
    Input: nums = [1,1,1], k = 2
    Output: 0

Example 3:
Input: nums = [1,6,1], k = 3
Output: 5
 
Constraints:
    n == nums.length
    2 <= n <= 10^4
    0 <= nums[i] <= 10^6
    1 <= k <= n * (n - 1) / 2

*/

class Solution {
    /**
       NOTES: 
        * First of all we sort input array
        * After sorting we are able to know the range of distances in the problem 
            * The range is [0, num[last]-num[first]]
             Ex: assume given array after sorting is: [1,3,4,5,7,10,15,16] 
             and the wanted distance is 3rd, k=3, OUR RANGE IS [0, 16-1] -> [0, 15]
         
        * Given we having a range to search in it we are able to use some form of Binary Search
        * So we need to have a cariteria to know which direction to take in searching our range, 
        either to go to the left or to go to the right 
        
        * To know which direction to go we compare target distance order (kth) 
        and then count of pairs having distances between them less than the current mid-distance. 
        Here the count of pairs having distances less than the mid-distance is equal to the number 
        of distances less than the mid one, ex: if we have 8 pairs with distances less than or equal say 7 
        and we are searching for the 4th smallest distance, then we have 8 possible distances we choose the 4th one of them, 
        So we make shorten our search range to become [0, 7] 
        
        and repeat like that till we find the kth smallest distance will be either low or heigh
        
        Ex: 
            Given [1,3,4,5,7,10,15,16] find the kth smallest pair distance, k=3 
            * Our search range is [0, 15], mid-distance = 7
            * Count how many pairs in the array having distances between them less than or equal to 7 
            We find that we have 17 pairs, meaning 17 or less possible distances, 
            so we make our search range: [0, 7], mid-distance = 3, 
            counting distances less than or equal to 3 we find we have 9 possilbe distances 
            So we take range: [0, 3], mid-distance 1, we find 3 possible distances less than or equal to 1
            So we take then range: [0, 1] then we repeat until our search becomes [1, 1]
            then we return 1 
            
        Ex: Given [1,1,3] find the 1st smallest distances (k=1), 
            *. Search range: [0, 2], mid = 1
            After counting we find 3 different or less possible distances less or equal to 1 
            then range become: [0, 1], mid: 0, 
            After counting we find 2 possible distances less than or equal to 0 
            so search range become [0, 0], loops break and we return 0 
    */ 
    

    public int smallestDistancePair(int[] arr, int k) {
        Arrays.sort(arr);
        
        int n = arr.length; 
        int low = 0; 
        int high = arr[n-1]-arr[0]; 
        
        while (low < high) {
            int mid = low + (high-low)/2; 
            
            int count=0, left=0; 
            for (int right=0; right<n; ++right) { 
                while(arr[right]-arr[left] > mid) left++; 
                count += right-left; 
            }
            
            
            if (count >= k) high = mid-1; 
            else low = mid+1; 
        }
        
        return low; 
    }
}
