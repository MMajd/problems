/*
 @link https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 @categories (bfs/dfs)

 There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
It's guaranteed that each city can reach city 0 after reorder.

Example 1:
    Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
    Output: 3
    Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 2:
    Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
    Output: 2
    Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 3:
    Input: n = 3, connections = [[1,0],[2,0]]
    Output: 0

Constraints:
    2 <= n <= 5 * 10^4
    connections.length == n - 1
    connections[i].length == 2
    0 <= ai, bi <= n - 1
    ai != bi
*/

import static java.lang.Math.*; 

// BFS  
class Solution {
    public int minReorder(int n, int[][] connections) {
        Deque<Integer> q = new LinkedList<>(); 
        Map<Integer, List<Integer>> adj = new HashMap<>(n); 
        boolean[] visited = new boolean[n];

        for (int[] conn : connections) { 
            adj.computeIfAbsent(conn[0], k -> new LinkedList<>()).add(conn[1]);
            adj.computeIfAbsent(conn[1], k -> new LinkedList<>()).add(-conn[0]);
        }

        int ans = 0; 
        q.add(0);
        visited[0] = true; 

        while (!q.isEmpty()) { 
            int size = q.size();

            for (int i=0; i<size; i++) {
                int node = q.poll(); 
                for (int nei : adj.get(node)) {
                    if (!visited[abs(nei)]) {
                        ans += nei > 0 ? 1 : 0; 
                        visited[abs(nei)] = true; 
                        q.add(abs(nei));
                    }
                }
            }
        }

        return ans; 
    }
}

// DFS 
import static java.lang.Math.*; 

class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> adj = new LinkedHashMap<>(); 
        for (int[] conn : connections) {
            adj.computeIfAbsent(conn[0], k -> new LinkedList<>()).add(conn[1]);
            adj.computeIfAbsent(conn[1], k -> new LinkedList<>()).add(-conn[0]);
        }
        return dfs(-1, 0, adj); 
    }

    private int dfs(int parent, int from, Map<Integer, List<Integer>> adj) {
        int ans = 0; 
        for (int nei : adj.get(from)) {
            if (abs(nei) == parent) continue;
            ans += dfs(from, abs(nei), adj) + (nei > 0 ? 1 : 0); 
        }
        return ans; 
    }
}

