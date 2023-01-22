/*
 @link https://leetcode.com/problems/palindrome-partitioning
 @categories (backtracking)

 Given a string s, partition s such that every substring of the partition is a palindrome. 
Return all possible palindrome partitioning of s.

Example 1:
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]

Example 2:
    Input: s = "a"
    Output: [["a"]]

Constraints:
    1 <= s.length <= 16
    s contains only lowercase English letters.
*/

class Solution {
    String s = null; 
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        this.s = s; 
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int i, List<String> curr) {
        if (i >= s.length()) ans.add(new ArrayList<String>(curr));
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(i, j)) {
                curr.add(s.substring(i, j + 1));
                dfs(j + 1, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
