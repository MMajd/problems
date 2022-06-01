/**
 * Valid Palindrome 
 * @link: https://leetcode.com/problems/valid-palindrome/submissions/
 * */

public class ValidPalindrome { 
    private class Solution {
	public boolean isPalindrome(String s) {
		int start = 0, end = s.length() -1;
		s = s.toLowerCase(); 
		
		while (end>=start) { 
		    char startChar = s.charAt(start); 
		    char endChar = s.charAt(end);  
		    
		    if (!Character.isLetterOrDigit(startChar)) {
			start += 1;
			continue;
		    };
		    if (!Character.isLetterOrDigit(endChar)) { 
			end -= 1; 
			continue;
		    };
		   
		    if (startChar != endChar) return false; 
		    
		    start += 1; 
		    end -= 1; 
		}
		
		return true;
	    }
	}

    private class Solution2 { 
	public boolean isPalindrome(String s) {
		s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); 
		
		if (s.length() == 0) return true; 
		
		int start = 0, end = s.length()-1, mid = s.length() / 2; 
		
		
		while (end >= mid && start <= mid)  { 
		    if (s.charAt(start) != s.charAt(end)) return false; 
		    start += 1;
		    end -= 1; 
		}
		
		return true;
	    }

    }
}

