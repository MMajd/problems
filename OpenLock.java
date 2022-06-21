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
