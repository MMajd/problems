/*
 
 @link https://leetcode.com/problems/find-k-closest-elements

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


Explanantion: 

    
lee215's avatar
lee215
157451
Last Edit: April 29, 2020 8:11 AM

68.3K VIEWS

Intuition
The array is sorted.
If we want find the one number closest to x,
we don't have to check one by one.
it's straightforward to use binary research.

Now we want the k closest,
the logic should be similar.


Explanation
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
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int N = arr.length; 
        int start=0, end=N-k; 
        
        while(start<end) { 
            int mid = start+(end-start)/2; 

            if (x - arr[mid] > arr[mid + k] - x) start= mid + 1;
            else end= mid;
        }
        
        return Arrays.stream(arr, start, start+k)
                .boxed()
                .collect(Collectors.toList());
    }
}
