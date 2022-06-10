/** 
 *
 * Sliding window solution 
 *
 * @link https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 */ 

public class LongestSubStringWithNonRepeatingCharacters { 
    /** 
     * return the length of longest substring w/ non
     * repeating characters 
     * ex: abceabcabc -> solution len(abce) = 4 
     */


    public int solution(String s) { 
        int maxlen = 0; 
        int j = 0; 

        Map<Character, Integer> map = new HashMap<>(); 

        for (int i=0; i<s.length(); i++) { 
            if (map.containsKey(s.charAt(i)) { 
                j = Math.max(j, map.get(s.charAt(i)));
            }

            maxlen = Math.max(maxlen, i-j+1); 
            map.put(s.charAt(i), i+1); 
        }


        return maxlen; 
    }
}
