/*

 @link https://leetcode.com/problems/combination-sum-ii/
 
 Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:
    Input: candidates = [10,1,2,7,6,1,5], target = 8
    Output: 
    [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
    ]

Example 2:
    Input: candidates = [2,5,2,1,2], target = 5
    Output: 
    [
        [1,2,2],
        [5]
    ]
 

Constraints:
    1 <= candidates.length <= 100
    1 <= candidates[i] <= 50
    1 <= target <= 30

*/


class Solution {
    List<List<Integer>> ans = new LinkedList<>(); 
    public List<List<Integer>> 
        combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        solve(0, target, 
                new ArrayList<>(candidates.length), 
                candidates);
        return ans; 
    }
    
    private void solve(int i, int target, 
                       List<Integer> data, int[] cand) { 
        if (target == 0) { 
            ans.add(new ArrayList<>(data));
            return; 
        }
        
        for (int k=i; k<cand.length; k++) { 
            if (k>i && cand[k-1] == cand[k]) continue; 
            if (cand[k]-target > 0) break;
            data.add(cand[k]);
            solve(k+1, target-cand[k], data, cand);
            data.remove(data.size()-1);
        }
    }
}
