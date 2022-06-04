/** 
 * EPI, Efficient Long Parity Computation 
 * 64-bit 
 * */


public class CheckParityOfLong { 
    public static short precomputed[]; 
    public static final int BIT_MASK = 0xFFFF; 
    public static final int WORD_SIZE = 16; 

    public static void main (String[] args) { 
        precomputed = new short[Math.pow(2, 16)]; 
        for (int i=0; i<precomputed.length; i++) precomputed[i] = getParity(i); 
    }

    public static short getParity (int x) {
        x ^= x >> 32;
        x ^= x >> 16;
        x ^= x >> 8;
        x ^= x >> 4;
        x ^= x >> 2;
        x ^= x >> 1;

        return short(x & 0x01); 
    }

    public static short solve (long x) { 
        return precomputed[x & BIT_MASK] 
            ^ precomputed[(x >> WORD_SIZE) & BIT_MASK] 
            ^ precomputed[(x >> (2*BIT_MASK)) & BIT_MASK] 
            ^ precomputed[(x >> (3*BIT_MASK)) & BIT_MASK]; 
    }

}

