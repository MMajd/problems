/** 
 *
 * Segment Tree implementation in form of 
 * Range Sum Query
 *
 */ 

class NumArray {
    private int[] seg, arr; 
    private int n; 

    public NumArray(int[] nums) {
        n = nums.length; 
        seg = new int[4*n]; arr = nums.clone(); 
        build(1, 0, n-1); }

    private void build(int pos, int start, int end) { 
        if (start == end) { 
            seg[pos] = arr[start]; return; 
        }
        
        int mid = start+(end-start)/2; 
        
        build(2*pos, start, mid); 
        build(2*pos+1, mid+1, end); 
        
        seg[pos] = seg[2*pos] + seg[2*pos+1]; 
    }
    
    private void updateHelper(int pos, int start, int end, 
                              int idx, int val) { 
        
        if (start == end) { 
            seg[pos] = val; return; 
        }
        
        int mid = start+(end-start)>>1;
        
        if (idx>mid) updateHelper(2*pos+1, mid+1, end, idx, val); 
        else updateHelper(2*pos, start, mid, idx, val); 
        
        seg[pos] = seg[2*pos] + seg[2*pos+1]; 
    }
    
    public void update(int index, int val) {
        updateHelper(1, 0, n-1, index, val); 
    }
    
    
    public int get(int pos, int start, int end, int left, int right) { 
        if (start>=left && end<=right) { 
            return seg[pos]; 
        }
        
        if (start>right || end<left) return 0; 
        
        int mid = start+(end-start)/2; 
        int l = get(2*pos, start, mid, left, right); 
        int r = get(2*pos+1, mid+1, end, left, right); 
        
        return l+r; 
    }
    
    public int sumRange(int left, int right) {
        return get(1, 0, n-1, left, right); 
    }
}


class NumArray {
    int[] tree;
    int treelen; 
    int datalen; 
    int n; 
    int h; 

    public NumArray(int[] nums) {
        datalen = nums.length;
        h = (int)Math.ceil((Math.log(datalen)/Math.log(2))); 
        n = 1<<h; 
        treelen = (n<<1); // complete binary tree height (1 << (h+1))
        tree = new int[treelen];
        build(nums);
    }

    private void build(int[] data) {
        for (int i=0; i<datalen; i++) {
            tree[i+n] = data[i]; 
        }
        
        for (int i=n-1; i>=1; --i) { 
            tree[i] = tree[i<<1] + tree[(i<<1)+1];
        }
    }

    public void update(int idx, int val) {
        tree[idx+n] = val; 
        
        for (int i=(n+idx)>>1; i>=1; i=i>>1) { 
            tree[i] = tree[i<<1] + tree[(i<<1)+1];
        }
    }

    private int execQuery(int pos, int low, int high, int qrylow, int qryhigh) {
        if(low >= qrylow && high<=qryhigh)
            return tree[pos];

        if(high<qrylow || low>qryhigh)
            return 0;

        int mid = low + ((high - low)>>1);  
        int l = execQuery(pos<<1, low, mid, qrylow, qryhigh);
        int r = execQuery((pos<<1)+ 1, mid + 1, high, qrylow, qryhigh);
        
        return l+r;
    }

    public int sumRange(int qrylow, int qryhigh) {
        return execQuery(1, 0, n-1, qrylow, qryhigh);
    }
}

