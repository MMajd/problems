/*
 @link https://leetcode.com/problems/subsets-ii/

 Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
    Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
    Input: nums = [0]
    Output: [[],[0]]

Constraints:
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
*/


/** 
 * Same idea of subsets but 
 * avoiding using same key multiple time in the same subset
 **/
class Solution {
    List<List<Integer>> ans = new LinkedList<>(); 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        
        for (int i=0; i<=nums.length; i++) { 
            solve(0, i, nums, new LinkedList<>());
        }
        
        return ans; 
    }
    
    private void solve(int s, int len, int[] nums,  
                                    List<Integer> data) { 
        if (len == data.size()) { 
            ans.add(new ArrayList<>(data));
            return;
        }
        
        Integer prev = null; 
        
        for (int i=s; i<nums.length; i++) { 
            if (prev != null && prev == nums[i]) continue; 
            prev = nums[i];
            data.add(nums[i]);
            solve(i+1, len, nums, data); 
            data.remove(data.size()-1);
        }
    }
}


/** Same as above but using set instead of prev */
class Solution {
    List<List<Integer>> ans = new LinkedList<>(); 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // use counting sort here if you can 
        Arrays.sort(nums);
        
        for (int i=0; i<=nums.length; i++) { 
            solve(0, i, nums, new LinkedList<>());
        }
        return ans; 
    }
    
    private void solve(int s, int len, int[] nums, 
                                    List<Integer> data) { 
        if (len == data.size()) { 
            ans.add(new ArrayList<>(data));
            return;
        }
        
        Set<Integer> set = new HashSet<>();
        
        for (int i=s; i<nums.length; i++) { 
            if (set.contains(nums[i])) continue; 

            data.add(nums[i]);
            solve(i+1, len, nums, data); 
            data.remove(data.size()-1);

            set.add(nums[i]);
        }
    }
}

/** Using counting sort */
class Solution {
    List<List<Integer>> ans = new LinkedList<>(); 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        sort(nums); 
        
        for (int i=0; i<=nums.length; i++) { 
            solve(0, i, nums, new ArrayList<>(nums.length));
        }
        
        return ans; 
    }
    
    private void solve(int s, int len, int[] nums, List<Integer> data) { 
        if (len == data.size()) { 
            ans.add(new ArrayList<>(data));
            return;
        }
        
        // Set<Integer> set = new HashSet<>();
        Integer prev = null; 
        
        for (int i=s; i<nums.length; i++) { 
            // if (set.contains(nums[i])) continue; 
            if (prev != null && prev == nums[i]) continue; 
            prev = nums[i];
            data.add(nums[i]);
            solve(i+1, len, nums, data); 
            data.remove(data.size()-1);
            // set.add(nums[i]);
        }
    }
    
    public void sort(int[] arr) { 
        int min = 20;  
        int max = -20;
        
        for (int i : arr) { 
            if (min > i) min = i; 
            if (i > max) max = i; 
        }
        
        int[] output = new int[arr.length];
        int[] count = new int[max-min+1];
        
        for (int i=0; i<arr.length; i++) { 
            count[arr[i]-min] += 1;
        }
        
        for (int i=1; i<count.length; i++) { 
            count[i] += count[i-1];
        }
        
        for (int i=0; i<arr.length; i++) { 
            output[count[arr[i]-min]-1] = arr[i]; 
            count[arr[i]-min]--; 
        }
        
        for (int i=0; i<arr.length; i++) { 
            arr[i] = output[i]; 
        }
    }
}
