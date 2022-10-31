/*
 @link https://leetcode.com/problems/string-compression-ii/
 @categories (dp/backtracking[similiar approach])

 Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.

Example 1:
    Input: s = "aaabcccd", k = 2
    Output: 4
    Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.

Example 2:
    Input: s = "aabbaa", k = 2
    Output: 2
    Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

Example 3:
    Input: s = "aaaaaaaaaaa", k = 0
    Output: 3
    Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 

Constraints:
    1 <= s.length <= 100
    0 <= k <= s.length
    s contains only lowercase English letters.
*/

class Solution {

    private int xs(int x) { 
        return x < 2 ? 0 : x < 10 ? 1 : x < 100 ? 2 : 3; 
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        // dp[i][k]: the minimum length for s[:i] with at most k deletion.
        int n = s.length();
        int[][] dp = new int[n+1][k+1];

        Arrays.setAll(dp, i -> { 
            Arrays.fill(dp[i], 1<<10);
            return dp[i]; 
        });

        dp[0][0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= k; j++) {                
                int cnt = 0, del = 0;

                // we have two choices, either to delete s[i] or to keep it 
                if (j != 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }

                // keep s[i], here we have some kind of greedy approach, 
                // we try to keep the current char and delete all different chars
                // basically, concat the same, remove the different
                for(int p = i; p >= 1; p--) { 
                    if (s.charAt(p - 1) == s.charAt(i - 1)) cnt++;
                    else del++;

                    if (del - j > 0) break;

                    dp[i][j] = Math.min(dp[i][j], dp[p-1][j-del] + 1 + xs(cnt));
                }
            }
        }

        return dp[n][k];
    }
}


class Solution1 {
    static final int N = 127;

    // dp[left][k] means the minimal coding size for substring 
    // s[left:] and removing at most k chars
    int[][] dp = new int[N][N]; 

    String str;
    int n;

    // get length of digit
    private int xs(int x) { return x == 1 ? 0 : x < 10 ? 1 : x < 100 ? 2 : 3; }

    private int solve(int left, int k) {
        if(k < 0) return N;  // invalid, return INF
        if(left >= n || n - left <= k) return 0;  // empty

        int res = dp[left][k];
        if(res != -1) return res;
        res = N;

        int[] cnt = new int[26];
        // we try to make s[left:j] (both inculded) as one group,
        // and all chars in this group should be the same.
        // so we must keep the most chars in this range and remove others
        // the range length is (j - left + 1)
        // and the number of chars we need to remove is (j - left + 1 - most)
        
        for(int j = left, most = 0; j < n; j++) {
            most = Math.max(most, ++cnt[str.charAt(j) - 'a']);  // most = max(count(s[left:j])
            res = Math.min(res, 1 + xs(most) + solve(j + 1, k - (j - left + 1 - most)));
        }
        
        return res;
    }
    
    public int getLengthOfOptimalCompression(String s, int k) {
        for (int i=0; i<N; i++) { 
            for (int j=0; j<N; j++) { 
                dp[i][j] = -1; 
            }
        }
        
        str = s;
        n = s.length();
        
        return solve(0, k);
    } 
}
