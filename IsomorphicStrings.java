/*
  

 Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.


Example 1:
    Input: s = "egg", t = "add"
    Output: true

Example 2:
    Input: s = "foo", t = "bar"
    Output: false

Example 3:
    Input: s = "paper", t = "title"
    Output: true
 
Constraints:
    1 <= s.length <= 5 * 10^4
    t.length == s.length
    s and t consist of any valid ascii character. character [256]
*/



class Solution {
    /** Final Solution */
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        
        for (int i=0; i<s.length(); i++) { 
            int c1 = (int)s.charAt(i);
            int c2 = (int)t.charAt(i);
            
            if (m1[c1] != m2[c2]) return false; 
                
            m1[c1] = m2[c2] = i+1;
        }
        
        return true; 
    }
    
    /** First Solution */
    public boolean isIsomorphic1(String s, String t) {
        int n = s.length();
        int[] h1 = mapify(s, n);
        int[] h2 = mapify(t, n);
        
        for (int i=0; i<n; i++) { 
            if (h1[i] != h2[i]) return false; 
        }
        
        return true; 
    }
    
    private int[] mapify(String s, int n) {
        int[] h = new int[n]; 
        int[] m = new int[256];


        int count = 1; 

        for (int i=0; i<n; i++) { 
            int c = (int)s.charAt(i);

            if (m[c] != 0) {
                h[i] = m[c];  
            } 
            else {
                m[c] = count; 
                h[i] = count++; 
            }
        }
        
        return h; 
    }
}
