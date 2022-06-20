/** 
 *
 * @link https://leetcode.com/problems/design-circular-queue/
 *
 *
 */ 

class MyCircularQueue {
    int[] queue; 
    int head, tail, size, capacity; 

    public MyCircularQueue(int k) {
        queue = new int[k]; 
        tail = -1;
        capacity = k;
        head = size = 0;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) return false;
        
        tail = (tail+1) % capacity;
        size += 1;
        
        queue[tail] = value; 
        
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) return false;
        
        head = (head+1) % capacity; 
        size -= 1; 
        
        return true; 
    }
    
    public int Front() {
        if (isEmpty()) return -1;
        return queue[head]; 
    }
    
    public int Rear() {
        if (isEmpty()) return -1;
        return queue[tail];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
