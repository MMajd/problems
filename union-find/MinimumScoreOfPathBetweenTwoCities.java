/*
 @link https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities
 @categories (bfs/dfs/union-find)

 You are given a positive integer n representing n cities numbered from 1 to n. 
You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that 
there is a bidirectional road between cities ai and bi with a distance equal to distancei. 
The cities graph is not necessarily connected.
The score of a path between two cities is defined as the minimum distance of a road in this path.
Return the minimum possible score of a path between cities 1 and n.

Note:
- A path is a sequence of roads between two cities.
- It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
- The test cases are generated such that there is at least one path between 1 and n.

Example 1:
    Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
    Output: 5
    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
    It can be shown that no other path has less score.

Example 2:
    Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
    Output: 2
    Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.

Constraints:
    2 <= n <= 10^5
    1 <= roads.length <= 10^5
    roads[i].length == 3
    1 <= ai, bi <= n
    ai != bi
    1 <= distancei <= 10^4
    There are no repeated edges.
    There is at least one path between 1 and n.
*/

import static java.lang.Math.*; 

class Solution {
    private class DSU { 
        int[] parent;
        int[] rank; 

        public DSU(int size) {
            parent = new int[size]; 
            rank = new int[size]; 
            for (int i=0; i<size; i++) {
                parent[i] = i; 
                rank[i] = Integer.MAX_VALUE; 
            }
        }

        public int rank(int u) {
            return rank[find(u)]; 
        }

        public int find(int u) {
            if (u != parent[u]) return find(parent[u]);
            return parent[u] = u; 
        }

        public boolean union(int u, int v, int r) {
            int a = find(u); 
            int b = find(v); 
            if (rank[a] <= rank[b]) { 
                parent[b] = a; 
            } else { 
                parent[a] = b; 
            }
            rank[a] = rank[b] = min(r, min(rank[a], rank[b]));
            return true; 
        }
    }

    public int minScore(int n, int[][] roads) {
        DSU dsu = new DSU(n+1); 
        for (int[] road : roads) {
            dsu.union(road[0], road[1], road[2]);
        }
        return dsu.rank(1); // or rank(n) 
    }
}

// DSU: the short version
public class Solution {
    int[] dsu; // to be used in find
    public int minScore(int n, int[][] roads) {
        dsu = new int[n+1];
        int[] ans = new int[n+1];

        Arrays.fill(ans, Integer.MAX_VALUE);
        for(int i = 0; i <= n; i++) {
            dsu[i] = i;
        }

        for(int[] r : roads) {
            int a = find(r[0]), b = find(r[1]);
            dsu[a] = dsu[b];
            ans[a] = ans[b] = min(r[2], min(ans[a], ans[b]));
        }

        return ans[find(1)]; // or rank of n 
    }

    int find(int i) {
        return dsu[i]==i ? i : (dsu[i] = find(dsu[i]));
    }
}


class Solution {
    public int minScore(int n, int[][] roads) {
        int ans = Integer.MAX_VALUE;
        List<List<Pair<Integer, Integer>>> gr = new ArrayList<>(n+1);

        for (int i = 0; i < n+1; i++) {
            gr.add(new ArrayList<Pair<Integer, Integer>>());
        }

        for (int[] edge : roads) { 
            gr.get(edge[0]).add(new Pair<>(edge[1], edge[2])); // u-> {v, dis}
            gr.get(edge[1]).add(new Pair<>(edge[0], edge[2])); // v-> {u, dis}
        }

        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (Pair<Integer, Integer> pair : gr.get(node)) {
                int v = pair.getKey();
                int dis = pair.getValue();
                ans = min(ans, dis);
                if (!vis[v]) {
                    vis[v] = true;
                    q.add(v);
                }
            }
        }

        return ans;
    }
}

