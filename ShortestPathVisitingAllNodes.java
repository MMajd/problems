/** 
 @categories(bst/queue/bitmask/shortest-path]  
 @link https://leetcode.com/problems/shortest-path-visiting-all-nodes/description
  
You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 

Example 1:
    Input: graph = [[1,2,3],[0],[0],[0]]
    Output: 4
    Explanation: One possible path is [1,0,2,0,3]

Example 2:
    Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
    Output: 4
    Explanation: One possible path is [0,1,4,2,3]

Constraints:
    n == graph.length
    1 <= n <= 12
    0 <= graph[i].length < n
    graph[i] does not contain i.
    If graph[a] contains b, then graph[b] contains a.
    The input graph is always connected.
 */


// Implicitly storing visit info in dp[node][state] value, 
// if MAX_VALUE means not visited else means visited 
// Other approach below 
class Solution {
    public int shortestPathLength(int[][] graph) {
        final int n = graph.length; 
        final int done = (1 << n) - 1; 
        // not visited means MAX_VALUE
        // contains distance := dp[node][state]
        final int[][] dp = new int[n][1<<n]; 

        // structure: [node, state]
        final Deque<int[]> q = new ArrayDeque<>(); 

        for(int i=0; i<n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][1<<i] = 0;
            q.offer(new int[]{i, 1<<i});
        }

        while (!q.isEmpty()) { 
            int[] curr = q.poll(); 
            System.out.println(Arrays.toString(curr));
            int node = curr[0]; 
            int state = curr[1]; 
            int dist = dp[node][state]; 

            if (state == done) return dist; 

            for (int neigh : graph[node]) { 
                int nextState = state | (1 << neigh);
                if (dp[neigh][nextState] > dist + 1) {
                    dp[neigh][nextState] = dist + 1; 
                    q.offer(new int[]{neigh, nextState});
                }
            }
        }

        return -1; 
    }
}

// having explicit visited bitmap
class Solution {
    public int shortestPathLength(int[][] graph) {
        final int n = graph.length; 
        final int done = (1 << n) - 1; 
        final boolean[][] visited = new boolean[n][1<<n]; 

        // structure: [mask/state, node, dist]
        final Deque<int[]> q = new ArrayDeque<>(); 

        for (int i=0; i<n; i++) { 
            q.offer(new int[] {1<<i, i, 0});
        }

        while (!q.isEmpty()) { 
            int[] tuple = q.poll(); 
            int state = tuple[0]; 
            int node = tuple[1]; 
            int dist = tuple[2]; 

            if (state == done) return dist; 

            for (int i=0; i<graph[node].length; i++) { 
                int neigh = graph[node][i]; 
                int nextState = state | (1 << neigh); 
                if (visited[neigh][nextState]) continue; 
                visited[neigh][nextState] = true; 
                q.offer(new int[]{nextState, neigh, dist + 1});
            }
        }

        return -1; 
    }
}
