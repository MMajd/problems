/** 
 * @link https://leetcode.com/problems/remove-palindromic-subsequences
 *
 * NOTE: The string consist only of two characters (ex: a, b) 
 *
 * Subsequence doesn't mean contingous, 
 * thus there're two solutions, first one string is a palindrome and it takes us 1 step to remove it 
 * second one string is not palindrome, here we take one step to remove all occurences of the first character 
 * and another step to the remaining palindrome (one character string) 
 *
 *
 */

class RemovePalindromicSubsequences {
    public int removePalindromeSub(String s) {
        return isPalindrome(s) ?  1 : 2; 
    }
    
    private boolean isPalindrome(String s) { 
        int start=0, end=s.length()-1;

        while(start < end) { 
            if (s.charAt(start++) != s.charAt(end--)) 
                return false;
        }
        
        return true;
    }
}
