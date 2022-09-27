/*
 @link https://leetcode.com/problems/push-dominoes/
 categories (two-pointers/simluation)

 There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.


Example 1:
    Input: dominoes = "RR.L"
    Output: "RR.L"
    Explanation: The first domino expends no additional force on the second domino.

Example 2:
    Input: dominoes = ".L.R...LR..L.."
    Output: "LL.RR.LLRRLL.."

Constraints:
    n == dominoes.length
    1 <= n <= 105
    dominoes[i] is either 'L', 'R', or '.'.


*/

/** 
 * My explanation link 
 * @link https://leetcode.com/problems/push-dominoes/discuss/2631209/Java-Intuitive-One-Path-Solution-Similar-to-Two-Pointers
 *
 */ 
class Solution {
    public String pushDominoes(String s) {
        int n = s.length() + 2; 
        char[] A = new char[n]; 
        
        A[0] = 'L'; 
        A[n-1] = 'R';
        
        for (int i=1; i<n-1; i++) { 
            A[i] = s.charAt(i-1);
        }
        
        int i=0, j=0; 

        while (i < n) { 
            while (i < n && A[i] == '.') i++; 
            
            if (A[j] == 'R' && A[i] == 'L') { 
                int end = i; 
                while (++j < --end) {
                    A[end] = 'L'; 
                    A[j] = 'R'; 
                }
            }
            else if (A[j] == 'R' && A[i] == 'R' || 
					    A[j] == 'L' && A[i] == 'L') { 
                while(++j <= i) A[j]  = A[j-1]; 
            }
            
            j = i; 
            i += 1; 
        }
        
        return new String(Arrays.copyOfRange(A, 1, n-1));
    }
}


class Solution {
    public String pushDominoes(String D) {
        int n = D.length();
        char[] A = D.toCharArray();
        int[] forces = new int[n];
        
        int f = 0; 
        
        // System.out.println(Arrays.toString(A));
        
        for (int i=0; i<n; i++) { 
            if (A[i] == 'R') f = n; 
            else if (A[i] == 'L') f = 0; 
            else f = Math.max(f-1, 0); 
            
            forces[i] = f; 
        }
        
        // System.out.println(Arrays.toString(forces));
        
        f = 0; 
        
        for (int i=n-1; i>=0; --i) { 
            if (A[i] == 'L') f = n; 
            else if (A[i] == 'R') f = 0; 
            else f = Math.max(f-1, 0);
            
            forces[i] -= f; 
        }
        
        // System.out.println(Arrays.toString(forces));
        
        StringBuilder sb = new StringBuilder(); 
        for (int ch : forces) {
            sb.append(ch > 0 ? 'R' : ch < 0 ? 'L' : '.');
        }
        
        return sb.toString();
    }
}
