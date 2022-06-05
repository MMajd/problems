public class XexpYBitManipulation { 
    public static void main (String args[]) { 
        System.out.println(power(3, 10));
    }


    public static double power(double x, int y) {
        double result = 1.0;
        long power = y;

        if (y < 0) {
            power = -power;
            x = 1.0 / x ;
        }

        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
                System.out.println("X: "+ x + "\tR: " + result);
            }

            x *= x ;
            power >>= 1 ;
            System.out.println("X: "+ x + "\tP: " + power);
        }

        return result ;
    }

}
