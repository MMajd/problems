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

    public int minDeletions(String s) {
        int ans = 0; 
        int[] freq = new int[26]; 
        
        for (char c : s.toCharArray()) freq[c-'a'] += 1; 
        
        freq = Arrays.stream(freq)
                .boxed()
                .sorted((a, b) -> b-a)
                .mapToInt(i -> i)
                .toArray();
        
        int maxAllowed = freq[0]; 
        
        for (int i=0; i<26; i++) { 
            if (freq[i] > maxAllowed) { 
                ans += freq[i] - maxAllowed; 
                freq[i] = maxAllowed; 
            }
            maxAllowed = Math.max(0, freq[i]-1);
        }
        
        return ans; 
    }
}
