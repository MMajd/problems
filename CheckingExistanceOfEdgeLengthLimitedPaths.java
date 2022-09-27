/* 
  
 @link https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
  
 @categories (union-find/graph/sorting/arrays/lists)

 **KEY OBSERVATION HERE**
 - Queries array is given in advance and each query is independant of others 
 - What means queries can be carried in any order as we wish 
 - Thus We sort the Queries asc. using limit property of each query, and some how find a way to know each query original index to be able to put it its result in the right index of the answer array 
 - And we sor the Edges asc using distance/weight property 
 - Knowing a data-structure that enable us to link/join/merge vertices will be a vital part of our solution (we know such ds, DSU/UF)
 - We merge vertices with edges smaller than given Q[i][2] limited property and test if we have Q[i][0] and Q[i][1] in the same forest or no

 - If we can then we use the index information to put true in answer array 

 *****************************

 An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.


Example 1:
    Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
    Output: [false,true]
    Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
    For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
    For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.

Example 2:
    Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
    Output: [true,false]
    Exaplanation: The above figure shows the given graph.
 

Constraints:
    2 <= n <= 10^5
    1 <= edgeList.length, queries.length <= 10^5
    edgeList[i].length == 3
    queries[j].length == 3
    0 <= ui, vi, pj, qj <= n - 1
    ui != vi
    pj != qj
    1 <= disi, limitj <= 10^9
    There may be multiple edges between two nodes.

  
  
 */




class Solution {
    private static final class DSU {
        private int[] id; 
        private int[] sz; 
        
        private int sets; 
        
        public DSU(int size) { 
            id = new int[size];
            sz = new int[size]; 
            
            for (int i=0; i<size; i++) { 
                id[i] = i; 
                sz[i] = 1; 
            }
            
            sets = size; 
        }
        
        public int find(int x) {
            int root = x; 
            
            while(root != id[root]) root = id[root]; 

            int next; 
            while(x != root) {
                next = id[x]; 
                id[x] = root; 
                x = next; 
            }

            return root; 
        }
        
        public boolean union(int x, int y) { 
            int xroot = find(x);
            int yroot = find(y);
            
            if (xroot == yroot) return false; 
            
            if (sz[xroot] > sz[yroot]) { 
                sz[xroot] += sz[yroot]; 
                id[yroot] = xroot;
            }
            else { 
                sz[yroot] += sz[xroot]; 
                id[xroot] = yroot;
            }
            
            sets -= 1; 
            
            return true;
        }
    }
    
    public boolean[] distanceLimitedPathsExist(int n, int[][] E, int[][] Q) {
        int qlen = Q.length; 
        int elen = E.length; 
        boolean[] ans = new boolean[qlen];
        
        // after sorting we will lose order, 
        // thus we add query index to the query itself to map it to the right index in the ans array
        int[][] newQ = new int[qlen][4];
        
        for (int i=0; i<qlen; i++) {
            newQ[i][0] = Q[i][0]; 
            newQ[i][1] = Q[i][1]; 
            newQ[i][2] = Q[i][2]; 
            newQ[i][3] = i; 
        }
        
        Arrays.sort(newQ, (int[] a, int[] b) -> Integer.compare(a[2], b[2])); // sort small limits first 
        Arrays.sort(E, (int[] a, int[] b) -> Integer.compare(a[2], b[2])); // sort small edges first 
        
        DSU dsu = new DSU(n);
        
        for (int j=0, i=0; j<qlen; j++) { 
            while (i<elen && E[i][2] < newQ[j][2]) { 
                dsu.union(E[i][0], E[i][1]);
                i += 1; 
            }
            
            if (dsu.find(newQ[j][0]) == dsu.find(newQ[j][1])) { 
                ans[newQ[j][3]] = true; 
            }
        }
        
        return ans; 
    }
}
