/** 
@link : https://leetcode.com/problems/powx-n/ 

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

Example 1:
    Input: x = 2.00000, n = 10
    Output: 1024.00000

Example 2:
    Input: x = 2.10000, n = 3
    Output: 9.26100

Example 3:
    Input: x = 2.00000, n = -2
    Output: 0.25000
    Explanation: 2-2 = 1/22 = 1/4 = 0.25
 
Constraints:
    -100.0 < x < 100.0
    -2^31 <= n <= 2^31-1
    -10^4 <= x^n <= 10^4
*/ 


public class XpowN { 
    public static void main (String args[]) { 
        System.out.println(power(3, 10));
    }

    public double myPow(double x, int n) {
        double ans = 1; 
        int power = n; 

        /** if x < 0 and n is even then the sign is positive, 
         * else sign is negative */
        int sign = ((x > 0) || (n%2==0))  ? 1:-1; 
        x = Math.abs(x); 
        
        if (n < 0) { 
            x = 1.0 / x; 

            if (power == Integer.MIN_VALUE)  { 
                power = Integer.MAX_VALUE; 
            }
            else { 
                power *= -1; 
            }
        }
        
        while (power != 0) { 
            if ((power & 0x01) != 0) { 
                ans *= x; 
            }
            
            x *= x; 
            power >>= 1; 
        }
        
        return ans * sign; 
    }
}


class Solution {
    public double myPow(double x, int n) {
        int sign = ((x > 0) || (n%2==0)) ? 1 : -1;
        x = Math.abs(x); 
        
        if (n < 0) {
            x = 1/x;
            
            // convert power to positive value 
            if (n == Integer.MIN_VALUE) n = Integer.MAX_VALUE;
            else n *= -1; 
        }
        
        return xPowN(x, n, 1*sign); 
    }
    
    /// tail recursion 
    private double xPowN(double x, int n, double ans) { 
        if (n == 0) return ans; 
        return xPowN(x*x, n>>1, (n&0x01)!=0 ? x * ans : ans); 
    }
}

/** Binary search solution, Not working */
class Solution {
    public double myPow(double x, int n) {
        double ans = 1; 
        int sign = ((x > 0) || (n%2==0))  ? 1:-1; 
        x = Math.abs(x); 
        
        if (n < 0) { 
            x = 1/x; 
            
            if (n == Integer.MIN_VALUE) 
                n = Integer.MAX_VALUE; 
            else n *= -1; 
        }
        
        long i = 1;
        
        while (i <= n) { 
            ans *= x;   
            
            if (i < n/2) { 
                ans *= ans; 
                i = 2*i+1; 
            }
            
            else i += 1;
        }
        
        return ans * sign; 
    }
}
