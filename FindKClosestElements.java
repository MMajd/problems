/*
 
 @link https://leetcode.com/problems/find-k-closest-elements
 @categories (binary-search/two-pointers/priority-queue)
 Given a sorted integer array arr, two integers k and x, 
return the k closest integers to x in the array. 
The result should also be sorted in ascending order.

 An integer a is closer to x than an integer b if:

 |a - x| < |b - x|, or
 |a - x| == |b - x| and a < b

Example 1:
    Input: arr = [1,2,3,4,5], k = 4, x = 3
    Output: [1,2,3,4]

Example 2:
    Input: arr = [1,2,3,4,5], k = 4, x = -1
    Output: [1,2,3,4]
 
Constraints:
    1 <= k <= arr.length
    1 <= arr.length <= 10^4
    arr is sorted in ascending order.
    -10^4 <= arr[i], x <= 10^4

*************************************************
Intuition: 
    The array is sorted.
    If we want find the one number closest to x,
    we don't have to check one by one.
    it's straightforward to use binary research.

    Now we want the k closest,
    the logic should be similar.


Detailed Explanation
    Assume we are taking A[i] ~ A[i + k -1].
    We can binary research i
    We compare the distance between x - A[mid] and A[mid + k] - x

    Assume A[mid] ~ A[mid + k] is sliding window

    case 1: x - A[mid] < A[mid + k] - x, need to move window go left
    -------x----A[mid]-----------------A[mid + k]----------

    case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
    -------A[mid]----x-----------------A[mid + k]----------

    case 3: x - A[mid] > A[mid + k] - x, need to move window go right
    -------A[mid]------------------x---A[mid + k]----------

    case 4: x - A[mid] > A[mid + k] - x, need to move window go right
    -------A[mid]---------------------A[mid + k]----x------

    If x - A[mid] > A[mid + k] - x,
    it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1],
    and we have mid smaller than the right i.
    So assign left = mid + 1.

    IMPORTANT
    NOTE THAT, YOU SHOULD NOT COMPARE THE ABSOLUTE VALUE OF abs(x - A[mid]) AND abs(A[mid + k] - x).
    IT FAILS AT CASES LIKE A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3

*/

class Solution {

    public List<Integer> findClosestElements1(int[] A, int k, int x) {
        int n = A.length;
        int s=0, e=n-k; 
        
        while (s < e) {
            int m = s+(e-s)/2; 
            if (x-A[m] > A[m+k]-x) s = m+1; 
            else e = m; 
        }
        
        return Arrays
            .stream(Arrays.copyOfRange(A, s, s+k))
            .boxed()
            .collect(Collectors.toList());
    }
    
    public List<Integer> findClosestElements2(int[] A, int k, int x) {
        int n = A.length;
        int s=0, e=n-1; 
        
        while((e-s)>=k) { 
            int a = x-A[s];
            int b = A[e]-x;
            
            if (a > b) s += 1; 
            else e -= 1; 
        }
        return Arrays
            .stream(Arrays.copyOfRange(A, s, s+k))
            .boxed()
            .collect(Collectors.toList());
    }
    
    public List<Integer> findClosestElements3(int[] A, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : A) {
            if (pq.size() < k) {
                pq.offer(i);
            } 
            else if (x-pq.peek() > i-x) {
                pq.poll();
                pq.offer(i);
            }
        }
        
        List<Integer> ans = new ArrayList<>(pq.size());
        while (!pq.isEmpty()) ans.add(pq.poll());
        return ans;
    }
}
