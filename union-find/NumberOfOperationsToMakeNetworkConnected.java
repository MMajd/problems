/*
 @link https://leetcode.com/problems/number-of-operations-to-make-network-connected
 @categories (bfs/dfs/union-find) 

 There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network 
where connections[i] = [ai, bi] represents a connection between computers ai and bi. 
Any computer can reach any other computer directly or indirectly through the network.
You are given an initial computer network connections. You can extract certain cables between two directly connected computers, 
and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

Example 1:
    Input: n = 4, connections = [[0,1],[0,2],[1,2]]
    Output: 1
    Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:
    Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
    Output: 2

Example 3:
    Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
    Output: -1
    Explanation: There are not enough cables.

Constraints:
    1 <= n <= 10^5
    1 <= connections.length <= min(n * (n - 1) / 2, 10^5)
    connections[i].length == 2
    0 <= ai, bi < n
    ai != bi
    There are no repeated connections.
    No two computers are connected by more than one cable.
*/

class Solution {
    private class DSU {
        private int[] ids; 
        private int[] sz; 
        private int components;

        public DSU(int n) {
            ids = new int[n]; 
            sz = new int[n]; 
            Arrays.setAll(ids, i -> {
                sz[i] = 1; 
                return i; 
            }); 
            components = n-1; 
        }

        public int components() { 
            return components; 
        }

        public int find(int u) {
            if (ids[u] != u) ids[u] = find(ids[u]);
            return ids[u]; 
        }

        public boolean union(int u, int v) {
            int proot = find(u); 
            int qroot = find(v); 
            if (proot == qroot) return false; 

            components -= 1; 
            if (sz[proot] >= sz[qroot]) { 
                ids[qroot] = proot; 
                sz[proot] += sz[qroot]; 
            } else { 
                ids[proot] = qroot; 
                sz[qroot] += sz[proot]; 
            }
            return true; 
        }
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n-1) return -1; 
        DSU dsu = new DSU(n); 
        for (int[] conn : connections) { 
            dsu.union(conn[0], conn[1]);
        }
        return dsu.components(); 
    }
}
