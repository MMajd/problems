/**
  @link leetcode.com/problems/group-anagrams

  Given an array of strings strs, group the anagrams together. You can return the answer in any order.

  An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
    Input: strs = [""]
    Output: [[""]]

Example 3:
    Input: strs = ["a"]
    Output: [["a"]]
 
Constraints:
    1 <= strs.length <= 10^4
    0 <= strs[i].length <= 100
    strs[i] consists of lowercase English letters.
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(); 
        
        for (int i=0; i<strs.length; i++) { 
            char[] s = strs[i].toCharArray();
            Arrays.sort(s);
            
            String k = String.valueOf(s); 
            
            if (!map.containsKey(k)) {
                map.put(k, new ArrayList<>());
            } 
            
            map.get(k).add(strs[i]);
        }
        
        return new LinkedList<>(map.values());
    }
    
    public String sort(String s) { 
        return s.chars()
                .sorted()
                .collect(StringBuilder::new, 
                         StringBuilder::appendCodePoint, 
                         StringBuilder::append)
                .toString();
    }
}
