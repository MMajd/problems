/**
  @link https://leetcode.com/problems/height-checker/

  A school is trying to take an annual photo of all the students. 
  The students are asked to stand in a single file line in non-decreasing order by height. 
  Let this ordering be represented by the integer array expected 
  where expected[i] is the expected height of the ith student in line.

 You are given an integer array heights representing the current order 
 that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).

 Return the number of indices where heights[i] != expected[i].

 Example 1:

 Input: heights = [1,1,4,2,1,3]
 Output: 3
 Explanation: 
    heights:  [1,1,4,2,1,3]
    expected: [1,1,1,2,3,4]
    Indices 2, 4, and 5 do not match.


Constraints:
    1 <= heights.length <= 100
    1 <= heights[i] <= 100       # REASON TO USE COUNTING SORT 
*/

/** Closed range counting sort */
class Solution {
    public int heightChecker(int[] h) {
        int min = Arrays.stream(h).min().getAsInt(); 
        int max = Arrays.stream(h).max().getAsInt();
        
        int[] count = new int[max-min+1]; 
        int[] output = new int[h.length];
        
        int diff = 0; 
        
        for (int i=0; i<h.length; i++)
            count[h[i]-min] += 1; 
        
        for (int i=1; i<count.length; i++) 
            count[i] += count[i-1]; 
        
        for (int i=0; i<h.length; i++) { 
            output[count[h[i]-min] - 1] = h[i];
            count[h[i]-min]--;
        }
        
        for (int i=0; i<h.length; i++) 
            diff += h[i] != output[i] ? 1 : 0;
        
        return diff; 
    }
}


class Solution {
    public int heightChecker(int[] h) {
        int max = Arrays.stream(h).max().getAsInt();
        
        int[] count = new int[max+1]; 
        int[] output = new int[h.length];
        
        int diff = 0; 
        
        for (int i=0; i<h.length; i++)
            count[h[i]] += 1; 
        
        for (int i=1; i<count.length; i++) 
            count[i] += count[i-1]; 
        
        for (int i=0; i<h.length; i++) { 
            output[count[h[i]] - 1] = h[i];
            count[h[i]]--;
        }
        
        for (int i=0; i<h.length; i++) 
            diff += h[i] != output[i] ? 1 : 0;
        
        return diff; 
    }
}
