/*
 @link https://leetcode.com/problems/cheapest-flights-within-k-stops
 @categories (dp/bfs/dfs/djikstra)

 There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

Example 1:
    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    Output: 700
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
    Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

Example 2:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

Example 3:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph is shown above.
    The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.

Constraints:
    1 <= n <= 100
    0 <= flights.length <= (n * (n - 1) / 2)
    flights[i].length == 3
    0 <= fromi, toi < n
    fromi != toi
    1 <= pricei <= 10^4
    There will not be any multiple flights between two cities.
    0 <= src, dst, k < n
    src != dst
*/

/**
 * Djistra Solution 
 */ 

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] i : flights)
            adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // {dist_from_src_node, node, number_of_stops_from_src_node}
        pq.offer(new int[] { 0, src, 0 });

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int dist = temp[0];
            int node = temp[1];
            int steps = temp[2];
            // was seen with less steps or steps violate our constraint
            if (steps > stops[node] || steps > k + 1) continue;
            
            stops[node] = steps; // update
                                 //
            if (node == dst) return dist; // this is the answer as we always sort by lesser cost, proof by contradiction

            for (int[] a : adj.getOrDefault(node, new ArrayList<>)) {
                pq.offer(new int[] { dist + a[1], a[0], steps + 1 });
            }
        }

        return -1; // no way dst can be reach from src with given constraint [k steps]
    }
}

/** 
 * DP Solution,
 * Our state consist of dp[k][u] number of stops k need to reach a city u
 * If we can reach city u with k-1 stops from city v with cost better than reach u with k stops, update dp[k][u]
 * We by setting k = 1 and update all cities reach with 1 stops from the src, and go on till k is maxStops+2
 */ 

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        long[][] dp = new long[k+2][n];
        Arrays.setAll(dp, i -> { 
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            return dp[i]; 
        });

        dp[0][src] = 0; 

        for (int i=1; i<=k+1; i++) { 
            dp[i][src] = 0; // src is reachable for all k stops with zero cost

            for (int[] f : flights) { 
                int from = f[0], to = f[1], cost = f[2]; 

                dp[i][to] = Math.min(dp[i][to], dp[i-1][from] + cost);
            }
        }

        return (int)(dp[k+1][dst] == Integer.MAX_VALUE ? -1 : dp[k+1][dst]);
    }
}


/**
 * BFS, with memoization array
 */ 
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Map<Integer, List<int[]>> adj = new HashMap<>();
        Queue<int[]> q = new LinkedList<>();
        
        for (int[] f : flights) { 
            int from = f[0], to = f[1], cost = f[2]; 
            adj.computeIfAbsent(from, value -> new ArrayList<>()).add(new int[] {to, cost});
        }


        int stops = k + 1;
        q.offer(new int[] {src, 0});

        while (stops-- > 0 && !q.isEmpty()) {
            int sz = q.size();

            for(int i=0; i<sz; i++) {
                int[] curr = q.poll();
                int city = curr[0];
                int cost = curr[1];

                for (int[] nei : adj.getOrDefault(city, new ArrayList<>())) {
                    int next = nei[0];
                    int total = cost + nei[1];

                    if (total >= dist[next] || total >= dist[dst]) continue;

                    q.offer(new int[] {next, total});
                    dist[next] = total; 
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

/** DFS With Memoization Solution */
class Solution {
    private final static int INF = Integer.MAX_VALUE; 

    private int dst; 

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.dst = dst; 

        int[][] memo= new int[n][n+1]; // given contraint
        Map<Integer, List<int[]>> adj = new HashMap<>(); 

        Arrays.setAll(memo, i -> {
            Arrays.fill(memo[i], -1);
            return memo[i]; 
        });

        for (int[] f : flights) { 
            int from = f[0], to = f[1], cost = f[2]; 
            adj.computeIfAbsent(from, u -> new ArrayList<>()).add(new int[]{to, cost}); 
        }

        int ans = (int) dfs(src, k+1, adj, memo);  

        return ans == Integer.MAX_VALUE ? -1 : ans; 
    }

    private long dfs(int node, int k, Map<Integer, List<int[]>> adj, int[][] memo) {
        if (k < 0) return INF; 
        if (memo[node][k] != -1) return memo[node][k];  
        if (node == dst) return 0; 

        long total = INF; 

        for (int[] neighbor: adj.getOrDefault(node, new ArrayList<>())) {
            int to = neighbor[0], price = neighbor[1];
            total = Math.min(total, price + dfs(to, k-1, adj, memo)); 
        }

        return memo[node][k] = (int) total; 
    }
}

