/*
 @link https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
 @categories(string/arrays/dsu/union-find)

 You are given two strings of the same length s1 and s2 and a string baseStr.
We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

Example 1:
    Input: s1 = "parker", s2 = "morris", baseStr = "parser"
    Output: "makkek"
    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
    The characters in each group are equivalent and sorted in lexicographical order.
    So the answer is "makkek".

Example 2:
    Input: s1 = "hello", s2 = "world", baseStr = "hold"
    Output: "hdld"
    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
    So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".

Example 3:
    Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
    Output: "aauaaaaada"
    Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".

Constraints:
    1 <= s1.length, s2.length, baseStr <= 1000
    s1.length == s2.length
    s1, s2, and baseStr consist of lowercase English letters.
*/

class Solution {
    /** This problem needs equivlence classes data structure 
     * so use union find (Disjoint Sets)
     */

    private static final class DSU {
        private int [] chars; 

        public DSU() {
            chars = new int[26];
            Arrays.setAll(chars, i -> i);
        }

        public int find(int u) { 
            if (u != chars[u]) return find (chars[u]); 
            return chars[u]; 
        }

        public void union(int u, int v) { 
            int pu = find(u); 
            int pv = find(v); 

            if (pu > pv) {
                chars[pu] = pv; 
            } else { 
                chars[pv] = pu; 
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length(); 
        int m = baseStr.length();
        DSU dsu = new DSU();
        StringBuilder sb = new StringBuilder(n);
        

        for (int i=0; i<n; i++) { 
            int u = s1.charAt(i) - 'a';
            int v = s2.charAt(i) - 'a'; 
            dsu.union(u, v);
        }

        for (int i=0; i<m; i++) {
            int u = dsu.find(baseStr.charAt(i) - 'a');
            sb.append((char)(u + 'a'));
        }

        return sb.toString();
    }
}
