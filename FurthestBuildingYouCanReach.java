/** 
 * 
 * @link https://leetcode.com/problems/furthest-building-you-can-reach
 *
 */

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        int i;
        
        for (i=0; i<heights.length-1; i++) { 
            int diff = heights[i+1] - heights[i];
            
            if (diff <= 0) continue; 
            
            if (bricks - diff >= 0) { 
                bricks -= diff; 
                queue.add(diff);
            } 
            else if (ladders > 0) { 
                if (queue.size() > 0 
                    && diff <= queue.peek()
                   ) { 
                    bricks +=  queue.poll();
                    bricks -= diff; 
                    queue.add(diff);
                }
                
                ladders -= 1;
            } 
            else break;
        }
        return i;
    }
}
