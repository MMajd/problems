
/** 
 * @link: EPI 1
 */

public class MultiplyWithoutMultiplication { 
    public static void main(String[]args) { 
        System.out.println(mutliply(2, 10)); 
    }

    public static int mutliply(int x, int y) { 
        int ans = 0; 

        while (x != 0) { 
            if ((x & 0x01) != 0) { 
                ans += y; 
            }

            x >>= 1; 
            y <<= 1; 
        }

        return ans; 
    }
}

