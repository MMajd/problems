class Knapsack {
    public int solveKnapsack(int[] p, int[] w, int c) {
        if (c <= 0 || p.length == 0 || w.length != p.length)
            return 0;
 
        int n = p.length;
        int[][] dp = new int[n][c+1]; 

        // fill the first row of the dp array with the proper values 
        // i = 0; 
        for (int j=0; j<=c; j++) {
          if (w[0] <= j) dp[0][j] = w[0]; 
        }

        // start from the second item and up , 1 <= i < n;
        for(int i=1; i<n; i++) {
            for(int j=1; j <= c; j++) {       
                int p1 = 0;                     
                if(w[i] <= j)  p1 = p[i] + dp[i-1][j-w[i]];
                dp[i][j] = Math.max(p1, dp[i-1][j]); 
            }
        }
        
        return dp[n-1][c];
    }
 
    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] p = {1, 6, 10, 16};
        int[] w = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(p, w, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(p, w, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
