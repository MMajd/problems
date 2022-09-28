class DSU {
    private int[] parent;
    private int[] sz; 
    private int components; 
    private int max; 

    private DSU(int size) {
        parent = new int[size];
        sz = new int[size];
        
        Arrays.setAll(parent, i-> {
            sz[i] = 1; 
            return i; 
        });
        
        max = 1; 
        components = size; 
    }

    public int components() {
        return components; 
    }

    public int maxComponentSize() {
        return max;
    }
       
    private int f(int u) {// recursive find with path compression
        if (u != parent[u]) parent[u] = f(parent[u]); 
        return parent[u]; 
    }
    
    public int parent(int u) { 
        return find(u); 
    }
    
    public int find(int u) {
        int root = u, next; 
        
        while(root != parent[root]) root = parent[root]; 
        
        while(u != root) {
            next = parent[u]; 
            parent[u] = root; 
            u = next; 
        }
        
        return root;            
    }
    
    public boolean connected(int u, int v) { 
        return find(u) == find(v);
    }

    public boolean union(int u, int v) {
        int up = find(u);
        int vp = find(v);
        
        if (up == vp) return false; 

        max = Math.max(max, sz[up]+sz[vp]);

        if (sz[up] >= sz[vp]) {
            parent[vp] = up; 
        } 
        else { 
            parent[up] = vp; 
        }

        sz[up] += sz[vp]; 
        sz[vp] = sz[up]; 
        
        components -= 1; 
        return true; 
    }
}
