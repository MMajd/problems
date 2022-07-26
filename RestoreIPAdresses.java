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


/** Backtracking solution 3^N*/
class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        solve(0, s, new ArrayList<>(4)); 
        return ans; 
    }
    
    private void solve(int idx, String s, List<Integer> data) { 
        if (data.size() == 4) { 
            if (idx == s.length()) { 
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<4; i++) {
                    sb.append(data.get(i)); 
                    if (i < 3) sb.append('.'); 
                }
                ans.add(sb.toString());
            }
            return; 
        }

        if (idx == s.length()) return;
        
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

/** N^4 soluton **/
class Solution {
    public List<String> restoreIpAddresses(String s) {
		List<String> ret = new ArrayList<>();
		
		StringBuffer ip = new StringBuffer();
		for(int a = 1 ; a < 4 ; ++ a)
		for(int b = 1 ; b < 4 ; ++ b)
	    for(int c = 1 ; c < 4 ; ++ c)
		for(int d = 1 ; d < 4 ; ++ d) {
			if(a + b + c + d == s.length() ) {
				int n1 = Integer.parseInt(s.substring(0, a));
				int n2 = Integer.parseInt(s.substring(a, a+b));
				int n3 = Integer.parseInt(s.substring(a+b, a+b+c));
				int n4 = Integer.parseInt(s.substring(a+b+c));
				if(n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
					ip.append(n1)
                        .append('.')
                        .append(n2)
						.append('.')
                        .append(n3)
                        .append('.')
                        .append(n4);

					if(ip.length() == s.length() + 3) 
                        ret.add(ip.toString());

					ip.delete(0, ip.length());
				}
			}
		}
		return ret;
    }
}
