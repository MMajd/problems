
/** 
 *
 *
 */ 

/** This is beacase if we perform n push operation time complexity is around n!, not amortized 
 *
 * Amortized solution is below this one 
 *
 * */
class MyQueue {
    Stack<Integer> s1; 
    Stack<Integer> s2; 
    
    public MyQueue() {
        s1 = new Stack<>();      
        s2 = new Stack<>();      
    }
    
    public void push(int x) {
        if (s2.isEmpty()) { 
            s2.push(x);
            return;
        }
        
        while(!s2.isEmpty())  { 
            s1.push(s2.pop());
        }
        
        s1.push(x);
        
        while(!s1.isEmpty())  { 
            s2.push(s1.pop());
        }
        
    }
    
    public int pop() {
        return s2.pop();
    }
    
    public int peek() {
        return s2.peek();
    }
    
    public boolean empty() {
        return s2.isEmpty();
    }
}


class MyQueue {
    Stack<Integer> s1; 
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        if(s1.isEmpty()){
            while(s2.isEmpty() == false){
                s1.push(s2.pop());
            }
            s1.push(x);
        }
        else{
            s1.push(x);
        }
    }
    
    public int pop() {
        if(s1.isEmpty()){
            return s2.pop();
        }
        else{
            while(s1.isEmpty() == false){
                s2.push(s1.pop());
            }
            
            return s2.pop();
        }
    }
    
    public int peek() {
        if(s1.isEmpty()){
            return s2.peek();
        }
        else{
            while(s1.isEmpty() == false){
                s2.push(s1.pop());
            }
            
            return s2.peek();
        }
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}



/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
