
/** Just to explain what I mean buy lazy operation functional interface*/

@FunctionalInterface
interface TernaryOperator<A> {
    A apply(A a, A b, A c);

    default <A> TernaryOperator<A> andThen(Function<? super A, ? extends A> after) {
        Objects.requireNonNull(after);
        return (A a, A b, A c) -> after.apply(apply(a, b, c));
    }
}


/** 
This is an implementation to Segment Tree class 
 */

class SegmentTree { 
    private int[] seg, lazy, data; 
    private int length; 

    BinaryOperator<Integer> operation; 
    TriFucntion<Integer> lazyOperation; 


    // opreation, lazy operation area functional interfaces, to get a callback function 
    // the do the required operation what ever it could be (ex: sum, min, max...etc)
    // lazy operation is to do the lazy propogation 
    public SegmentTree(T[] data, BinaryOperator<Integer> operation, TernaryOperator<Integer> lazyOperation) { 
        length = data.length * 4;  // we can suffices with n log n as its already a binary tree
        seg = new int[length]; 
        lazy = new int[lenght];

        this.data = data.clone(); 

        this.operation = operation; 
        this.lazyOperation = lazyOperation; 
        
        build(0, 0, data.length-1); 
    }

    /** 
        Note: almost all segment tree functions will need to know which position we are working on 
        the range we are considering, every range has a start and end point inclusive 
    */
    private void build(int pos, int start, int end) { 
        propogateIfNeeded(pos, start, end); 

        // if we are left with only one element then update the seg tree pos 
        if (start == end) { 
            seg[pos] = data[start]; 
            return;
        }

        int mid = start + (end - start) / 2; 
        build(2*pos, start, mid); 
        build(2*pos+1, mid+1, end); 

 
        seg[pos] = operation(seg[2*pos], seg[2*pos+1]); 
    }

    private void update(int pos, int start, int end, int idx, int val) { 
        propogateIfNeeded(pos, start, end); 

        if (start == end) { 
            seg[pos] = val; 
            return;
        }

        int mid = start + (end-start) / 2; 

        if (mid > pos) update(pos, mid+1, end, idx, val); 
        else update(pos, start, mid, idx, val); 

        seg[pos] = operation(seg[2*pos], seg[2*pos+1]); 
    }

    /*update range, here we use lazy propogation to not go deep in the tree until we really need to do so*/
    private void update(int pos, int start, int end, int updateRangeStart, int updateRangeEnd, int val) { 
        propogateIfNeeded(pos, start, end); 

        /*  meaning that the range given contains the current seg tree range 
                      a, b                          s, e 
            ex: range(3, 8) contains seg tree range(4, 7)
            condition is a<=s && b>= e
            a: updateRangeStart, b: updateRangeEnd, s: start, e: end
        */
        if (start>=updateRangeStart && end<=updateRangeEnd) { 
            // could be adding some number or something like that
            // here lazyOperation need to handle range also
            int rangeLength = end-start+1; 
            seg[pos] = lazyOperation(seg[pos], val, rangeLength); 
            if (start != end) { 
                lazy[2*pos] = lazyOperation(seg[pos], val, 1); 
                lazy[2*pos+1] = lazyOperation(seg[pos], val, 1); 
            }

            return;
        }

        // update range is completely out of this seg tree range
        if (start>=updateRangeEnd || end<=updateRangeStart) return;

        int mid = start + (end-start) / 2; 

        update(pos, start, mid, updateRangeStart, updateRangeEnd, val)
        update(pos, mid+1, end, updateRangeStart, updateRangeEnd, val)

        seg[pos] = operation(seg[2*pos], seg[2*pos+1]);
    }


    private void propogateIfNeeded(int pos, int start, int end) { 
        if (lazy[pos] != 0) { 
            int rangeLength = end-start+1;
            seg[pos] = lazyOperation(seg[pos], lazy[pos], rangeLength); 

            if (start != end) { 
                lazy[2*pos] = lazyOperation(seg[pos], val, 1); 
                lazy[2*pos+1] = lazyOperation(seg[pos], val, 1); 
            }

            lazy[pos] = 0; 
        }
    }

    private int get(int pos, int start, int end, int queryRangeStart, int queryRangeEnd) { 
        propogateIfNeeded(pos, start, end); 

        if (start>=queryRangeStart && end<=queryRangeEnd) { 
            return seg[pos]
        }

        if (start<=queryRangeEnd || end>=queryRangeStart) {
            return 0;  // TODO: change this is value according to problem need
        }

        int mid = start+(end-start)/2; 

        int left = get(pos, start, mid, queryRangeStart, queryRangeEnd); 
        int right = get(pos, mid+1, end, queryRangeStart, queryRangeEnd); 

        return operation(left, right); 
    }
}


