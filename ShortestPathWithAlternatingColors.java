/** 
 @link https://leetcode.com/problems/shortest-path-with-alternating-colors
 @categories (bfs/shortest-path) 
  
 You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:
- redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
- blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.

Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

Example 1:
    Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
    Output: [0,1,-1]

Example 2:
    Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
    Output: [0,1,-1]
 

Constraints:
    1 <= n <= 100
    0 <= redEdges.length, blueEdges.length <= 400
    redEdges[i].length == blueEdges[j].length == 2
    0 <= ai, bi, uj, vj < n
*/ 


/** 
 * Proof, Why result of reach vertix v with alternating colors 
 * will be always min?
 *
 * Using BFS in case of unweighted graph will result in min 
 * number of steps to reach vertix v, as all vertices has same probabiltity to be choosen to be visited from vertix u with cost = 1 + cost[u] 
 * hence vertices that appears ealier in our queue will always have min steps
 */
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] reds, int[][] blues) {
        var dist=new int[n][2];
        Arrays.setAll(dist, i -> { 
            Arrays.fill(dist[i], -1);
            return dist[i]; 
        }); 
        dist[0]=new int[]{0, 0};

        var adj=new HashMap<Integer, List<int[]>>();
        for (var e : reds) {
            adj.computeIfAbsent(e[0], k -> new ArrayList<>()).add(new int[]{e[1], 0});
        }
        for (var e : blues) {
            adj.computeIfAbsent(e[0], k -> new ArrayList<>()).add(new int[]{e[1], 1});
        }

        var q = new ArrayDeque<int[]>();
        q.add(new int[]{0, 0});
        q.add(new int[]{0, 1});

        while (!q.isEmpty()) {
            var c = q.poll();
            int prev = c[0]; 
            int prevcolor = c[1]; 

            for (var ne : adj.getOrDefault(prev, new ArrayList<>())) {
                int next = ne[0]; 
                int nextcolor = ne[1]; 
                if (prevcolor == nextcolor || dist[next][nextcolor] != -1) continue; 
                int cost = 1 + dist[prev][prevcolor]; 
                dist[next][nextcolor] = cost; 
                q.add(new int[]{next, nextcolor, cost});
            }
        }

        var a = new int[n];
        for (var i=0; i<dist.length; i++) {
            if(dist[i][0]==-1 || dist[i][1]==-1) { 
                a[i] = Math.max(dist[i][0], dist[i][1]);
            }
            else {
                a[i] = Math.min(dist[i][0], dist[i][1]);
            }
        }
        return a;
    }
}
