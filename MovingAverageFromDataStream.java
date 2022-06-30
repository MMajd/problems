/** 
 * @link https://www.lintcode.com/problem/642/
 */ 

public class MovingAverage {
    private int k;
    private double sum = 0;  
    private Queue<Integer> q; 
    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        q = new ArrayDeque<>(size);
        k = size; 
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        sum += val;
        q.add(val); 

        if (q.size() > k) { 
            sum-= q.poll();
        }

        return sum / q.size(); 
    }
}



