/** 
 *
 * @link https://leetcode.com/problems/maximum-units-on-a-truck/
 *
 */

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            boxTypes.length, (a, b) -> b[1] == a[1] 
            ? a[0] - b[0]
            : b[1] - a[1]
        );
        
        for (int i=0; i<boxTypes.length; i++) { 
            pq.add(boxTypes[i]);
        }
        
        int ans = 0; 
        int boxes = 0; 
        
        while(!pq.isEmpty() && truckSize-boxes>0) { 
            int[] typei = pq.poll();
            if (truckSize - boxes>= typei[0]) { 
                boxes += typei[0]; 
                ans += typei[0] * typei[1]; 
            } else { 
                int temp = truckSize - boxes; 
                boxes += temp; 
                ans += temp * typei[1]; 
            }
        }
        
        return ans; 
    }
}
