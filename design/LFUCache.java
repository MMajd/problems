/*
 @link https://leetcode.com/problems/lfu-cache/
 @categories (design/hash-table/linked-list/sets)

 Design and implement a data structure for a Least Frequently Used (LFU) cache.

 Implement the LFUCache class:
LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

-NOTE: The functions get and put must each run in O(1) average time complexity.

Example 1:
    Input
    ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
    Output
    [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

    Explanation
    // cnt(x) = the use counter for key x
    // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
    LFUCache lfu = new LFUCache(2);
    lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
    lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
    lfu.get(1);      // return 1
                     // cache=[1,2], cnt(2)=1, cnt(1)=2
    lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                     // cache=[3,1], cnt(3)=1, cnt(1)=2
    lfu.get(2);      // return -1 (not found)
    lfu.get(3);      // return 3
                     // cache=[3,1], cnt(3)=2, cnt(1)=2
    lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                     // cache=[4,3], cnt(4)=1, cnt(3)=2
    lfu.get(1);      // return -1 (not found)
    lfu.get(3);      // return 3
                     // cache=[3,4], cnt(4)=1, cnt(3)=3
    lfu.get(4);      // return 4
                     // cache=[4,3], cnt(4)=2, cnt(3)=3

Constraints:
    0 <= capacity <= 10^4
    0 <= key <= 10^5
    0 <= value <= 10^9
    At most 2 * 10^5 calls will be made to get and put.
*/
class LFUCache {
    private int minf; // min freq till operation x  
    private int capacity; // constant capacity 
    private Map<Integer, Pair<Integer, Integer>> cache; //key, value and it's freq
    private Map<Integer, LinkedHashSet<Integer>> frequencies; // freq, set of values in that freq
    
    private void insert(int key, int frequency, int value) {
        cache.put(key, new Pair<>(frequency, value));
        frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
        frequencies.get(frequency).add(key);
    }

    public LFUCache(int capacity) {
        minf = 0;
        this.capacity = capacity;
        cache = new HashMap<>(capacity * 2);
        frequencies = new HashMap<>();
    }
    
    public int get(int key) {
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue == null) {
            return -1;
        }
        final int frequency = frequencyAndValue.getKey();
        final Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (minf == frequency && keys.isEmpty()) { 
            // if curr freq = min freq and it's keyset is empty then update minf to next valid freq
            ++minf;
        }
        final int value = frequencyAndValue.getValue();
        insert(key, frequency + 1, value);
        return value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return; 
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue != null) { // key already exist in cache 
            cache.put(key, new Pair<>(frequencyAndValue.getKey(), value)); // udpate its value
            get(key); //update its frquence 
            return;
        }
        if (capacity == cache.size()) {
            final Set<Integer> keys = frequencies.get(minf); // get keyset of min freq
            final int keyToDelete = keys.iterator().next(); // get first inserted item [key] in it lru
            cache.remove(keyToDelete); // remove it from cache 
            keys.remove(keyToDelete); // remove it from keyset
        }
        minf = 1; // set min freq 1 
        insert(key, 1, value); // insert key-value pair 
    }
}
