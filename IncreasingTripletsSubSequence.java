/** 
 *
 */

class IncreasingTripletsSubSequence {
    /** binary search solution */
     class Solution {
         public boolean increasingTriplet(int[] nums) {
             int[] dp= new int[3];
             int len=0;
             for(int n: nums){
                 // returns -1 * (insertion point - 1) 
                 int index= Arrays.binarySearch(dp,0,len, n);
                 if(index<0){
                     index=-(index+1); // to get insertion index;
                 }
              
                 dp[index]= n;

                 if(index == len)               
                     len++;
                 if(len==3) return true;
             }
             return false;
         }
     }



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
