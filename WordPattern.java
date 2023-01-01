/*
 @link https://leetcode.com/problems/word-pattern
 @categories (hash-table/mapping)

 Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

Example 1:
    Input: pattern = "abba", s = "dog cat cat dog"
    Output: true

Example 2:
    Input: pattern = "abba", s = "dog cat cat fish"
    Output: false

Example 3:
    Input: pattern = "aaaa", s = "dog cat cat dog"
    Output: false

Constraints:
    1 <= pattern.length <= 300
    pattern contains only lower-case English letters.
    1 <= s.length <= 3000
    s contains only lowercase English letters and spaces ' '.
    s does not contain any leading or trailing spaces.
    All the words in s are separated by a single space.

*/


/** 
 * There's another solution below
 */ 
class Solution {
    public boolean wordPattern(String P, String s) {
        String[] arr = s.split(" "); 
        Map<Character, String> map = new HashMap<>(); 
        if (arr.length != P.length()) return false; 

        for (int i=0; i<P.length(); i++) {
            char c = P.charAt(i);
            boolean keyExist = map.containsKey(c);
            if (map.containsValue(arr[i]) && !keyExist) return false; 
            if (keyExist && !map.get(c).equals(arr[i])) return false; 
            map.put(c, arr[i]);
        }

        return true; 
    }
}

class Solution {
    public boolean wordPattern(String P, String s) {
        Map<Character, List<Integer>> cmap = new HashMap<>(); 
        Map<String, List<Integer>> smap = new HashMap<>(); 
        String[] slist = s.split(" "); 

        if (slist.length != P.length()) return false; 

        for (int i=0; i<P.length(); i++) {
            cmap.computeIfAbsent(P.charAt(i), k -> new ArrayList<>()).add(i);
        }

        for (int i=0; i<slist.length; i++) { 
            smap.computeIfAbsent(slist[i], k -> new ArrayList<>()).add(i);
        }

        if (smap.size() != cmap.size()) return false; 

        return new HashSet<>(cmap.values()).equals(new HashSet<>(smap.values())); 
    }
}
