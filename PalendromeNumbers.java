
public class PalendromeNumbers { 

	public boolean palendromeNumber(int x) { 

		int y = 0; 

		while (x > y) { 
			y = y * 10 + x % 10; 
			x /= 10; 
		}

		return x == y || x == y / 10 
	}

}
