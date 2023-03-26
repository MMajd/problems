/*
 @link https://leetcode.com/problems/longest-cycle-in-a-graph
 @categories (graph/dfs/topological-sort)

 You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
The graph is represented with a given 0-indexed array edges of size n, 
indicating that there is a directed edge from node i to node edges[i]. 
If there is no outgoing edge from node i, then edges[i] == -1.
Return the length of the longest cycle in the graph. If no cycle exists, return -1.

A cycle is a path that starts and ends at the same node.

Example 1:
    Input: edges = [3,3,4,2,3]
    Output: 3
    Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
    The length of this cycle is 3, so 3 is returned.

Example 2:
    Input: edges = [2,-1,3,1]
    Output: -1
    Explanation: There are no cycles in this graph.

Constraints:
    2 <= n <= 10^5
    n == edges.length
    -1 <= edges[i] < n
    edges[i] != i, no cycle of size 1 
*/

// DFS using visited only 
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length; 
        int ans = -1;
        int[] visited = new int[n]; 
        for (int i = 0; i<n; i++) {
            ans = Math.max(ans, dfs(i, 1, edges, visited));
        }
        return ans;
    }

    int dfs(int node, int total, int[] edges, int[] visited) {
        if (node == -1 || visited[node] == -1) return -1; 
        if (visited[node] != 0) return total - visited[node];
        visited[node] = total;
        int result = dfs(edges[node], total + 1, edges, visited);
        visited[node] = -1; // if done, mark node as visited
        return result;
    }
}

// DFS using visited booleans array + seen positions hashmap
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length; 
        int ans = -1; 
        boolean[] visited = new boolean[n]; 
        for (int i=0; i<n; i++) { 
            if (visited[i]) continue; 
            Map<Integer, Integer> posMap = new LinkedHashMap<>(); 
            ans = Math.max(dfs(i, edges, visited, posMap), ans);
        }
        return ans; 
    }
    private int dfs(int node, int[] edges, boolean[] visited, Map<Integer, Integer> posMap) {
        if (node == -1) return -1; 
        if (visited[node]) {
            int size = posMap.size(); 
            int pos = posMap.getOrDefault(node, -1);
            return pos == -1 ? pos : size-pos;
        }
        visited[node] = true; 
        posMap.put(node, posMap.size());
        return dfs(edges[node], edges, visited, posMap); 
    }
}
