

/** 
 *
 * @link leetcode.com/problems/kth-largest-element-in-an-array
 *
 *
 * Java solution
 *
 */

class KthLargestElementInArray { 
    public int findKthLargest(int[] nums, int k) {
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
    
    private PriorityQueue<Integer> removelast(
        PriorityQueue<Integer> pq
    ) {

        PriorityQueue pqnew = new PriorityQueue();

        while(pq.size() > 1)
        {
            pqnew.add(pq.poll());
        }

        pq.clear();
        return pqnew;
    }
}

