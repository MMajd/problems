/**
 
 @link https://leetcode.com/problems/reverse-words-in-a-string-iii/
 @categories (string/arrays)

Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

 

Example 1:
    Input: s = "Let's take LeetCode contest"
    Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:
    Input: s = "God Ding"
    Output: "doG gniD"

Constraints:
    1 <= s.length <= 5 * 10^4
    s contains printable ASCII characters.
    s does not contain any leading or trailing spaces.
    There is at least one word in s.
    All the words in s are separated by a single space.
*/

class Solution {
    public String reverseWords(String s) {
        return reverseWordsInPlace(s.toCharArray());
    }
    
    private String reverseWordsInPlace(char[] a) {
        int i = 0, j = 0;
        int n = a.length;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++;
            while (j < i || j < n && a[j] != ' ') j++; 
            reverse(a, i, j - 1);
        }
        
        return new String(a);
    }
    
    private void reverse(char[] arr, int i, int j) { 
        while (i < j) { 
            swap(arr, i++, j--); 
        }
    }
    
    private void swap(char[] arr, int i, int j) { 
        char temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}
