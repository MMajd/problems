

/** 
 *
 * @link leetcode.com/problems/kth-largest-element-in-an-array
 *
 * Java solution
 */

class KthLargestElementInArray { 
    /** BETTER SPACE COMPLEXITY, O(K) */
    public int findKthLargest(int[] nums, in k) {
        /** Min queue */
        PriorityQueue<Integer> q = new PriorityQueue<>(k+1);

        for (int i : nums) { 
            q.add(i);

            if (q.size() > k) q.poll();
        }

        return q.peek();
    }

    /** SPACE OF O(n), First try solution */
    public int findKthLargest(int[] nums, int k) {
        /** Max Queue */
        PriorityQueue<Integer> q = new PriorityQueue<>(
            k,
            (a, b) -> b.compareTo(a)
        );
        
        int counter = 0; 
        
        for (int i : nums) { 
            q.add(i);
        }
        
        for (int i=k-1; i>=1; i--) q.poll();
        
        return q.peek();
    }
}

