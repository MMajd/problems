/**
 

  @link https://leetcode.com/problems/design-linked-list


*/ 


/** Doubly-circular linkedlist */
class MyLinkedList {
    class Node { 
        int val; 
        Node next; 
        Node prev; 
        
        public Node(int v) { 
            val = v; 
            next = prev = null; 
        }
    }; 
    
    private int size; 
    private Node head; 
    private Node tail; 

    public MyLinkedList() {
        head=tail=null; 
    }
    
    public int get(int index) {
        if (index >= size) return -1; 
        
        Node p = null; 
    
        if (index<=(size-index)) { 
            p = head; 
            while(index-->0) p = p.next;
        }
        else { 
            p = tail; 
            index = size-index; 
            while(--index>0) p = p.prev;
        }
        
       return p.val; 
    }
    
    public void addAtHead(int val) {
        Node temp = new Node(val);
        
        size += 1; 
        
        if (head == null) { 
            head = tail = temp;
            head.prev = tail;
            head.next = tail; 
            return; 
        }
        
        temp.next = head; 
        head.prev = temp; 
        temp.prev = tail; 
        tail.next = temp; 
        
        head = temp; 
    }
    
    public void addAtTail(int val) {
        Node temp = new Node(val);
        
        size += 1; 
        
        if (tail==null) { 
            head = tail = temp; 
            head.prev = tail;
            head.next = tail; 
            return; 
        }
        
        tail.next = temp; 
        temp.prev = tail; 
        temp.next = head; 
        head.prev = temp; 
        
        tail = temp; 
    }
    
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        
        if (index == 0) {
            addAtHead(val);
            return; 
        }
        
        if (index == size) { 
            addAtTail(val);
            return;
        }
        
        Node p = null; 
        
        if (index <= (size-index)) { 
             p = head; 
            while(index-->0) p = p.next; 
        } 
        else { 
            p = tail; 
            index = size-index; 
            while(--index>0) p = p.prev; 
        }
        
        size += 1; 
        
        Node temp = new Node(val);
        
        temp.next = p; 
        p.prev.next = temp; 
        temp.prev = p.prev; 
        p.prev = temp; 
    }

    private void deleteAtHead() {
        size -= 1; 
        
        Node p = head; 
        head = head.next; 
        
        p.prev.next=p.next;  
        p.next.prev=p.prev; 
        
        p = null; 
    }
    
    private void deleteAtTail() { 
        size -= 1; 
        
        Node p = tail; 
        tail = tail.prev; 
        
        p.prev.next=p.next;  
        p.next.prev=p.prev; 
        
        p = null; 
    }
    
    public void deleteAtIndex(int index) {
        if (index >= size) return;
        
        if (index == 0) { 
            deleteAtHead();
            return;
        }
        if (index == size-1) {
            deleteAtTail();
            return;
        }
        
        Node p = null; 
        
        if (index <= (size-index)) { 
            p = head;
            while(index-->0) p = p.next;
        } 
        else { 
            p = tail; 
            index = size-index; 
            while(--index>0) p = p.prev; 
        }
        
        size -= 1; 
        p.prev.next=p.next;  
        p.next.prev=p.prev; 
        p = null; 
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

