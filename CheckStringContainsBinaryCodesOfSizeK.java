
/**
 * Make a set to only record unique substrings 
 * if you reach the specified size which is 1<<k = 2^k 
 * return true, if you get out of the loop return false 
 *
 * @link: https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/ 
 * */

class CheckStringContainsBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        int total = 1 << k;
        
        for (int i=0; i+k<=s.length(); i++) {
	    /**
	     * Ex: S=00110110, K=2, Total = 4, 
	     * Substrings: 00 | 01 | 11 | 10 | 01 | 11 | 10
	     * Set: 00 01 11 10 
	     * Set size == 4 
	     *
	     * Ex: S=0110, K=2, Total: 4
	     * Substrings: 01 | 11 | 10 
	     * Set: 01 11 10
	     * Set size != 4  
	     * */
            set.add(s.substring(i, i+k));
            if (set.size() == total) return true;
        }
        
        return false;
    }
}
