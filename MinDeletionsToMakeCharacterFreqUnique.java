/** 
 *
 * @link https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 *
 */ 

class Solution {
    public int minDeletions(String s) {
        int ans = 0; 
        int [] freq = new int[26]; 
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); 
        
        for (char c : s.toCharArray()) freq[c-'a'] += 1; 
        
        for (int i : freq) if (i > 0) q.add(i);
        
        while(q.size() > 1)  { 
            int head = q.poll(); 
            if (head == q.peek()) { 
                if (head > 1) { 
                    q.add(head-1); 
                }
                ans += 1; 
            }
        }
        
        return ans; 
    }
}
