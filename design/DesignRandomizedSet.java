/*
 @link https://leetcode.com/problems/insert-delete-getrandom-o1
 @categories (design/hash-table/mapping/randomize)

 Implement the RandomizedSet class:
RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.


Example 1:
    Input
    ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
    [[], [1], [2], [2], [], [1], [2], []]
    Output
    [null, true, false, true, 2, true, false, 2]

    Explanation
    RandomizedSet randomizedSet = new RandomizedSet();
    randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
    randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
    randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
    randomizedSet.insert(2); // 2 was already in the set, so return false.
    randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

Constraints:
    -231 <= val <= 231 - 1
    At most 2 * 105 calls will be made to insert, remove, and getRandom.
    There will be at least one element in the data structure when getRandom is called.

*/

/** 
 * More efficient solution below 
 */

class RandomizedSet {
    final List<Integer> nums; 
    final Map<Integer, Integer> map; 
    final Random rand; 


    public RandomizedSet() {
        nums = new ArrayList<>(); 
        map = new HashMap<>(); 
        rand = new Random(); 
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) return false; 
        map.put(val, nums.size());
        nums.add(val);
        return true; 
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false; 
        int idx = map.get(val);  
        int size = nums.size(); 

        if (idx < size-1) { 
            int lastone = nums.get(size-1);
            nums.set(idx, lastone); 
            map.put(lastone, idx);
        }

        map.remove(val);
        nums.remove(size-1);
        return true;
    }
    
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}


class RandomizedSet {

    private Node[] buckets;
    private int size = 0;
    private Node[] idxCache;
    private static Random random = new Random();

    public RandomizedSet() {
        this(128 * 64);
    }

    private RandomizedSet(int bucketsCount) {
        this.buckets = new Node[bucketsCount];
        this.idxCache = new Node[bucketsCount];
    }

    private void rebalance() {
        RandomizedSet rebalanced = new RandomizedSet(buckets.length * 2);
        for (int i = 0; i < buckets.length; i++) {
            Node node = buckets[i];
            while (node != null) {
                rebalanced.insert(node.val);
                node = node.next;
            }
        }

        this.buckets = rebalanced.buckets;
        this.idxCache = rebalanced.idxCache;
    }
    
    public boolean insert(int val) {
        int idx = val & (buckets.length - 1);
        Node node = buckets[idx];
        if (node == null) {
            buckets[idx] = new Node(val, size);
            idxCache[size] = buckets[idx];
        } else {
            Node prev = null;
            while (node != null && node.val != val) {
                prev = node;
                node = node.next;
            }

            if (node == null) {
                prev.next = new Node(val, size);
                idxCache[size] = prev.next;
            } else {
                return false;
            }
        }

        size++;
        if (size >= buckets.length * 0.75) {
            rebalance();
        }

        return true;
    }
    
    public boolean remove(int val) {
        int idx = val & (buckets.length - 1);
        Node node = buckets[idx];
        int removedIdx;
        if (node == null) {
            return false;
        } else {
            Node prev = null;
            while (node != null && node.val != val) {
                prev = node;
                node = node.next;
            }

            if (node == null) {
                return false;
            } else {
                removedIdx = node.insertedAt;

                if (prev != null) {
                    prev.next = node.next;
                } else {
                    buckets[idx] = node.next;
                }
            }
        }

        size--;
        idxCache[removedIdx] = idxCache[size];
        idxCache[removedIdx].insertedAt = removedIdx;
        return true;
    }
    
    public int getRandom() {
        return idxCache[random.nextInt(size)].val;
    }
}

class Node {
    Node next;
    int val;
    int insertedAt;

    Node(int val, int insertedAt) {
        this.val = val;
        this.insertedAt = insertedAt;
    }
}


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
