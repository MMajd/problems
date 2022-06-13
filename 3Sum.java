/** 
 *
 * @link https://leetcode.com/problems/3sum/
 *
 */

class Solution {    
    public List<List<Integer>> threeSum(int[] nums) {    
        if (nums.length < 3) Arrays.asList();    
            
        Arrays.sort(nums); // IMPORTANT TO AVOID DUPLICATES TRIPLETS    
                                                                             
        List<List<Integer>> ans = new ArrayList<>();          
                                                         
        for(int i=0; i<nums.length-2;i++) {               
            // If we start with the same last element continue;
            if (i > 0 && nums[i] == nums[i-1]) continue;                              
                                                       
            // this two sum with two pointer     
            // this applies on sorted arrays     
            int j =i+1;                                           
            int  k = nums.length-1;                      
                                                       
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];     
                if (sum > 0) k -= 1; 
                else if (sum < 0) j += 1; 
                else { 
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j += 1; 
                    // if we land on the same element keep searching for new element 
                    // we do not need to decrease k above condition will takecare of that  
                    while(nums[j] == nums[j-1] && j < k) j += 1;
                    
                    /** 
                        We guarantee to get a new list every time as we do not allow to 
                        use same ith element (0-index in answer lists) twice and also same applies 
                        to the jth element (1-index in answer lists)
                    */
                }
            } 
        }

        return ans; 
    }
}
