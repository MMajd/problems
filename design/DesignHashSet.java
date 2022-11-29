/**
 *
 * @link https://leetcode.com/problems/design-hashset/
 *
 */ 

class MyHashSet {
    private int capcity = 0; 
    private static final int FULL_CAP = 4091;  // FULL CAP
    // the use of primes decrease the chance of collision
    
    LinkedList<Integer>[] buckets; 

    public MyHashSet() {
        buckets = new LinkedList[FULL_CAP];
    }
    
    public void add(int key) {
        if (contains(key)) return; 
        
        int index = hash(key);
        
        if (buckets[index] == null) { 
            LinkedList<Integer> b = new LinkedList<>(); 
            b.add(key);
            
            buckets[index] = b; 
        } 
        else { 
            LinkedList<Integer> b = buckets[index]; 
            b.addLast(key);
        }
        
        capcity += 1; 
    }
    
    public void remove(int key) {
        int index = hash(key);
        if (buckets[index] == null) return; 
        buckets[index].removeFirstOccurrence(key);
    }
    
    private int hash(int key) {
        return key % FULL_CAP; 
    }
    
    public boolean contains(int key) {
        int index = hash(key); 
        if (buckets[index] ==  null) return false; 
        LinkedList<Integer> b = buckets[index]; 
        return b.contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
