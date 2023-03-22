/*
 @link 
 @categories (bfs/dfs/union-find)

 You are given a positive integer n representing n cities numbered from 1 to n. 
You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that 
there is a bidirectional road between cities ai and bi with a distance equal to distancei. 
The cities graph is not necessarily connected.
The score of a path between two cities is defined as the minimum distance of a road in this path.
Return the minimum possible score of a path between cities 1 and n.

Note:
A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.

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

static import java.lang.Math.*; 

class Solution {
    int[] par,rank,values;
    
    public int find(int a) {
        if(par[a] == a) return a;
        return par[a] = find(par[a]);
    }
    
    public void union(int a,int b,int val) {
        int ra = find(a), rb = find(b);
        
        values[ra] = min(val, min(values[ra], values[rb]));
        values[rb] = min(val, min(values[ra], values[rb]));
        
        if(ra != rb) {
            if(rank[ra] > rank[rb]) {
                par[rb] = ra;
                rank[ra] += rank[rb];
                
            } else {
                par[ra] = par[rb];
                rank[rb] += rank[ra];
            }
        }
    }
    
    public int minScore(int n, int[][] roads) {
        par = new int[n];
        rank = new int[n];
        values = new int[n];
        
        for(int i = 0;i < n;i++) {
            par[i] = i;
            rank[i] = 1;
            values[i] = Integer.MAX_VALUE;
        }
        
        for(int[] road : roads) {
            int u = road[0] - 1, v = road[1] - 1, wt = road[2];
            union(u,v,wt);
        }

        return values[find(0)];
    }
}

class Solution {
    public int minScore(int n, int[][] roads) {
        int ans = Integer.MAX_VALUE;
        List<List<Pair<Integer, Integer>>> gr = new ArrayList<>();
        for(int i = 0; i < n+1; i++) {
            gr.add(new ArrayList<Pair<Integer, Integer>>());
        }

        for(int[] edge : roads) { 
            gr.get(edge[0]).add(new Pair<>(edge[1], edge[2])); // u-> {v, dis}
            gr.get(edge[1]).add(new Pair<>(edge[0], edge[2])); // v-> {u, dis}
        }

        int[] vis = new int[n+1];
        Arrays.fill(vis, 0);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = 1;
        while(!q.isEmpty()) {
            int node = q.poll();
            for(Pair<Integer, Integer> pair : gr.get(node)) {
                int v = pair.getKey();
                int dis = pair.getValue();
                ans = Math.min(ans, dis);
                if(vis[v]==0) {
                    vis[v] = 1;
                    q.add(v);
                }
            }
        }
        return ans;
    }
}
