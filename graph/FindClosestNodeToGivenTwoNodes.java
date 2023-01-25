/*
 @link https://leetcode.com/problems/find-closest-node-to-given-two-nodes/
 @categories (graph/dfs/bfs)

 You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers a and b.

 Return the index of the node that can be reached from both a and b, such that the maximum between the distance from a to that node, and from b to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

- Note that edges may contain cycles.

Example 1:
    Input: edges = [2,2,3,-1], a = 0, b = 1
    Output: 2
    Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
    The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.

Example 2:
    Input: edges = [1,2,-1], a = 0, b = 2
    Output: 2
    Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
    The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
     
Constraints:
    n == edges.length
    2 <= n <= 10^5
    -1 <= edges[i] < n
    edges[i] != i
    0 <= a, b < n
*/

/**
 * Solutions:  
 *      1. DFS Solution
 *      2. BFS Solution 
 */ 
class Solution {
    public int closestMeetingNode(int[] edges, int a, int b) {
        int n = edges.length;
        int[] d1 = new int[n]; 
        int[] d2 = new int[n];
        Arrays.fill(d1, Integer.MAX_VALUE);
        Arrays.fill(d2, Integer.MAX_VALUE);
        d1[a] = 0;
        d2[b] = 0;

        int[] v1 = new int[n]; 
        int[] v2 = new int[n];

        dfs(a, edges, d1, v1);
        dfs(b, edges, d2, v2);

        int minDistNode = -1, minDistTillNow = Integer.MAX_VALUE;
        for (int curr = 0; curr < n; curr++) {
            if (minDistTillNow > Math.max(d1[curr], d2[curr])) {
                minDistNode = curr;
                minDistTillNow = Math.max(d1[curr], d2[curr]);
            }
        }

        return minDistNode;
    }

    public void dfs(int node, int[] edges, int[] dist, int[] visit) {
        visit[node] = 1;
        int next = edges[node];
        if (next != -1 && visit[next] == 0) {
            dist[next] = 1 + dist[node];
            dfs(next, edges, dist, visit);
        }
    }

}


class Solution {
    public void bfs(int startNode, int[] edges, int[] dist) {
        int n = edges.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);

        int[] visit = new int[n];
        dist[startNode] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            if (visit[node]) {
                continue;
            }

            visit[node] = 1;
            int next = edges[node];
            if (next != -1 && visit[next] == 0) {
                dist[next] = 1 + dist[node];
                q.offer(next);
            }

        }
    }

    public int closestMeetingNode(int[] edges, int a, int b) {
        int n = edges.length;
        int[] d1 = new int[n]; 
        int[] d2 = new int[n];
        Arrays.fill(d1, Integer.MAX_VALUE);
        Arrays.fill(d2, Integer.MAX_VALUE);

        bfs(a, edges, d1);
        bfs(b, edges, d2);

        int minDistNode = -1, minDistTillNow = Integer.MAX_VALUE;
        for (int curr = 0; curr < n; curr++) {
            if (minDistTillNow > Math.max(d1[curr], d2[curr])) {
                minDistNode = i;
                minDistTillNow = Math.max(d1[curr], d2[curr]);
            }
        }

        return minDistNode;
    }
}
