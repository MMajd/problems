/*

 @link https://leetcode.com/problems/permutations-ii/

 Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 

Example 1:
    Input: nums = [1,1,2]
    Output:
    [[1,1,2],
    [1,2,1],
    [2,1,1]]

Example 2:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10

*/


class Solution {
    List<List<Integer>> ans = new ArrayList<>(); 
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        solve(0, nums);
        return ans; 
    }
    
    private void solve(int index, int[] nums) {
        if (index == nums.length) { 
            List<Integer> data = new ArrayList<>(index);
            for (int i : nums) data.add(i);
            ans.add(data);
            return; 
        }
        
        Set<Integer> set = new HashSet<>(); 
        
        for (int i=index; i<nums.length; i++) { 
            if (set.contains(nums[i])) continue; 
            swap(i, index, nums); 
            solve(index+1, nums);
            swap(i, index, nums); 
            set.add(nums[i]);
        }
    }
    
    private void swap(int i, int j, int[] arr) { 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}
