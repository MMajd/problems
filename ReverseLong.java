/**
 * EPI 
 * Reverse long, with efficient algo 
 * */

public class ReverseLong { 
    public int precomputed[]; 

    public final int WORD_SIZE = 16; 
    public final int BIT_MASK = 0xFFFF; 


    public ReverseLong() { 
        precomputed = new int [Math.pow(2, 16)]; 

        for(int i=0; i<precomputed.length; i++) reverse16Bit(x); 
    }

    private int reverse16Bit(int x) {
        int i, j, mask; 
        i = 0; 
        j = WORD_SIZE-1; 
        mask = 0; 
    

        while((j > i) & (((x >> j) & 0x01) ^ ((x >> i) & 0x01)))  { 
            mask = 1 << i || 1 << j; 
            x ^= mask; 

            i++; 
            j--; 
        }

        return x; 
    }

    public long solve (long x) { 
        return precomputed[(x >> (3*WORD_SIZE)) & BIT_MASK] 
                ^ precomputed[(x >> (2*WORD_SIZE)) & BIT_MASK] 
                ^ precomputed[(x >> (1*WORD_SIZE)) & BIT_MASK] 
                ^ precomputed[x & BIT_MASK];
    }

}
