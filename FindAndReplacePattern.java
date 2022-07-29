


class Solution {
    public List<String> findAndReplacePattern(String[] words, 
            String pattern) {
        List<String> ans = new ArrayList<>();
		int pweight = getWeight(pattern); 
		
        for (String word : words) { 
            if (getWeight(word) == pweight) { 
                ans.add(word);
            }
        }
        
        return ans; 
    }
    
    private int getWeight(String word) { 
        int[] chars = new int[26]; 
        int weight = 0; 
        for (int i=0, j=0; i<word.length(); i++) { 
            char c = word.charAt(i); 
            int idx = (int)(c-'a');

            if (chars[idx] == 0) { 
                j += 1; 
                chars[idx] = j*(i+1); 
                weight += chars[idx]; 
            }  
            else { 
                weight += chars[idx]; 
            }
        }   
        return weight; 
    }
}

