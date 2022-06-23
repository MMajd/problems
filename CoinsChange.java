/** 
 * @link https://leetcode.com/problems/coin-change/
 */
public class CoinsChange { 
	public static void main(String[] args) { 
	}


	private class Solution {
		public int changeCoin2(int[] coins, int amount) { 
			int[][] dp = new int[coins.length][amount + 1]; 

			for (int i=0; i<= coins.length; i++) { 
				for (int j=0; j<= amount; j++) { 
					if (i == 0) dp[i][j] = amount + 1; 
					else if (j == 0) dp[i][j] = 0; 
					else if (coins[i-1] > j) dp[i][j] = dp[i-1][j];
					else { 
						dp[i][j] = Math.min(dp[i][j-coins[i-1]] + 1, dp[i-1][j]);
					} 

				}
			}

			return dp[coins.length][amount] > amount 
					? -1 
					: dp[coins.length][amount];
		}


		public int changeCoin(int[] coins, int amount) { 
			int dp[] = new int [amount + 1]; 
			Arrays.fill(dp, amount + 1); 
			for (coin: coins) { 
				for (int currAmount=1; currAmount<= amount; currAmount++) { 
					if (currAmount >= coin) { 
						dp[currAmount] = Math.min(dp[currAmount-coin] + 1, dp[currAmount]); 
					}
				}
			}
			return dp[amount] > amount ? -1 : dp[amount];  // if there's no change return -1
		}
	}
}
