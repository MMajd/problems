/** 
 *
 *
 */

public DFSTemplate { 
    public boolean DFS(Node curr, Node target, Set<Node> visited) { 
        if (curr.equals(target)) return true; 

        for (Node n : curr.neighbors) { 

            if ( ! visited.contains(n) ) { 
                visited.add(n);

                if ( DFS(n, target, visited) == true ) { 
                    return true 
                }
            }
        }

        return false; 
    }
}

