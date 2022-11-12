/*
 @link https://leetcode.com/problems/find-median-from-data-stream/
 @categories (heap/ordered-set[tree-set]/two-pointers/sorting) 
 
 The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 
Example 1:
    Input
    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
    [[], [1], [2], [], [3], []]
    Output
    [null, null, null, 1.5, null, 2.0]

    Explanation
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
    medianFinder.addNum(3);    // arr[1, 2, 3]
    medianFinder.findMedian(); // return 2.0
 
Constraints:
    -105 <= num <= 10^5
    There will be at least one element in the data structure before calling findMedian.
    At most 5 * 10^4 calls will be made to addNum and findMedian.
 

Follow up:
    - If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    - If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

*/

/** 
 * Two Heaps Solution 
 * TreeSet Solution 
 * TreeMap S0lution, Gives TLE
 */
class MedianFinder {
    PriorityQueue<Integer> p, q; 

    public MedianFinder() {
        p = new PriorityQueue<>(Collections.reverseOrder());
        q = new PriorityQueue<>();
    }

    public void addNum(int n) {
        if (p.isEmpty() || n <= p.peek()) p.add(n);
        else q.add(n);

        if (p.size() - q.size() > 1) { 
            q.add(p.poll());
        }
        else if (q.size() - p.size() > 1) { 
            p.add(q.poll());
        }
    }
    public double findMedian() {
        if (p.isEmpty()) return q.peek();
        else if (q.isEmpty()) return p.peek();
        else if (p.size() > q.size()) return p.peek(); 
        else if (p.size() < q.size()) return q.peek(); 
        return (p.peek()+q.peek())/2.0; 
    }
}

class MedianFinder1 {
    private class TupleComparator implements Comparator<int[]>{
        public int compare(int[] a, int[] b) {
            return a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]; 
        }
    }

	int cnt = 0;

	int[] a;
	int[] b;
	TreeSet<int[]> tree;
	TupleComparator cmp;

	public MedianFinder1() {
        cmp = new TupleComparator();
        tree = new TreeSet<>(cmp);
    }
	
    public void addNum(int num) {
    	int[] item = {num, cnt++};
        tree.add(item);

        if (cnt == 1) {
        	a = tree.first();
        	b = tree.first();
        }
        else if (a == b) { // even size, 2-mid elements
        	if (cmp.compare(item, a)>=0) {
        		a = b;
        		b = tree.higher(b);
        	}
        	else {
        		b = a;
        		a = tree.lower(a);
        	}
        }
        else { // odd size, 1-mid element 
        	if (cmp.compare(item, b)>0) {
        		a = b;
        	}
        	else if (cmp.compare(item, a)<0) {
        		b = a;
        	}
        	else {
        		a = item;
        		b = item;
        	}
        }
    }
    
    public double findMedian() {
        return (a[0]+b[0])/2.0; // divide by double 
    }
}
 

// TLE 
class MedianFinder2 {
    int size = 0; 
    TreeMap<Integer, Integer> map; 

    public MedianFinder2() {
        map = new TreeMap<>(); 
    }
    
    public void addNum(int x) {
        map.compute(x, (k, v) -> v==null? 1 : ++v);
        size += 1; 
    }
    
    public double findMedian() {
        int cnt = 0, prev = 0;
        int mp = (size+1)/2;
        mp += size%2==0?1:0; 

        for (Map.Entry<Integer, Integer> e : map.entrySet()) { 
            int k = e.getKey(); 
            int v = e.getValue(); 
            cnt += v; 

            if (cnt >= mp) { 
                if (size % 2 == 1) return k; 
                if (mp-1 > cnt-v) return k; 
                return (prev+k)/2.0; 
            }

            prev = k; 
        }

        return Integer.MAX_VALUE; 
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
