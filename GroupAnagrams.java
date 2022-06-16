/**
 * @link leetcode.com/problems/group-anagrams
 */

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(); 
        
        for (String s : strs) { 
            String sortKey = sort(s); 
            if (!map.containsKey(sortKey)) map.put(sortKey, new ArrayList<>());
            map.get(sortKey).add(s);
        }
        
        return new ArrayList<List<String>>(map.values());
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
