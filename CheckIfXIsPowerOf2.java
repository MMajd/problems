public class CheckIfXIsPowerOf2 { 
	public boolean isPowerOf2(int x) { 
		if (x == 0) return false; 
		return (x & (x-1)) == 0;
	}

}
