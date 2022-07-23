/*

@link https://leetcode.com/problems/subsets/

 Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

The question is to generate the power set of an array

Example 1:
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
    Input: nums = [0]
    Output: [[],[0]]
 

Constraints:
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
    All the numbers of nums are unique.
*/

/** First to come to mind is backtracking because of the nature of the problem */

class Solution {
    List<List<Integer>> ans = new ArrayList<>(); 
    
    public List<List<Integer>> subsets(int[] nums) {
        for (int i=0; i<=nums.length; i++) { 
            backtrack(0, i, nums, new ArrayList<>());
        }

        return ans; 
    }
    
    private void backtrack(int start, int setLen, 
            int[] nums, List<Integer> data) { 
        
        if (setLen == data.size()) { 
            ans.add(new ArrayList<>(data));
            return;
        }
        
        for (int i=start; i<nums.length; i++) { 
            data.add(nums[i]);
            backtrack(i+1, setLen, nums, data);
            data.remove(data.size()-1);
        }
    }
}


/** Other approaches to be considered */
/** DP / Incremental approach */
class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>() {
            { add(new ArrayList<>()); }
        };
        
        for (int i : nums) { 
            List<List<Integer>> subsets = new ArrayList<>();
            
            for (List<Integer> curr : output) { 
                subsets.add(new ArrayList<>(curr) {
                    { add(i); }
                });
            }
            
            for (List<Integer> curr : subsets) { 
                output.add(curr);
            }
        }
        
        return output; 
    }
}
