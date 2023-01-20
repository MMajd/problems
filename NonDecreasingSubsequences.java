/*
 @link https://leetcode.com/problems/non-decreasing-subsequences
 @categories (backtracking) 

 Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

Example 1:
    Input: nums = [4,6,7,7]
    Output: [[4,6],[4,6,7],[4,6,7,7],
        [4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

Example 2:
    Input: nums = [4,4,3,2,1]
    Output: [[4,4]]

Constraints:
    1 <= nums.length <= 15
    -100 <= nums[i] <= 100
*/
class Solution {
    List<Integer> seq = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>(); 

    public List<List<Integer>> findSubsequences(int[] A) {
        bt(A, 0);
        return ans; 
    }

    private void bt(int[] A, int idx) {  
        if (seq.size() > 1) ans.add(new ArrayList<>(seq));
        if (idx == A.length) return; 

        Set<Integer> set = new HashSet<>(); 

        for (int i=idx; i<A.length; i++) { 
            if (set.contains(A[i])) continue; 
            if (!seq.isEmpty() && seq.get(seq.size()-1) > A[i]) continue; 

            set.add(A[i]);

            seq.add(A[i]);
            bt(A, i+1);
            seq.remove(seq.size()-1);
        }
    }
}
