/** 
 * @link https://leetcode.com/problems/implement-strstr/
 *
 */ 

class Solution {
    public int strStr(String h, String nd) {
        if (nd.equals("")) return 0; 
        if (nd.length() > h.length()) return -1; 
        
        int m = h.length(); 
        int n = nd.length(); 
        
        for (int i=0; i<=m-n; i++) { 
            int j; 
            for (j=0; j<n; j++) { 
                char a = h.charAt(i+j); 
                char b = nd.charAt(j); 
                if (a != b) break; 
            }
            
            if (j == n) return i; 
        }
        
        return -1; 
    }
}
