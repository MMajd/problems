/**
 * @link https://leetcode.com/problems/4sum/
 */

import java.util.*; 

class FourSum {
    private Deque<Integer> quad; 
    private List<List<Integer>> ans; 
    private int len; 
    private int[] nums; 
    
    public FourSum() { 
        ans = new ArrayList<>();
        quad  = new LinkedList<>();
    }
    
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        if (nums.length < 4) return ans; 
        Arrays.sort(nums);
        
        this.nums = nums; 
        len = nums.length; 
        
        kSum(0, target, 4);
        
        return ans; 
    }
    
    
    private void kSum(int start, int target, int k) {
        if (k != 2) { 
            for (int i=start; i<len-k+1; i++) {
                if (i > start && nums[i] == nums[i-1]) continue; 
                
                quad.push(nums[i]);
                kSum(i+1, target-nums[i], k-1);
                quad.pop();
            }
            
            return;
        }
        
        int left=start, right=len-1; 
        while(left < right) { 
            int sum = nums[left] + nums[right]; 
            
            if (sum > target) { 
                right -= 1; 
                while(right > left && nums[right] == nums[right+1]) right--;
            }
            else if (sum < target) { 
                left += 1; 
                while(left < right && nums[left] == nums[left-1]) left += 1; 
            } 
            else { 
                // add to the list 
                quad.push(nums[left]);
                quad.push(nums[right]);
                
                ans.add(new ArrayList<Integer>(quad));
                
                quad.pop();
                quad.pop();
                
                left += 1; 
                while(left < right && nums[left] == nums[left-1]) left += 1; 
            }
        }
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // if array less than 4 elements return empty list 
        if (nums.length < 4) return result; 
        
        Arrays.sort(nums);
        
        int len = nums.length; 
        
        for (int i=0; i<len-3; i++) {
            // make sure that there're no duplicates by make sure we have distinct 
            // values for the 0-index in answer lists
            if (i > 0 && nums[i] == nums[i-1]) continue; 
            
            for (int j=i+1; j<len-2; j++) { 
                // make sure that there're no duplicates by make sure we have distinct 
                // values for the 1-index in answer lists
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                
                // make sure that there're no duplicates by make sure we have distinct 
                // values for the 2-index and 3-index in answer lists 
                int left = j+1; 
                int right = len-1; 
                
                while(left < right) { 
                    int sum = nums[i] + nums[j] + nums[left] + nums[right]; 
                    
                    if (sum > target) { 
                        right -= 1; 
                        // if new right is equal to the previous one search for new one 
                        while(right > left && nums[right] == nums[right+1]) right -= 1;
                    }
                    else if (sum < target) { 
                        left += 1; 
                        // if new left is equal to the previous one search for new one 
                        while(left < right && nums[left] == nums[left-1]) left += 1; 
                    } 
                    else { 
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        right -= 1; 
                        while(right > left && nums[right] == nums[right+1]) right -= 1;
                        
                        left += 1; 
                        while(left < right && nums[left] == nums[left-1]) left += 1; 
                    }
                }
            }
        }
        
        return result; 
    }
}



