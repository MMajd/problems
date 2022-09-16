/*
  
 @link https://leetcode.com/problems/minimum-cost-to-hire-k-workers/submissions/
  
  
 There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.


Example 1:
    Input: quality = [10,20,5], wage = [70,50,30], k = 2
    Output: 105.00000
    Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

Example 2:
    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
    Output: 30.66667
    Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 

Constraints:
    n == quality.length == wage.length
    1 <= k <= n <= 104
    1 <= quality[i], wage[i] <= 104
  
 */




class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = wage.length; 
        int[][] workers = new int[n][2]; 
        
        for (int i=0; i<n; i++) { 
            workers[i] = new int[]{quality[i], wage[i]}; 
        }
        
        Arrays.sort(workers, (a, b) -> 
                    Double.compare(1.0*a[1]/a[0], 1.0*b[1]/b[0]));

        
        Queue<Integer> pq = new PriorityQueue<>(k); 
        
        double ans = Double.MAX_VALUE; 
        int sum = 0; 
        
        for (int[] w : workers) { 
            System.out.println(pq);
            pq.offer(-w[0]);
            sum += w[0]; 
            
            if (pq.size() > k) sum += pq.poll();
            if (pq.size() == k) ans = Math.min(ans, 1.0*sum*w[1]/w[0]);
        }
        
        
        return ans; 
    }
}
