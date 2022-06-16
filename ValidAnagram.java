
/** 
 *
 * @link leetcode.com/problems/valid-anagram
 *
 */ 

class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() == 0) return false; 
        if (t.length() == 0) return false; 
        
        int[] chars = new int[26];
        int size = 0; 
        
        for (int i=0; i<s.length(); i++) { 
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) { 
                chars[c-'a'] += 1; 
                size += 1; 
            }
        }
        
        for (int i=0; i<t.length(); i++) { 
            char c = t.charAt(i);
            if (Character.isAlphabetic(c)) { 
                chars[c-'a'] -= 1; 
                size -= 1; 
            }
        }
        
        if (size != 0) return false;
        
        for (int i=0; i<26; i++) { 
            if (chars[i] != 0) return false;
        }
        
        return true;
    }
    
    public boolean isAnagram1(String s, String t) {
        s = prepare(s); 
        t = prepare(t); 
        
        return s.equals(t);
    }

    
    private String prepare(String s) { 
        return s.chars()
                .filter(c -> Character.isAlphabetic(c))
                .sorted()
                .collect(
                    StringBuilder::new, 
                    StringBuilder::appendCodePoint, 
                    StringBuilder::append // needed for collect 
                ).toString();
    }

}
