/** 
 *
 */

class IncreasingTripletsSubSequence {

    public boolean increasingTriplet(int[] nums) {
        int len = nums.length; 
        if (len <= 2) return false; 
        
        int[] dp = new int[len]; 
        
        TreeSet<Integer> set = new TreeSet<>();
        
        // Patience sort nlogn  
        
        for (int i=0; i<len; i++) { 
            Integer x = set.ceiling(nums[i]);
            
            if (x != null) { 
                set.remove(x);
            }
            
            set.add(nums[i]);
            
            if (set.size() >= 3) return true;
        }
        
        return false; 
    }
}
