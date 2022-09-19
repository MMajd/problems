/** 
@link: https://leetcode.com/problems/range-sum-query-mutable
@categories (seg-tree/d-&-c)

Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between 
indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:
    Input
    ["NumArray", "sumRange", "update", "sumRange"]
    [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
    Output
    [null, 9, null, 8]

Explanation
    NumArray numArray = new NumArray([1, 3, 5]);
    numArray.sumRange(0, 2);    
    return 1 + 3 + 5 = 9
    numArray.update(1, 2);   // nums = [1, 2, 5]
    numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8

                             
Constraints:
    1 <= nums.length <= 3 * 10^4
    -100 <= nums[i] <= 100
    0 <= index < nums.length
    -100 <= val <= 100
    0 <= left <= right < nums.length
    At most 3 * 10^4 calls will be made to update and sumRange.
  
 */

class NumArray {
    private int[] seg, arr; 
    private int n; 

    public NumArray(int[] nums) {
        n = nums.length; 
        seg = new int[4*n]; 
        arr = nums.clone(); 
        
        build(1, 0, n-1); 
    }

    private void build(int pos, int start, int end) { 
        if (start == end) { 
            seg[pos] = arr[start]; 
            return; 
        }
        
        int mid = start+(end-start)/2; 
        
        build(2*pos, start, mid); 
        build(2*pos+1, mid+1, end); 
        
        seg[pos] = seg[2*pos] + seg[2*pos+1]; 
    }
    
    private void updateHelper(int pos, int start, int end, 
                              int idx, int val) { 
        
        if (start == end) { 
            seg[pos] = val;
            return; 
        }
        
        int mid = start+(end-start)/2; 
        
        if (idx>mid) updateHelper(2*pos+1, mid+1, end, idx, val); 
        else updateHelper(2*pos, start, mid, idx, val); 
        
        seg[pos] = seg[2*pos] + seg[2*pos+1]; 
    }
    
    public void update(int index, int val) {
        updateHelper(1, 0, n-1, index, val); 
    }
    
    
    public int get(int pos, int start, int end, int left, int right) { 
        if (start>=left && end<=right) { 
            return seg[pos]; 
        }
        
        if (start>right || end<left) return 0; 
        
        int mid = start+(end-start)/2; 
        int l = get(2*pos, start, mid, left, right); 
        int r = get(2*pos+1, mid+1, end, left, right); 
        
        return l + r; 
    }
    
    public int sumRange(int left, int right) {
        return get(1, 0, n-1, left, right); 
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
