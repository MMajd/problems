/*
 @link https://leetcode.com/problems/string-compression
 @categories (two-pointers)

 Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Example 1:
    Input: chars = ["a","a","b","b","c","c","c"]
    Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
    Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:
    Input: chars = ["a"]
    Output: Return 1, and the first character of the input array should be: ["a"]
    Explanation: The only group is "a", which remains uncompressed since it's a single character.

Example 3:
    Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
    Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
    Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

Constraints:
    1 <= chars.length <= 2000
    chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
*/ 
class Solution {
    public int compress(char[] chars) {
        char prev = '\0';
        int index = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != prev) {
                index = appendToChars(chars, index, count, prev);
                prev = c;
                count = 1;
            } else {
                count++;
            }
        }

        return appendToChars(chars, index, count, prev);
    }

    private int appendToChars(char[] chars, int index, int count, char c) {
        if (count == 0) {
            return index;
        }

        chars[index++] = c;
        if (count == 1) {
            return index;
        }

        index += (int) Math.log10(count);
        int newIndex = index + 1;
        while (count > 0) {
            chars[index--] = (char) ('0' + (count % 10));
            count /= 10;
        }

        return newIndex;
    }
}

class Solution {
    public int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        int n = chars.length; 
        int left = 0, right = 0;  

        while (right < n) {
            if (chars[left] != chars[right]) { 
                compress(chars, left, right, sb);
                left = right; 
            }
            right += 1; 
        }

        compress(chars, left, right, sb);

        for (int i=0; i<sb.length(); i++) { 
            chars[i] = sb.charAt(i);
        }

        return sb.length();
    }

    private void compress(char[] chars, int left, int right, StringBuilder sb) {
        int size = right - left; 
        if (size > 1) {
            sb.append(chars[left]);
            sb.append(size); 
        }
        else { 
            sb.append(chars[left]);
        }
    }
}

