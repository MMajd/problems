/*
 @link https://leetcode.com/problems/orderly-queue/
 @categories (math/sorting/string) 

You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..

Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.

 

Example 1:
    Input: s = "cba", k = 1
    Output: "acb"
    Explanation: 
    In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
    In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".

Example 2:
    Input: s = "baaca", k = 3
    Output: "aaabc"
    Explanation: 
    In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
    In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
 

Constraints:
    1 <= k <= s.length <= 1000
    s consist of lowercase English letters.


******* NOTE: **********************
- If we have more than one character to move with any number of movements, then we can sort the string ans return the answer directly 

- If we have exactly one character to move, then we used a for loop to order the string accordingly  

*/

class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) { 
            int M = s.length();
            String ans = s; 

            for (int i=0; i<M; i++) { 
                s = s.substring(1) + s.substring(0, 1);
                if (ans.compareTo(s) > 0) ans = s; 
            }

            return ans; 
        }

        char[] charset = s.toCharArray(); 
        Arrays.sort(charset);
        return String.valueOf(charset);
    }
}
