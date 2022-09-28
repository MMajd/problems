/*
  @link https://leetcode.com/problems/longest-consecutive-sequence
  @categories(union-find/maps)
  
  Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9
 
Constraints:
    0 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
 */


class Solution {
     public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n < 2) return n; 
        
        int ans = 1; 
        Set<Integer> set = new HashSet<>(); 
        
        for (int i : nums) set.add(i);
        
        for (int key : set) { 
            if (!set.contains(key-1)) { 
                int currmax = 1; 
                int currkey = key + 1; 
                
                while (set.contains(currkey)) { 
                    currmax += 1; 
                    currkey += 1; 
                }
                
                ans = Math.max(ans, currmax);
            }
        }
    
        return ans; 
    }
}



class Solution {
    private static final class DSU {
        private int[] parent;
        private int[] sz; 
        private int sets; 
        private int maxSetSize; 

        private DSU(int size) {
            parent = new int[size];
            sz = new int[size];

            Arrays.setAll(parent, i-> {
                sz[i] = 1; 
                return i; 
            });

            maxSetSize = 1; 
            sets = size; 
        }
 
        public int setsCount() {
            return sets; 
        }
        
        public int maxSetSize() { 
            return maxSetSize; 
        }
        
        // recursive find with path compression
        private int __find(int u) { 
            if (u != parent[u]) parent[u] = __find(parent[u]); 
            return parent[u]; 
        }

        public int parent(int u) { 
            return find(u); 
        }

        public int find(int u) {
            int root = u, next; 

            while(root != parent[root]) root = parent[root]; 

            while(u != root) {
                next = parent[u]; 
                parent[u] = root; 
                u = next; 
            }

            return root;            
        }

        public boolean connected(int u, int v) { 
            return find(u) == find(v);
        }

        public boolean union(int u, int v) {
            int up = find(u);
            int vp = find(v);

            if (up == vp) return false; 
            
            maxSetSize = Math.max(maxSetSize, sz[up]+sz[vp]);
            
            if (sz[up] >= sz[vp]) {
                parent[vp] = up; 
                sz[up] += sz[vp]; 
            } 
            else { 
                parent[up] = vp; 
                sz[vp] += sz[up]; 
            }
            
            sets -= 1; 
            
            return true; 
        }
    }

    
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        
        if (n < 2) return n; 
        
        DSU dsu = new DSU(n);
        Map<Integer, Integer> map= new HashMap<>();
        
        for (int i=0; i<n; i++) { 
            int key = nums[i]; 
            
            /* num has been processed, thus continue */
            if (map.containsKey(key)) continue; 
            
            /* num has not been processed before */
            if (map.containsKey(key-1)) dsu.union(i, map.get(key-1));
            if (map.containsKey(key+1)) dsu.union(i, map.get(key+1));
            map.put(key, i);
        }
    
        return dsu.maxSetSize(); 
    }
}
