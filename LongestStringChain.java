
/**
 *
 * @link leetcode.com/problems/longest-string-chain
 *
 */

class LongestStringChain {
    public int longestStrChain(String[] words) {
        if (words.length <= 1) return 1; 
        
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int len = words.length; 
        
        int max = 0; 
        int[] dp = new int[len];
        
        Arrays.fill(dp, 1);
        
        for (int i=1; i<len; i++) { 
            for (int j=0; j<i; j++) {
                if (isPredecessor(words[j], words[i])) { 
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                } 
            }
            if (dp[i] > max) max = dp[i];
        }
        
        return max; 
    }

    private boolean isPredecessor(String wordA,String wordB) { 
        if (wordA.length() != wordB.length()-1) return false; 
        
        int lenA = wordA.length(); 
        int lenB = wordB.length(); 
        int i=0, j=0; 
        
        while(i < lenB) { 
            if (j == wordA.length()) return true; 
            if (wordA.charAt(j) == wordB.charAt(i)) { 
                i+= 1; 
                j+= 1; 
            } else { 
                i+= 1; 
            } 
        }
        
        return j == lenA; 
    }
}
