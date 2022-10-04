/*  
 @link https://leetcode.com/problems/sort-characters-by-frequency/submissions/
 @categories (sorting/map/counting/hash-table/priority-queue/buket-sort)

 Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.


Example 1:
    Input: s = "tree"
    Output: "eert"
    Explanation: 'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
    Input: s = "cccaaa"
    Output: "aaaccc"
    Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
    Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
    Input: s = "Aabb"
    Output: "bbAa"
    Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.

Constraints:
    1 <= s.length <= 5 * 105
    s consists of uppercase and lowercase English letters and digits.
  
 */ 




class Solution {
    private static final class CharEntry {
        int freq; 
        char ch; 
        
        public CharEntry(char c, int f) {
            ch = c; 
            freq = f; 
        }
    }
    
    public String frequencySort(String s) {
        int n = s.length(); 
        
        CharEntry[] charset = new CharEntry[80];
        
        Arrays.setAll(charset, i -> new CharEntry((char)(i+48), 0));

        for (int i=0; i<n; i++) { 
            charset[s.charAt(i)-'0'].freq += 1; 
        }
        
        Arrays.sort(charset, (a, b) -> {
            return Integer.compare(b.freq, a.freq);
        });
        
        StringBuilder sb = new StringBuilder();
        
        for (CharEntry e : charset) { 
            if (e.freq == 0) break;
            for (int i=0; i<e.freq; i++) sb.append(e.ch);
        }
        
        return sb.toString();
    }
    
    public String frequencySort1(String s) {
        int[] charset = new int[250];
        
        for (int i=0; i<s.length(); i++) charset[s.charAt(i)-'0'] += 1; 
        
        TreeMap<Integer, List<Character>> set = new TreeMap<>(); 
        
        for (int i=0; i<charset.length; i++) { 
            if (charset[i] != 0) 
                set.computeIfAbsent(charset[i], (k)-> new ArrayList<>()).add((char)(i+'0'));
        }
        
        StringBuilder sb = new StringBuilder(set.size());
        
        for (int freq : set.descendingKeySet()) {
            List<Character> clist = set.get(freq);
            
            for (Character c : clist) for (int i=0; i<freq; i++) sb.append(c);
        }
        
        return sb.toString();
    }
}
