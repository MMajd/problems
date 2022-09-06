/** 
  
  @link https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/

  Problem statement: 
      Given two integer arrays to represent weights and profits of ‘N’ items, 
      we need to find a subset of these items which will give us 
      maximum profit such that their cumulative weight is not more than a given number ‘C’. 
      Write a function that returns the maximum profit. 
      Each item can only be selected once, 
      which means either we put an item in the knapsack or skip it.

 */

/** With memo */
class Knapsack {
    private int solve(int[] p, int[] w, int c, int idx, Integer[][]dp) {
        if (c <= 0 || idx >= p.length) return 0;
        if (dp[c][idx] != null) return dp[c][idx]; 

        int p1 = 0; 
        if (w[idx] <= c) {
            p1 = p[idx] + solve(p, w, c-w[idx], idx+1);
        }

        int p2 = solve(p, w, c, idx+1);

        dp[c][idx] = Math.max(p1, p2); 

        return dp[c][idx]; 
    }

    public int solveKnapsack(int[] p, int[] w, int c) {
        return solve(p, w, c, 0, new Integer[c+1][p.length]); 
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = { 4, 5, 3, 7 };
        int[] weights = { 2, 3, 1, 4 }; 
        int maxProfit = ks.solveKnapsack(profits, weights, 5);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}





/** Without memo */
class Knapsack {
    private int solve(int[] p, int[] w, int c, int idx) {
        if (c <= 0 || idx >= p.length)
            return 0;

        int p1 = 0; 
        // You have two choice either to take the current item or 
        // to skip it 

        /**
         *                       A 
         *          TakeA /             \ Leave A 
         *
         *             B                 B 
         *                              ... 
         *    TakeB  /  \ Leave B      .....
         */

        if (w[idx] <= c) {
            p1 = p[idx] + solve(p, w, c-w[idx], idx+1);
        }

        int p2 = solve(p, w, c, idx+1);
        return Math.max(p1, p2); 
    }

    public int solveKnapsack(int[] p, int[] w, int c) {
        return solve(p, w, c, 0); 
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = { 4, 5, 3, 7 };
        int[] weights = { 2, 3, 1, 4 }; 
        int maxProfit = ks.solveKnapsack(profits, weights, 5);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}

