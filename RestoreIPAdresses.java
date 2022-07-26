/* 
 
 @link https://leetcode.com/problems/restore-ip-addresses/
  
 A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 
Example 1:
    Input: s = "25525511135"
    Output: ["255.255.11.135","255.255.111.35"]

Example 2:
    Input: s = "0000"
    Output: ["0.0.0.0"]

Example 3:
    Input: s = "101023"
    Output: ["1.0.10.23","1.0.102.3","10.1.0.23",
            "10.10.2.3","101.0.2.3"]
 
Constraints:
    1 <= s.length <= 20
    s consists of digits only.
*/


/** Backtracking solution */

class Solution {
    List<String> ans = new ArrayList<>();
    
    public List<String> restoreIpAddresses(String s) {
        solve(0, s, new ArrayList<>(4)); 
        return ans; 
    }
    
    private void solve(int idx, String s, List<Integer> data) { 
        if (idx == s.length()) { 
            if (data.size() == 4) { 
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<4; i++) {
                    sb.append(data.get(i)); 
                    if (i < 3) { 
                        sb.append('.'); 
                    }
                }
                ans.add(sb.toString());
            }
            return; 
        }
        
        Integer sint = null; 
        
        for (int i=0; i<3; i++) { 
            if (sint != null && sint == 0
               || i+idx >= s.length()) return;
            
            sint = sint == null 
                ? s.charAt(i+idx) - '0'
                : sint * 10 + s.charAt(i+idx) - '0';
            
            if (sint >= 0 && sint <= 255) { 
                data.add(sint);
                solve(i+idx+1, s, data); 
                data.remove(data.size()-1);
            }
        }
    }
}
