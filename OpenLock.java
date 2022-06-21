/** 
 *
 * @link https://leetcode.com/problems/open-the-lock/
 *
 */ 
class OpenLock {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>(); 
        Set<String> visited = new HashSet<>();
        Set<String> deset = new HashSet<>(Arrays.asList(deadends));  
        
        if (deset.contains("0000")) return -1;
        
        String start = "0000";
        int steps = 0; 
        
        queue.add(start);
        
        while(!queue.isEmpty()) { 
            int size = queue.size();
            
            while((size--) > 0) { 
                String curr = queue.poll(); 
                
                if (deset.contains(curr)) continue; 
                if (curr.equals(target)) return steps; 
                
                for (int i=0; i<4; i++) { 
                    char c = curr.charAt(i);
                    
                    // a trick to deal w/ integers 
                    // (x + i)      % 10  -> used when adding to keep numbers in range of 0 to 9
                    // (x + 10 - i) % 10  -> used when substracting to keep numbers in range of 0 to 9
                    String increaseslot = curr.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + curr.substring(i + 1);
                    String decreaseslot = curr.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + curr.substring(i + 1);

                    
                    if (!visited.contains(increaseslot)) { 
                        queue.add(increaseslot);
                        visited.add(increaseslot);
                    }
                    
                    if (!visited.contains(decreaseslot)) { 
                        queue.add(decreaseslot);
                        visited.add(decreaseslot);
                    }
                }
            }
            
            steps += 1; 
        }
        
        return -1; 
    }
}
