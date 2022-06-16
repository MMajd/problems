/** 
 *
 * @link https://leetcode.com/problems/top-k-frequent-elements
 *
 */

class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= 1) return nums;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer,Integer>> q = new 
            PriorityQueue<>(
                map.size(), 
                (a, b) -> b.getValue().compareTo(a.getValue())
            ); 
        
        
        for(Map.Entry<Integer,Integer> e : map.entrySet()) { 
            q.offer(e);
        }
        
        int[] ans = new int[k];
        
        for (int i=k-1; i>=0; i--) { 
            ans[i] = q.poll().getKey();
        }
        
        return ans; 
    }
}
