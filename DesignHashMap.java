/** 
 *
 * @link https://leetcode.com/problems/design-hashmap
 *
 */

class MyHashMap {
    private static final int[] PRIMES = {
        5, 53, 157, 173, 211, 257, 263, 373, 563, 
        593, 607, 653, 733, 947, 977, 1103, 1123, 
        1187, 1223, 1367, 1511, 1747, 1753, 1907, 
        2287, 2417, 2677, 2903, 2963, 3307, 3313, 
        3637, 3733, 4013, 4409, 4457, 4597, 4657, 
        4691, 4993, 5107, 5113, 5303, 5387, 5393
    }; 
    
    private int size = 0; 
    private int sizeIdx = 0;
    private LinkedList<Node>[] buckets; 
    
    public MyHashMap() {
        init();
    }
    
    private void init() { 
        int length = PRIMES[sizeIdx];
        buckets = new LinkedList[length]; 
        
        for (int i=0; i<length; i++) { 
            buckets[i] = new LinkedList<>(); 
        }
    }
    
    private int hash(Integer key) { 
        return key.hashCode() % buckets.length; 
    }
    
    private int containsKey(int key, int bi) { 
        int di = 0; 
        
        for (Node node : buckets[bi]) { 
            if (node.key == key) return di; 
            di += 1; 
        }
        
        return -1; 
    }
    
    private void rehash() { 
        sizeIdx += 1;
        LinkedList<Node>[] old = buckets;
        init();
        
        for (int i=0; i<old.length; i++) { 
            for (Node node : old[i]) { 
                put(node.key, node.value);
            }
        }
    }
    
    public void put(int key, int value) {
        int bi = hash(key);
        int di = containsKey(key, bi);
        
        if (di == -1) { 
            buckets[bi].addLast(new Node(key, value));
            size += 1; 
        } else { 
            buckets[bi].get(di).value = value; 
        }
        
        double loadFactor = (1.0 * size) / buckets.length; 
        
        if (!(sizeIdx >= PRIMES.length - 1) && loadFactor > 2) rehash(); 
    }
    
    public int get(int key) {
        int bi = hash(key);
        int di = containsKey(key, bi);
        
        if (di == -1) return -1; 
        return buckets[bi].get(di).value; 
    }
    
    public void remove(int key) {
        int bi = hash(key); 
        int di = containsKey(key, bi);
        
        if (di == -1) return; 
        
        size -= 1; 
        buckets[bi].remove(di);
    } 
     
    private final class Node { 
        final int key; 
        int value; 

        public Node(final int key, final int value) { 
            this.key = key; 
            this.value = value; 
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
