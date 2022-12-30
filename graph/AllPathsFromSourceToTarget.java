/*
 @link https://leetcode.com/problems/all-paths-from-source-to-target
 @categories (graph/dfs/bfs/back-tracking)

 Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 
Example 1:
    Input: graph = [[1,2],[3],[3],[]]
    Output: [[0,1,3],[0,2,3]]
    Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Example 2:
    Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
    Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

Constraints:
    n == graph.length
    2 <= n <= 15
    0 <= graph[i][j] < n
    graph[i][j] != i (i.e., there will be no self-loops).
    All the elements of graph[i] are unique.
    The input graph is guaranteed to be a DAG.
*/

class Solution {
    List<List<Integer>> ans; 
    public List<List<Integer>> allPathsSourceTarget(int[][] G) {
        int n = G.length-1;
        ans = new ArrayList<>(); 
        solve(new ArrayList<>(List.of(0)), 0, G);
        return ans; 
    }

    private void solve(List<Integer> route, int i, int[][] G) { 
        if (i == G.length-1) {
            ans.add(new ArrayList<>(route)); 
            return; 
        }

        for (int j : G[i]) {
            route.add(j);
            solve(route, j, G);
            route.remove(route.size()-1);
        }
    }
}
