/*
 @link https://leetcode.com/problems/satisfiability-of-equality-equations/
 @categories (union-find/graph/string/arrays)

 You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.


Example 1:
    Input: equations = ["a==b","b!=a"]
    Output: false
    Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
    There is no way to assign the variables to satisfy both equations.

Example 2:
    Input: equations = ["b==a","a==b"]
    Output: true
    Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 

Constraints:
    1 <= equations.length <= 500
    equations[i].length == 4
    equations[i][0] is a lowercase letter.
    equations[i][1] is either '=' or '!'.
    equations[i][2] is '='.
    equations[i][3] is a lowercase letter.
*/



class Solution {
    private static final class UnionFind { 
        int [] parent; 
        
        public UnionFind() { 
            parent = new int[26]; 
            for (int i=0; i<26; i++) parent[i] = i;
        }
        
        public int find(char x) {
            int xcode = x - 'a'; 
            int temp = xcode; 
            
            while (temp != parent[temp]) { 
                temp = parent[temp]; 
            }

            while (xcode != temp) { 
                int next = parent[xcode]; 
                parent[xcode] = temp; 
                xcode = next; 
            }
            
            return xcode; 
        }
        
        public void union(char x, char y) { 
            int xx = find(x);
            int yy = find(y);
            
            if (xx != yy) { 
                parent[xx] = yy; 
            }
        }
    }
    
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(); 
        
        for (String e : equations) { 
            if (e.charAt(1) == '=') { 
                uf.union(e.charAt(0), e.charAt(3));
            }
        }
        
        for (String e: equations) { 
            if (e.charAt(1) == '!' && uf.find(e.charAt(0)) == uf.find(e.charAt(3))) { 
                return false; 
            }
        }
        
        return true;
    }
}
