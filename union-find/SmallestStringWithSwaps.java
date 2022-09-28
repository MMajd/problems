/*
 @link https://leetcode.com/problems/smallest-string-with-swaps/submissions/  
 @categories (union-find/hash-table/priority-queue/sorting/bfs/dfs)

 You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.


Example 1:
    Input: s = "dcab", pairs = [[0,3],[1,2]]
    Output: "bacd"
    Explaination: 
    Swap s[0] and s[3], s = "bcad"
    Swap s[1] and s[2], s = "bacd"

Example 2:
    Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    Output: "abcd"
    Explaination: 
    Swap s[0] and s[3], s = "bcad"
    Swap s[0] and s[2], s = "acbd"
    Swap s[1] and s[2], s = "abcd"

Example 3:
    Input: s = "cba", pairs = [[0,1],[1,2]]
    Output: "abc"
    Explaination: 
    Swap s[0] and s[1], s = "bca"
    Swap s[1] and s[2], s = "bac"
    Swap s[0] and s[1], s = "abc"

Constraints:
    1 <= s.length <= 10^5
    0 <= pairs.length <= 10^5
    0 <= pairs[i][0], pairs[i][1] < s.length
    s only contains lower case English letters.
 */ 


class Solution {
	private class DSU {
		private int[] parent;
        private int[] sz; 
        private int sets; 

		private DSU(int size) {
			parent = new int[size];
			sz = new int[size];
            
            Arrays.setAll(parent, i-> {
                sz[i] = 1; 
                return i; 
            });
            
            sets = size; 
		}
        
        public boolean connected(int x, int y) { 
            return find(x) == find(y);
        }
       
        // recursive find
        public int parentRecursive(int x) { 
            if (x != parent[x]) { 
                parent[x]= find(parent[x]); 
                return parent[x]; 
            }
            return x; 
        }
        
        public int parent(int x) { 
            return find(x); 
        }
        
		public int find(int x) {
            int root = x, next; 
            
            while(root != parent[root]) root = parent[root]; 
            
            while(x != root) {
                next = parent[x]; 
                parent[x] = root; 
                x = next; 
            }
            
            return root;            
		}

		public boolean union(int x, int y) {
			int xroot = find(x);
			int yroot = find(y);
            
            if (xroot == yroot) return false; 

            if (sz[xroot] >= sz[yroot]) {
                parent[yroot] = xroot; 
                sz[xroot] += sz[yroot]; 
            } 
            else { 
                parent[xroot] = yroot; 
                sz[yroot] += sz[xroot]; 
            }
            
            sets -= 1; 
            return true; 
		}

	}

    
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
		char[] str = s.toCharArray();
        
        PriorityQueue<Character>[] swapsMap = (PriorityQueue<Character>[]) new PriorityQueue[n]; 
		DSU dsu = new DSU(n);

		for (List<Integer> pair : pairs) {
			dsu.union(pair.get(0), pair.get(1));
		}

		for (int i=0; i<n; i++) {
            int p = dsu.parent(i);
            
            if (swapsMap[p] == null) { 
                swapsMap[p] = new PriorityQueue<>();
            }
            
            swapsMap[p].add(str[i]);
		}

		for (int i=0; i<n; i++) {
			int p = dsu.parent(i);
			str[i] = swapsMap[p].poll();
		}
        
		return new String(str);
	}
}
