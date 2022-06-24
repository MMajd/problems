/** 
 *
 *
 * @link https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 *
 */ 

class Solution {
    public boolean isPossible(int[] target) {
        if (target.length == 1) return target[0] == 1; 
        
        PriorityQueue<Integer> q = 
            new PriorityQueue<>(target.length, Collections.reverseOrder()); 
        
        int sum = 0; 
        for (int x : target) { 
            sum  += x; 
            q.offer(x);
        }
        
        while (q.peek() != 1) { 
            int sumtobe = q.poll();

            int diff = sum - sumtobe;

            if (diff == 1) return true;

            int delta = sumtobe % diff; 
            sum = delta + diff; 
            
            if (delta == 0 || delta == sumtobe) return false; 
            
            q.add(delta);
        }
        
        return true;
    }
}






