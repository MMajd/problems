/**
 @link https://leetcode.com/problems/flip-string-to-monotone-increasing/
 @categories (string/dp)

 A binary string is monotone increasing if it consists of some number of 0's (possibly none), 
followed by some number of 1's (also possibly none).
You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
Return the minimum number of flips to make s monotone increasing.

Example 1:
    Input: s = "00110"
    Output: 1
    Explanation: We flip the last digit to get 00111.

Example 2:
    Input: s = "010110"
    Output: 2
    Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
    Input: s = "00011000"
    Output: 2
    Explanation: We flip to get 00000000.

Constraints:
    1 <= s.length <= 10^5
    s[i] is either '0' or '1'.
 */

/** 
 * DP solution at the end
 *
 * Dynamic Window Solution. 
 * Basically every monotonic string has 2-windows first one is the left one and it contains 0's only 
 * and the second one contains 1's only, and if there're any defects in theses windows our goal is to minimize number of flips to fix them
 * So will will take min number of 1's to be flipped to 0's in the left window + min number of 0'1 to flipped to 1 int right window. 
 * Algorithm: 
 *    - Count number of zeros in the string, m = count_zeros(string)
 *    - Enumerate all possible window sizes for left from 0 up to n;  
 *    for i = 0 to n: 
 *      if str[i] is '0': 
 *          // lastest character added to left window is '0', thus it adds no defects to left window
 *          // and reduces number of defects in the current rigth window, so substract 1 from previous right window defects
 *          m -= 1 // number of zeros in right window decrease, hence number of flipes decrease by 1
 *          ans = min(ans, m) // choose the best answer we have got till now
 *      else: // left window has a new defect so add it total number of defects
 *          m += 1
 *    return ans
 */
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length(); 
        int m = 0; 
        int ans = 0; 

        for (int i=0; i<n; i++) { 
            if (s.charAt(i) == '0') { 
                m += 1; 
            }
        }

        ans = m; 

        for (int i=0; i<n; i++) { 
            if (s.charAt(i) == '0') { 
                ans = Math.min(ans, --m);
            } else { 
                m += 1; 
            }
        }

        return ans; 
        
    }
}


class Solution {
    public int minFlipsMonoIncr(String s) {
        int ans = 0; 
        int ones = 0; 

        for (char bit : s.toCharArray()) { 
            if (bit == '0') {
                ans = Math.min(ans + 1, ones);
            }
            else { 
                ones += 1; 
            }
        }

        return ans; 
    }
}
