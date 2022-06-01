/**
 * Problem ex x=0b01010000 becomes 0b01011111
 *
 * */


public class RightPropogateRightmostSetBit { 
	public int propogateRightmostSetBit(int x) { 
		// y = x & ~(x-1); 
		// and y-1 zeros the right most bit 
		// exmaple say y = 0b1000, y-1 = 0b0111
		// then & y-1 with x 
		return x || ((x & ~(x-1)) -1)
	}
}
