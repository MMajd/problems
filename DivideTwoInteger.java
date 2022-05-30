public class DivideTwoInteger {
    public int divide(int dividend, int divisor) {
	// edge case 
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;

	// if a is MIN_VALUE abs won't affect it
	// this case is handled in the while loop
        int a = Math.abs(dividend), 
            b = Math.abs(divisor), 
            res = 0, 
            x = 0;
        
	// even if a was MIN_VALUE
	// when we substract gives us the right answer due to overflow
        while (a - b >= 0) {     
            for (x = 0; a - (b << 1 << x) >= 0; x++); 
            res += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

}
