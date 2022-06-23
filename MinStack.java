/** 
 * @link leetcode.com/problems/min-stack
 */

/** LinkedList based solution */

final class Node { 
    int val; 
    int min; 
    Node next; 
    
    public Node(int val, int min) {
        this.val = val; 
        this.min = min;
        next = null; 
    }
}

class MinStack {
    int top;
    Node head; 
    
    public MinStack() {
        top = -1; 
        head = null;
    }
    
    public void push(int val) {
        if (isEmpty()) { 
            head = new Node(val, val);
        }
        else { 
            Node n = new Node(val, Math.min(val, head.min));
            n.next = head; 
            head = n; 
        }
        top += 1; 
    }
    
    public void pop() {
        if (isEmpty()) return; 
        
        Node temp = head; 
        head = temp.next; 
        temp = null;  
        top -= 1; 
    }
    
    public int top() {
        if (isEmpty()) return -1;
        return head.val;
    }
    
    public int getMin() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return head.min;
    }
    
    public int size()  { return top + 1; }
    public boolean isEmpty() { return size() == 0; }
}


/** ArrayList based solution */
class MinStack {
    int top; 
    ArrayList<int[]> arr;
    
    public MinStack() {
        arr = new ArrayList<>();
        top = -1; 
    }
    
    public void push(int val) {
        top += 1;
        if (top == 0) { 
            arr.add(top, new int[]{val, val});
        }
        else { 
            int[] topnode = arr.get(top-1);
            arr.add(top, new int[]{val, topnode[1] > val? val : topnode[1]});
        }
    }
    
    public void pop() {
        arr.remove(top);
        top -= 1; 
    }
    
    public int top() {
        return arr.get(top)[0];
    }
    
    public int getMin() {
        return arr.get(top)[1];
    }
}
