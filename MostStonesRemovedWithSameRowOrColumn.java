/* 
 @link https://leetcode.com/problems/most-stones-removed-with-same-row-or-column
 @categories (graph/union-find/dfs) 

 On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.


Example 1:
    Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    Output: 5
    Explanation: One way to remove 5 stones is as follows:
    1. Remove stone [2,2] because it shares the same row as [2,1].
    2. Remove stone [2,1] because it shares the same column as [0,1].
    3. Remove stone [1,2] because it shares the same row as [1,0].
    4. Remove stone [1,0] because it shares the same column as [0,0].
    5. Remove stone [0,1] because it shares the same row as [0,0].
    Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

Example 2:
    Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    Output: 3
    Explanation: One way to make 3 moves is as follows:
    1. Remove stone [2,2] because it shares the same row as [2,0].
    2. Remove stone [2,0] because it shares the same column as [0,0].
    3. Remove stone [0,2] because it shares the same row as [0,0].
    Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

Example 3:
    Input: stones = [[0,0]]
    Output: 0
    Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
     

Constraints:
    1 <= stones.length <= 1000
    0 <= xi, yi <= 10^4
    No two stones are at the same coordinate point.
*/


class Solution {
    private static class DSU {
        private int[] parent; 
        private int[] sz; 
        private int components; 
        private int max; 

        public DSU(int size) {
            parent = new int[size]; 
            sz = new int[size]; 

            Arrays.setAll(parent, i -> {
                sz[i] = 1; 
                return i; 
            });

            max = 1; 
            components = size; 
        }

        public int components() {
            return components; 
        }

        public int maxComponentSize() { 
            return max-1; 
        }

        public int find(int u) { 
            if (u != parent[u]) parent[u] = find(parent[u]); 
            return parent[u];
        }

        public boolean union(int u, int v) { 
            int p = find(u); 
            int q = find(v); 

            if (p == q) return false; 

            if (sz[p] >= sz[q]) { 
                parent[q] = p; 
            } 
            else { 
                parent[p] = q; 
            }

            sz[p] += sz[q]; 
            sz[q] = sz[p]; 

            components -= 1; 
            max = Math.max(max, sz[p]); 

            return true; 
        }
    }

    public int removeStones(int[][] A) {
        int N = A.length; 
        if (N == 1) return 0; 

        DSU dsu = new DSU(N); 

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (A[i][0] == A[j][0]
                    || A[i][1] == A[j][1]) dsu.union(i, j);
            }
        }

        return N - dsu.components(); 
    }
}

