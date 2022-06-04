/** 
 * This solution depends on segTree tree
 * @link: https://leetcode.com/problems/range-sum-query-mutable
 */
public class RangeQueryMutable { 

    public static void main(String[] args) {}

    private class NumArray {

        int[] segTree;
        int n;
        
        public NumArray(int[] nums) {
            this.n = nums.length;
            this.segTree = new int[4 * n];
            build(nums, 1, 0, n - 1);
        }
        
        private void build(int[] nums, int pos, int start, int end) {
            if(start == end){
                this.segTree[pos] = nums[start];
                return;
            }
            
            int mid = start + (end - start) / 2;
            build(nums, 2 * pos, start, mid);
            build(nums, 2 * pos + 1, mid + 1, end);
            
            segTree[pos] = segTree[2 * pos] + segTree[2 * pos + 1];
        }
        
        public void update(int index, int val) {
            updateHelper(1, 0, n - 1, index, val);
        }
        
        private void updateHelper(int pos, int start, int end, int idx, int val) {
            if(start == end) {
                segTree[pos] = val;
                return; 
            }
            
            int mid = start + (end - start) / 2;
            if(idx > mid) updateHelper(2 * pos + 1, mid + 1, end, idx, val);
            else updateHelper(2 * pos, start, mid, idx, val);

            segTree[pos] = segTree[2 * pos] + segTree[2 * pos + 1]; 
        }
         
        private int sumRangeHelper(int pos, int start, int end, int left, int right) {
            if(left <= start && right >= end)
                return segTree[pos];
            
            if(left > end || right < start)
                return 0;
            
            int mid = start + (end - start) / 2;
            int l = sumRangeHelper(2 * pos, start, mid, left, right);
            int r = sumRangeHelper(2 * pos + 1, mid + 1, end, left, right);
            return l + r;
        }

        public int sumRange(int left, int right) {
            return sumRangeHelper(1, 0, n - 1, left, right);
        }
    }
}
