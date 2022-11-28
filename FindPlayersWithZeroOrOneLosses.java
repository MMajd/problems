/*
 @link https://leetcode.com/problems/find-players-with-zero-or-one-losses
 @categories (hashing/mapping/sorting/counting) 

 You are given an integer array matches where matches[i] = [winner-i, loser-i] 
indicates that the player winner-i defeated player loser-i in a match.

Return a list answer of size 2 where:
- answer[0] is a list of all players that have not lost any matches.
- answer[1] is a list of all players that have lost exactly one match.
- The values in the two lists should be returned in increasing order.

Note:
- You should only consider the players that have played at least one match.
- The testcases will be generated such that no two matches will have the same outcome.
 

Example 1:
    Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
    Output: [[1,2,10],[4,5,7,8]]
    Explanation:
    Players 1, 2, and 10 have not lost any matches.
    Players 4, 5, 7, and 8 each have lost one match.
    Players 3, 6, and 9 each have lost two matches.
    Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].

Example 2:
    Input: matches = [[2,3],[1,3],[5,4],[6,4]]
    Output: [[1,2,5,6],[]]
    Explanation:
    Players 1, 2, 5, and 6 have not lost any matches.
    Players 3 and 4 each have lost two matches.
    Thus, answer[0] = [1,2,5,6] and answer[1] = [].

Constraints:
    1 <= matches.length <= 10^5
    matches[i].length == 2
    1 <= winneri, loseri <= 10^5
    winner-i != loser-i
    All matches[i] are unique.
*/

class Solution {
    private static final int W = 0; 
    private static final int L = 1; 

    public List<List<Integer>> findWinners(int[][] matches) {
        int N = matches.length;

        TreeMap<Integer, int[]> players = new TreeMap<Integer, int[]>(); 

        for (int[] m : matches) { 
            players.compute(m[W], (k, v) -> { 
                if (v == null) return new int[]{1, 0}; 
                v[0]  += 1; 
                return v; 
            });

            players.compute(m[L], (k, v) -> { 
                if (v == null) return new int[]{0, 1};
                v[1] += 1; 
                return v; 
            });
        }

        List<List<Integer>> ans = new ArrayList<>(2); 
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        for (Map.Entry<Integer, int[]> e : players.entrySet()) { 
            int p = e.getKey();
            int[] v = e.getValue();

            if (v[L] == 0) ans.get(0).add(p);
            else if (v[L] == 1) ans.get(1).add(p);
        }

        return ans; 
    }
}

