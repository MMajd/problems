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
            
            // if (delta == 0 || sum == sumtobe) return false; // sum after update is the same as sumtobe, meaning no chaning is happing 
            if (delta == 0 || delta == sumtobe) return false; 
            // DELTA == 0, its contradicts our assumption that we can get the array from all ones, 
            // when delta is 0 means we started needed to start w/ some where 
            // DELTA == SUMTOBE, means that we did not change the sum and we will keep looping 
            
            q.add(delta);
        }
        
        return true;
    }
}






