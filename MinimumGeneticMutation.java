/* 
 @link https://leetcode.com/problems/minimum-genetic-mutation/
 @categories (hash-table/bfs/string) 

 A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.


Example 1:
    Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
    Output: 1

Example 2:
    Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
    Output: 2

Example 3:
    Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
    Output: 3
 

Constraints:
    start.length == 8
    end.length == 8
    0 <= bank.length <= 10
    bank[i].length == 8
    start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].

**********************
HINT: Needs two hashtables, one for valid mutations, and one for visited mutaitons
**********************
*/

class Solution {
    private static final char[] charset = {'A', 'C', 'G', 'T'};  
    
    public int minMutation(String start, String end, String[] bank) {

        Deque<String > q = new ArrayDeque<>();
        q.add(start);
        
        Map<String, Integer> valid = new HashMap<>(); 
        
        for (int i=0; i<bank.length; i++) { 
            valid.put(bank[i], 1);
        }
        
        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size(); 
            
            while (size-- > 0) { 
                String gene = q.poll(); 
                
                if (gene.equals(end)) return steps; 
                
                for(int j=0; j<4; j++) {
                    char[] m = gene.toCharArray(); 
                    
                    for (int i=0; i<8; i++) {
                        char t = m[i];
                        m[i] = charset[j];
                        
                        if (valid.getOrDefault(String.valueOf(m), 0) != 0) { 
                            q.add(String.valueOf(m));
                            valid.put(String.valueOf(m), 0);
                        }
                        
                        m[i] = t; 
                    }
                }
            }
            
            steps += 1; 
        }
        
        return -1; 
    }
}
