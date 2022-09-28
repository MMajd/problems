class DSU {
    private int[] parent;
    private int[] sz; 
    private int sets; 

    private DSU(int size) {
        parent = new int[size];
        sz = new int[size];
        
        Arrays.setAll(parent, i-> {
            sz[i] = 1; 
            return i; 
        });
        
        sets = size; 
    }

       
    // recursive find with path compression
    private int __find(int x) { 
        if (x != parent[x]) { 
            parent[x]= __find(parent[x]); 
            return parent[x]; 
        }
        return x; 
    }
    
    public int parent(int x) { 
        return find(x); 
    }
    
    public int find(int x) {
        int root = x, next; 
        
        while(root != parent[root]) root = parent[root]; 
        
        while(x != root) {
            next = parent[x]; 
            parent[x] = root; 
            x = next; 
        }
        
        return root;            
    }
    
    public boolean connected(int x, int y) { 
        return find(x) == find(y);
    }

    public boolean union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        
        if (xroot == yroot) return false; 

        if (sz[xroot] >= sz[yroot]) {
            parent[yroot] = xroot; 
            sz[xroot] += sz[yroot]; 
        } 
        else { 
            parent[xroot] = yroot; 
            sz[yroot] += sz[xroot]; 
        }
        
        sets -= 1; 
        return true; 
    }
}
