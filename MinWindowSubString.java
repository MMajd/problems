/** 
 *
 * @link https://leetcode.com/problems/minimum-window-substring/
 *
 *
 * Sliding Window
 *
 */

class MinWindowSubString {
    public String minWindow(String s, String t) {
        if (t.length() == 0) return ""; 
        
        int start = -1, end = -1; 
        int j = 0, maxLen = Integer.MAX_VALUE; 
        Map<Character, Integer> smap = new HashMap<>(); 
        Map<Character, Integer> tmap = new HashMap<>(); 
        
        for (int i=0; i<t.length(); i++) { 
            char c = t.charAt(i); 
            tmap.put(c, 1+tmap.getOrDefault(c, 0));
        }
        
        int have = 0;  
        int need = tmap.size();
        
        for (int i=0; i<s.length(); i++)  { 
            char c = s.charAt(i);
            
            smap.put(c, 1+smap.getOrDefault(c, 0));
            
            if (tmap.containsKey(c) 
                && smap.get(c).intValue() == tmap.get(c).intValue()) have += 1; 
            
            while (have == need) { 
                int size = i-j+1; 
                
                if (size < maxLen) { 
                    start = j; 
                    end = i; 
                    maxLen = size; 
                }
                
                char mostLeft = s.charAt(j++);
                smap.put(mostLeft, smap.get(mostLeft)-1);
                
                if (tmap.containsKey(mostLeft) 
                    && smap.get(mostLeft).intValue() < tmap.get(mostLeft).intValue()) { 
                    have -= 1; 
                }
            }
        }
        
        return maxLen == Integer.MAX_VALUE? "" : s.substring(start, end+1);
    }
}
