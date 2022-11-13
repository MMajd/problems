/** 
 @link https://leetcode.com/problems/reverse-words-in-a-string/
 @categories (string/two-pointers/arrays) 

 Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. 
The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

 Note that s may contain leading or trailing spaces or multiple spaces between two words. 
The returned string should only have a single space separating the words. 
Do not include any extra spaces.

Example 1:
    Input: s = "the sky is blue"
    Output: "blue is sky the"

Example 2: 
    Input: s = "      the sky        is blue      "
    Output: "blue is sky the"
 */ 

class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        reverse(arr, 0, arr.length-1);
        reverseWords(arr);
        return cleanSpaces(arr);
    }
    
    void reverseWords(char[] a) {
        int i = 0, j = 0;
        int n = a.length;

        while (i < n) {
          while (i < j || i < n && a[i] == ' ') i++;
          while (j < i || j < n && a[j] != ' ') j++; 
          reverse(a, i, j - 1);
        }
    }

    String cleanSpaces(char[] a) {
        int i = 0, j = 0;
        int n = a.length;

        while (j < n) {
          while (j < n && a[j] == ' ') j++;
          while (j < n && a[j] != ' ') a[i++] = a[j++]; 
          while (j < n && a[j] == ' ') j++;
            
          if (j < n) a[i++] = ' ';
        }

        return new String(a).substring(0, i);
    }

    private void reverse(char[] a, int i, int j) {
        while (i < j) swap(a, i++, j--);
    }

    private void swap(char[] arr, int i, int j) { 
        char temp = arr[j]; 
        arr[j] = arr[i]; 
        arr[i] = temp; 
    }
}
