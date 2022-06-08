/**
 * Use the algo for find palindromes and delete them, 
 * after that return the new version of the string; 
 *
 */ 


class RemovePalindromesFromString {

    public int removePalindromesFromString (String s) {

        int ans = 0; 
        String palindrome = ""; 
        
        while(!(palindrome  = getLongestPalindrome(s)).equals("")) { 
            s = s.replaceAll(palindrome, ""); 
            System.out.println(s.length());
            ans += 1; 
        }; 
        
        return s; 
    }
    
    
    private String getLongestPalindrome(String origin) { 
        if (origin.equals("")) 
            return ""; 
        
        int strlen = origin.length(); 
        int dp[] = new int [strlen]; 
        String reverse = new StringBuffer(origin).reverse().toString(); 
        int maxLen = 0; 
        int maxEnd = 0; 
        
        for (int i=0; i<strlen; i++) { 
            for (int j=strlen-1; j>=0; j--) { 
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[j] = 1; 
                    }
                    else { 
                        dp[j] = dp[j - 1] + 1; 
                    }
                } else {
                    dp[j] = 0;
                }
                
                if (dp[j] > maxLen) { 
                    int startReverse = strlen-(j+1); 
                    int startIndex = i-(dp[j]-1);
                    
                    if (startIndex == startReverse) { 
                        maxLen = dp[j]; 
                        maxEnd = i; 
                    }
                }
            } 
        }
        
        return origin.substring(maxEnd - (maxLen -1), maxEnd + 1); 
    }
}
