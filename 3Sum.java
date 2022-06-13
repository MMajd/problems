/** 
 *
 */


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) Arrays.asList();
        
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        
        for(int i=0; i<nums.length-2;i++){
            int j =i+1;
            int  k = nums.length-1;
            
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==0) ans.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
                else if ( sum >0) k--;
                else if (sum<0) j++;
            }

        }

        return  new ArrayList<>(ans);
    }
}
