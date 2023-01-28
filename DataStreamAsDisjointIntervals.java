/*
 @link https://leetcode.com/problems/data-stream-as-disjoint-intervals
 @categories (design/ordered-set/bst)

 Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.

Implement the SummaryRanges class:
    SummaryRanges() Initializes the object with an empty stream.
    void addNum(int value) Adds the integer value to the stream.
    int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.
     

Example 1:
    Input
    ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
    [[], [1], [], [3], [], [7], [], [2], [], [6], []]
    Output
    [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

    Explanation
    SummaryRanges summaryRanges = new SummaryRanges();
    summaryRanges.addNum(1);      // arr = [1]
    summaryRanges.getIntervals(); // return [[1, 1]]
    summaryRanges.addNum(3);      // arr = [1, 3]
    summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
    summaryRanges.addNum(7);      // arr = [1, 3, 7]
    summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
    summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
    summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
    summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
    summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]

Constraints:
    0 <= value <= 10^4
    At most 3 * 104 calls will be made to addNum and getIntervals.
 

Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
  
*/ 


/**
 * Fast solution 
 * Time: O(log(n))
 * Space: O(n) 
 */ 
class SummaryRanges {
    TreeSet<int[]> set;


    public SummaryRanges() {
        set = new TreeSet<>(SummaryRanges::comparator);
    }

    /** 
     * If Intervals have same startings then sort by their endings, 
     * if not sort by their startings, and endings is guranteed to be different 
     */
    private static int comparator(int[] a, int[] b) { 
        return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]; 
    }
    
    public void addNum(int value) {
        int[] curr = new int[]{value, value};
        // same number exist
        if (set.contains(curr)) return;

        int[] low = set.lower(curr); // lower < curr or null 
        int[] high = set.higher(curr); // hight > curr or null 

        if (mergeable(low, curr) && mergeable(curr, high)) {
            low[1] = high[1]; // merge two intervals
            set.remove(high);
            return; 
        }

        if (mergeable(low, curr)) {
            low[0] = Math.min(low[0], value);
            low[1] = Math.max(low[1], value);
            return;
        }

        if (mergeable(curr, high)) {
            high[0] = Math.min(high[0], value);
            high[1] = Math.max(high[1], value);
            return; 
        } 
        
        set.add(curr);
    }
    
    public int[][] getIntervals() {
        List<int[]> res = new ArrayList<>();
        for (int[] c: set) {
            res.add(c);
        }
        return res.toArray(new int[res.size()][]);
    }

    private boolean mergeable(int[] a, int[] b) {
        if (a == null || b == null) return false; 
        return Math.abs(Math.min(a[1], b[1]) - Math.max(a[0], b[0])) <= 1;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */

/**
 * Quite slow solution 
 * Time: O(n) 
 * Space: O(n)
 */
class SummaryRanges {
    Set<Integer> set = new TreeSet<>();

    public SummaryRanges() {
    }
    
    public void addNum(int value) {
        set.add(value);
    }

    public int[][] getIntervals() {
        List<int[]> ans = new ArrayList<>(set.size());
        List<int[]> intervals = new ArrayList<>(set.size());

        for (int i : set) { 
            intervals.add(new int[] {i, i}); 
        }

        for (int i=0; i<intervals.size(); i++) {
            int[] curr = intervals.get(i);

            while (i < intervals.size() && intersect(curr, intervals.get(i))) {
                curr = join(curr, intervals.get(i));
                i += 1; 
            }

            i -= 1; 
            ans.add(curr);
        }

        return ans.toArray(new int[ans.size()][2]);
    }

    private int[] join(int[] a, int[] b) { 
        return new int[] {Math.min(a[0], b[0]), Math.max(a[1], b[1])}; 
    }

    private boolean intersect(int[] a, int[] b) {
        return Math.abs(Math.min(a[1], b[1]) - Math.max(a[0], b[0])) <= 1;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
