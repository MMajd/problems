/*
 @link https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 @categories (two-pointers/string)

 Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', 
and they can appear in both lower and upper cases, more than once.

Example 1:
    Input: s = "hello"
    Output: "holle"

Example 2:
    Input: s = "leetcode"
    Output: "leotcede"
 
Constraints:
    1 <= s.length <= 3 * 10^5
    s consist of printable ASCII characters.
*/

class Solution {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        int N = s.length(); 
        int l = 0, r = N-1; 
        char[] str = s.toCharArray();

        while (l < r) {  
            while (l < r && !set.contains(str[l])) l++; 
            while (r > l && !set.contains(str[r])) r--; 
            swap(str, l++, r--); 
        }
        return String.valueOf(str);
    }

    private void swap(char[] a, int i, int j) {
        char t  = a[i]; 
        a[i] = a[j]; 
        a[j] = t; 
    }
}

