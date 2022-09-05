/* 
 @link https://leetcode.com/problems/top-k-frequent-elements

 Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

Example 2:
    Input: nums = [1], k = 1
    Output: [1]

Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.
 

 ****************
 - Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 ****************
  
 */

/** Quick sort */
class Solution {
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int partition(int left, int right, int pidx, 
                         int[] unique, Map<Integer,Integer> map) {
        int pfreq = map.get(unique[pidx]);
        swap(unique,pidx, right);
        int j = left;

        for (int i = left; i <= right; i++) {
            if (map.get(unique[i]) < pfreq) {
                swap(unique, j++, i);
            }
        }
        
        swap(unique, j, right);

        return j;
    }
    
    public void quickselect(int left, int right, int kidx, 
                         int[] unique, Map<Integer,Integer> map) {
        if (left == right) return;
        int pidx = left + (right-left)/2;

        pidx = partition(left, right, pidx, unique, map);

        if (kidx == pidx) return;    
        if (kidx < pidx) quickselect(left, pidx-1, kidx, unique, map);     
        else quickselect(pidx + 1, right, kidx, unique, map);  
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int n = map.size();
        int[] unique = new int[n]; 
        
        int i = 0;
        for (int num: map.keySet()) {
            unique[i] = num;
            i++;
        }
        
        quickselect(0, n-1, n-k, unique, map);
        return Arrays.copyOfRange(unique, n - k, n);
    }
}


/** Bucket sort */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        ArrayList<Integer>[] bucket = new ArrayList[nums.length+1];
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (Integer num : map.keySet()) {
            int freq = map.get(num);
            if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(num);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = nums.length; i >= 0 || res.size() < k; i--) {
            if (bucket[i] != null) res.addAll(bucket[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}


/** Heap */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {      
        Map<Integer, Integer> map = new HashMap<>(); 
        
        for (int i : nums) { 
            map.compute(i, (n, f) -> f != null ? ++f : 1);
        }
        
        Queue<int[]> pq = new PriorityQueue<>(k+1, (a, b) -> b[1] - a[1]);
        
        for (Map.Entry<Integer, Integer> e : map.entrySet()) { 
            pq.add(new int[]{ e.getKey(), e.getValue() });

            // need to be min heap instead of max heap
            // if (pq.size() > k) pq.poll();
        }
        
        int[] ans = new int[k]; 
        
        for (int i=0; i<k; i++) { 
            ans[i] = pq.poll()[0]; 
        }
        
        return ans; 
    }
}



