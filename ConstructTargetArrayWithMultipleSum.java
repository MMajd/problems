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
        
        int N = target.length; 
        while (q.peek() != 1) { 
            int sumtobe = q.poll();
            
            if (sum - sumtobe == 1) 
                return true;
            
            int delta = sumtobe % (sum - sumtobe);
            sum = delta + sum - sumtobe;
            
            if (delta == 0 || delta == sumtobe) 
                return false; 
            
            q.add(delta);
        }
        
        return true;
    }
}






