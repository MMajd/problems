

/** 
 *
 **/ 
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        return bfs(rooms);
    }

    private boolean bfs(List<List<Integer>> rooms) { 
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>(rooms.size());

        q.add(0);
        visited.add(0);
        
        while(!q.isEmpty()) {
            int sz = q.size(); 
            
            for (int j=0; j<sz; j++) { 
                int curr = q.poll();
                
                for (int i : rooms.get(curr)) {
                    if (!visited.contains(i)) { 
                        visited.add(i);
                        q.add(i);
                    }
                }
            }
        }
        
        return visited.size() == rooms.size();
    }

    private boolean dfsStack(List<List<Integer>> rooms) {
        Stack<Integer> stack = new Stack<>(); 
        Set<Integer> visited = new HashSet<>();
        
        stack.add(0);
        visited.add(0);
        
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            
            for (int i : rooms.get(curr)) { 
                if (!visited.contains(i)) { 
                    visited.add(i);
                    stack.push(i);
                }
            }
        }
        
        return visited.size() == rooms.size();
    }
    
    private void dfsRecursive(int i, List<List<Integer>> rooms, Set<Integer> visisted) {
        if(visisted.contains(i)) return;
        visisted.add(i);
        for(int room : rooms.get(i)) {
            dfsRecursive(room, rooms, visisted);
        }
    }
}
