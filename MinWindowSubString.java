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
        int j = 0, minLen = Integer.MAX_VALUE; 
        Map<Character, Integer> smap = new HashMap<>(); 
        Map<Character, Integer> tmap = new HashMap<>(); 
        
        for (int i=0; i<t.length(); i++) { 
            char c = t.charAt(i); 
            tmap.put(c, 1+tmap.getOrDefault(c, 0));
        }
        
        int available = 0;  
        int required = tmap.size();
        
        for (int i=0; i<s.length(); i++)  { 
            char c = s.charAt(i);
            
            smap.put(c, 1+smap.getOrDefault(c, 0));
            
            if (tmap.containsKey(c) 
                && smap.get(c).intValue() == tmap.get(c).intValue()) available += 1; 
            
            while (available == required) { 
                int size = i-j+1; 
                
                if (size < minLen) { 
                    start = j; 
                    end = i; 
                    minLen = size; 
                }
                
                char mostLeft = s.charAt(j++);
                smap.put(mostLeft, smap.get(mostLeft)-1);
                
                if (tmap.containsKey(mostLeft) 
                    && smap.get(mostLeft).intValue() < tmap.get(mostLeft).intValue()) { 
                    available -= 1; 
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE? "" : s.substring(start, end+1);
    }
}
