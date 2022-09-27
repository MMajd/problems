/*
 @link https://leetcode.com/problems/maximum-segment-sum-after-removals
 @categories (union-find/simulation)
 @link https://www.youtube.com/watch?v=L_EU1nxzFKg
 
 You are given two 0-indexed integer arrays nums and removeQueries, both of length n. For the ith query, the element in nums at the index removeQueries[i] is removed, splitting nums into different segments.

A segment is a contiguous sequence of positive integers in nums. A segment sum is the sum of every element in a segment.

Return an integer array answer, of length n, where answer[i] is the maximum segment sum after applying the ith removal.

Note: The same index will not be removed more than once.

 

Example 1:
    Input: nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
    Output: [14,7,2,2,0]
    Explanation: Using 0 to indicate a removed element, the answer is as follows:
    Query 1: Remove the 0th element, nums becomes [0,2,5,6,1] and the maximum segment sum is 14 for segment [2,5,6,1].
    Query 2: Remove the 3rd element, nums becomes [0,2,5,0,1] and the maximum segment sum is 7 for segment [2,5].
    Query 3: Remove the 2nd element, nums becomes [0,2,0,0,1] and the maximum segment sum is 2 for segment [2]. 
    Query 4: Remove the 4th element, nums becomes [0,2,0,0,0] and the maximum segment sum is 2 for segment [2]. 
    Query 5: Remove the 1st element, nums becomes [0,0,0,0,0] and the maximum segment sum is 0, since there are no segments.
    Finally, we return [14,7,2,2,0].

Example 2:
    Input: nums = [3,2,11,1], removeQueries = [3,2,1,0]
    Output: [16,5,3,0]
    Explanation: Using 0 to indicate a removed element, the answer is as follows:
    Query 1: Remove the 3rd element, nums becomes [3,2,11,0] and the maximum segment sum is 16 for segment [3,2,11].
    Query 2: Remove the 2nd element, nums becomes [3,2,0,0] and the maximum segment sum is 5 for segment [3,2].
    Query 3: Remove the 1st element, nums becomes [3,0,0,0] and the maximum segment sum is 3 for segment [3].
    Query 4: Remove the 0th element, nums becomes [0,0,0,0] and the maximum segment sum is 0, since there are no segments.
    Finally, we return [16,5,3,0].
     

Constraints:
    n == nums.length == removeQueries.length
    1 <= n <= 105
    1 <= nums[i] <= 109
    0 <= removeQueries[i] < n
    All the values of removeQueries are unique.

*/

/** 
 * Observation: we know a data-structure that can merge sets efficiently but we don't know one that disjoint sets with same efficiency as DSU / UnionFind,
 * So we start the solution by simulating that we have carried all remove queries in the queries-array, and make backword unions between the disjoint sets starting from removing the disjoint caused by last remove query till reaching the one made by the first remove query
 */ 

class Solution {
    private static final class DSU { 
        private int[] id; 
        private int[] sz; 
        private long[] sum; 
        private long max; 
        
        private int sets; 
        
        public DSU(int[] vals) {
            int size = vals.length;
            id = new int[size];
            sz = new int[size];
            sum = new long[size]; 
            
            for (int i=0; i<size; i++) {
                id[i] = i; 
                sz[i] = 1; 
                sum[i] = vals[i]; 
                max = Math.max(max, sum[i]);
            }
            
            sets = size; 
        }
        
        public long max() {
            return max; 
        }
        
        public void setSum(int idx, long val) { 
            sum[idx] = val;  
            max = Math.max(max, sum[idx]);
        }

        public int find(int x) { 
            int root = x; 
            
            while (root != id[root]) root = id[root]; 
            
            int next; 
            while (x != root) {
                next = id[x]; 
                id[x] = root; 
                x = next; 
            }

            return root;
        }
        
        public boolean union(int x, int y) { 
            int xroot = find(x);
            int yroot = find(y);
            
            if (xroot == yroot) {
                max = Math.max(max, sum[xroot]);
                return false; 
            }
            
            
            if (sz[xroot] > sz[yroot]) { 
                id[yroot] = xroot; 
                sz[xroot] += sz[yroot]; 
                sum[xroot] += sum[yroot];
                max = Math.max(max, sum[xroot]);
            }
            else { 
                id[xroot] = yroot; 
                sz[yroot] += sz[xroot]; 
                sum[yroot] += sum[xroot];
                max = Math.max(max, sum[yroot]);
            }
            
            sets -= 1; 
            return true; 
        }
    }
    
    public long[] maximumSegmentSum(int[] nums, int[] Q) {
        int n = nums.length; 
        
        // as Q values are unique & len(Q) = len(nums)
        // then after carrying all queries nums-array will be all zeros; 
        int[] removed = new int[n];
        
        // Only needed if len(Q) != len(nums) or Q values is not unique
        // for (int i : Q) removed[i] = 0;
        
        DSU dsu = new DSU(removed);
        
        // Only needed if len(Q) != len(nums)
        // for (int i=0; i<n; i++) { 
        //     if (removed[i] == 0) continue;
        //     
        //     int start = i; 
        //     while (i < n && removed[i] != 0) { 
        //         dsu.union(start, i++);
        //     }
        // }
        
        long[] ans = new long[n]; 
        
        for (int i=n-1; i>=0; --i) { 
            ans[i] = dsu.max();
            
            int idx = Q[i]; 
            removed[idx] = nums[idx]; 
            dsu.setSum(idx, nums[idx]);
            
            if (idx>0 && removed[idx-1] != 0) dsu.union(idx, idx-1);
            if (idx<n-1 && removed[idx+1] != 0) dsu.union(idx, idx+1);
        }
        
        return ans; 
    }
}


/* Simulation Solution */
class Solution {
    public long[] maximumSegmentSum(int[] nums, int[] Q) {
        int n = nums.length; 
        
        long[] ans = new long[n]; 
        Long[] prefix = new Long[n+1];
        
        prefix[0] = Long.valueOf(0); 
        
        for (int i=1; i<=n; i++) { 
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        
        TreeSet<Integer> removedElements = new TreeSet<>(); 
        TreeMap<Long, Long> sums = new TreeMap<>(); 
        
        sums.put(prefix[0], Long.valueOf(1));
        sums.put(prefix[n], Long.valueOf(1));
        
        
        for (int i=0; i<n; i++) {
            // Edit query index to work with preifx sum array correctly
            Q[i] += 1; 
            
            Integer l = removedElements.lower(Q[i]);
            Integer r = removedElements.higher(Q[i]); 
            
            l = l != null ? l+1 : 1; 
            r = r != null ? r-1 : n; 
            
            Long rangeSum = prefix[r]-prefix[l-1]; 
            
            sums.computeIfPresent(rangeSum, (k, v) -> v>1? --v: null);
            
            // interval [L, Q[i]-1]
            if (l <= Q[i]-1) {
                sums.compute(prefix[Q[i]-1]-prefix[l-1], (k, v) -> v!=null? ++v : 1);
            }

            // interval [Q[i], R]
            if (Q[i]+1 <= r) {
                sums.compute(prefix[r]-prefix[Q[i]], (k, v) -> v!=null? ++v : 1);
            }
            removedElements.add(Q[i]);
            ans[i] = sums.lastKey().longValue();
        }
        
        return ans; 
    }
}
