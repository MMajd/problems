/*
 @link https://leetcode.com/problems/maximum-total-beauty-of-the-gardens
 @categories (greedy/sorting/prefix-sum/two-pointers/binary-search)

 Alice is a caretaker of n gardens and she wants to plant flowers to maximize the total beauty of all her gardens.
You are given a 0-indexed integer array flowers of size n, 
where flowers[i] is the number of flowers already planted in the ith garden. 
Flowers that are already planted cannot be removed. 
You are then given another integer newFlowers, which is the maximum number of flowers that Alice can additionally plant. 
You are also given the integers target, full, and partial. A garden is considered complete if it has at least target flowers. 
The total beauty of the gardens is then determined as the sum of the following:
The number of complete gardens multiplied by full.
The minimum number of flowers in any of the incomplete gardens multiplied by partial. 
If there are no incomplete gardens, then this value will be 0.
Return the maximum total beauty that Alice can obtain after planting at most newFlowers flowers.

- Depend on math. max. funciton of two variables, we fix one and vary the other  

Example 1:
    Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
    Output: 14
    Explanation: Alice can plant
    - 2 flowers in the 0th garden
    - 3 flowers in the 1st garden
    - 1 flower in the 2nd garden
    - 1 flower in the 3rd garden
    The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
    There is 1 garden that is complete.
    The minimum number of flowers in the incomplete gardens is 2.
    Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
    No other way of planting flowers can obtain a total beauty higher than 14.

Example 2:
    Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
    Output: 30
    Explanation: Alice can plant
    - 3 flowers in the 0th garden
    - 0 flowers in the 1st garden
    - 0 flowers in the 2nd garden
    - 2 flowers in the 3rd garden
    The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
    There are 3 gardens that are complete.
    The minimum number of flowers in the incomplete gardens is 4.
    Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
    No other way of planting flowers can obtain a total beauty higher than 30.
    Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.

Constraints:
    1 <= flowers.length <= 10^5
    1 <= flowers[i], target <= 10^5
    1 <= newFlowers <= 10^10
    1 <= full, partial <= 10^5
*/

/** Binary search */
class Solution {
    public long maximumBeauty(int[] A, long newFlowers, int target, int full, int partial) {
        int n = A.length;
        long[] cost = new long[n];
        Arrays.sort(A);

        // all are full
        if(A[0] >= target) {
            return (long) full * n; 
        }

        for(int i = 1; i < n; i++) {
            A[i] = Math.min(A[i], target);
            cost[i] = cost[i-1] + i * (A[i] - A[i-1]);
        }
        
        // have enough flowers
        if(newFlowers >= cost[n-1] + (target - A[n-1]) * n) {
            // need proof
            return Math.max((long) full * n, full * (n - 1L) + partial * (target - 1L));
        }
        
        long ans = 0; 
        long left = newFlowers;
        for (int i=n-1; i>=0 && left >= 0; i--) { 
            if(A[i] == target) continue;
            int idx = Arrays.binarySearch(cost, 0, i+1, left);
            if(idx < 0) idx = -idx-2;
            long bar = A[idx] + (left - cost[idx]) / (idx + 1);
            ans = Math.max(ans, bar * partial + (long)full * (n-i-1));
            left -= (target - A[i]);
        }
        
        return ans;
    }
}

/** Two-pointers approach */
class Solution {
    public long maximumBeauty(int[] A, long newFlowers, int target, int full, int partial) {
        int n = A.length;
        long[] presum = new long[n + 1];
        Arrays.sort(A);

        if (A[0] >= target) { 
            return (long) n * full;  
        }

        for (int i = 0; i < n; ++i) {
            A[i] = Math.min(target, A[i]);
            presum[i+1] = presum[i] + A[i]; 
        }

        long ans = 0;
        for (int c = 0, i = n - 1; c <= n; ++c) {
            long best = 0; 
            long remain = presum[n] - presum[n-c] + newFlowers - (long)c*target;  
            if (0 > remain) break;
            i = Math.min(i, n-c-1);
            while (0 <= i && (target == A[i] || A[i]*(i+1L) - presum[i+1] > remain)) i--;
            if (0 <= i) {
                long diff = A[i] * (i+1L) - presum[i + 1];
                long total = remain - diff; 
                long each = A[i] + total / (i+1); 
                best = Math.min(target-1, each);
            }
            ans = Math.max(ans, 1L*c*full + 1L*best*partial);
        }
        return ans;
    }
}
