/* 
@link https://leetcode.com/problems/permutations/

 Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.


Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
    Input: nums = [0,1]
    Output: [[0,1],[1,0]]

Example 3:
    Input: nums = [1]
    Output: [[1]]
 

Constraints: 
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10 
    All the integers of nums are unique. 

    previous constarint define the relation between 
    indices and values contained in them which is one-to-one mapping
*/ 


class Solution {
    List<List<Integer>> ans = new ArrayList<>(); 
    public List<List<Integer>> permute(int[] nums) {
        solve(0, nums);
        return ans; 
    }
    
    private void solve(int index, int[] nums) { 
        if (index == nums.length) { 
            List<Integer> data = new ArrayList<>(); 
            for (int i : nums) data.add(i);
            ans.add(data);
            return; 
        }
        
        for (int i=index; i<nums.length; i++) { 
            swap(i, index, nums);
            solve(index+1, nums);
            swap(i, index, nums);
        }
    }
    
    private void swap(int i, int j, int[] arr) { 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}


class Solution {
    List<List<Integer>> ans = new ArrayList<>(); 
    public List<List<Integer>> permute(int[] nums) {
        /* used array is used to mark indices of used values
         * marking indices as visited, is the same as marking values; 
         * values are unique, thus indices and values have a 
         * one-to-one mapping relation
        */ 
        boolean[] used = new boolean[nums.length]; 
        
        solve(0, nums, used, new ArrayList<>());
        
        return ans; 
    }
    
    private void solve(int index, nums, used, List<Integer> data) { 
        if (index == nums.length) { 
            ans.add(new ArrayList<>(data));
            return; 
        }
        
        for (int i=0; i<nums.length; i++) { 
            if (used[i]) continue; 
            used[i] = true; 
            data.add(nums[i]);
            solve(index+1, data);
            data.remove(data.size()-1);
            used[i] = false; 
        }
    }
}


