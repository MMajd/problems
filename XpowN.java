
/** 
 *
 * @link : https://leetcode.com/problems/powx-n/ 
 */ 


public class XpowN { 
    public static void main (String args[]) { 
        System.out.println(power(3, 10));
    }

    public double myPow(double x, int n) {
        double ans = 1; 
        int power = n; 
        int sign = x > 0? 1:-1; 
        x = Math.abs(x); 
        
        if (n < 0) { 
            x = 1.0 / x; 

            if (power == Integer.MIN_VALUE) power = Integer.MAX_VALUE; 
            else power *= -1; 
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
