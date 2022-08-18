/** 
 
  @link https://leetcode.com/problems/median-of-two-sorted-arrays/

  Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

  The overall run time complexity should be O(log (m+n)).

Example 1:

    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
 */ 




class Solution {
    /** 
    1. Goal of the problem is to find the median which by definition is the element 
    that all the elements comes before it are smaller or equal to it and 
    that all the elements comes after it are larger or equla to it 
    
    2. Median is in the mid index, when having an odd sized total array 
    we need to add to its size 1 then dividing by  (x is size of the odd sized array mid=floor((x+1)/2)
    and flooring the result, if we have an even adding 1 and then flooring the division result 
    wont effect the final result ex: 10 -> (11/2) = 5.5 -> floor(5.5)=5 which is the one of the 
    mid points in an even sized array  (mids: 5,6)
    
    3. Searching for the median giving two sorted arrays and time constraint of log only leave us
    with Binary Search or something of the same nature 
    
    4. The main observation to solve this problem is the find a pivot point between the two arrays
    that follows the median definition, meaning we have to find a pivot that all elements to its left 
    are smaller/equal and all elements to its right are greater/equal and in the middle index. 
    Assume  A: x x x x x x  (size: 6)
            B: y y y y      (size: 4) 
            Median of final sorted Array (A&B) = floor((4+6+1)/2) = 5; 
            

    5. To find the pivot point we have to start at the shorted array which is B, finding its pivot idx
    and finding the supposed pivot of A using the two previously calculated values 
    
    * We choose b knowing that A will always have a valid index of media-bPivot as its larger in size
    
    bPivot = bStart + (bEnd - bStart) / 2 , say that bPivot = 1
    aPivot = median - bPivot = 5 - 1 = 4 
    
            A: 1 2 3 4|5 6 
            B:       2|3 4 5 
            
    if the current pivot point  is the media, then  all element to its left are smaller than 
    the elements to its right, as arrays are sorted then we only need to compare 
    the 4-numbers surrounding the pivot: 
        al = 2 , ar = 3 and bl = 2 , br = 3, [l:left, r: right]
    
    then al has to be <= br and lb has to be <= ar for point P to be pivot 
    
    In our case al NOT less than br, thus we need to change our pivot 
    depending on the comparsion we will decied which way to go in our short array B
    as B right to pivot is less the A left to the pivot we need to move the pivot to a larger element 
    in B, as b is sorted we move the pivot to the right in B 
    
    New search area in B for pivot starts from the start=bPivot+1  to the end of B, then
    bPivot = 2 + (4-2)/2 = 2 + 1 = 3, then 
    aPivot = 5 - 3 = 2 
    
    A:   1 2|3 4 5 
    B  2 3 4|5
    
    here 2 <= 5 and 4 NOT less the 3 then we need to move the left in the short array B, search area
    in B end will change to bPivot-1, then bEnd = 2 
    
    bPivot = 2 + (2-2)/2 = 2
    aPivot = 5 -2 = 3
    
    A:  1 2 3|4
    B:    2 3|4 5 

    Here the median is found and we choose 2 values (as our size is even) from the surrounding 4-values 
    from the right part we has to choose the smaller one 
    and from the left we has to choose the bigger one then divide by 2 
    
    3 + 4 / 2 = 3.5
    
    If our size was odd we only choose one point from the elements on the left of the media  
    we choose the larger one 

    EX-         A: 1 2 3|4 5        median point is (4+5+1)/2 = 5 
                 :   2 3|4 5 
                 
    Median is Max of la, lb = Max(3 , 3) = 3 
    
    */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // b has to be the shorted always 
        if (B.length > A.length) {
            int[] t = A; 
            A = B; 
            B = t; 
        }
        
        int m = A.length; 
        int n = B.length; 
        
        int bstart = 0; 
        int bend = n; 

        int median = (n + m + 1) / 2; // java always floor integer division
        int direction = 1; // direction of movement in b if its 0 means we are done, -1 left, 1 right
        
        int lb, la, rb, ra;  // will be initialized latter
        lb = la = rb = ra = 0;  // will be initialized latter
        
        while (direction != 0) {
            int bPivot = bstart + (bend - bstart)/2; 
            int aPivot = median - bPivot; 
            
            lb = getVal(B, bPivot-1);
            la = getVal(A, aPivot-1);
            rb = getVal(B, bPivot);
            ra = getVal(A, aPivot);
            
            direction = getDirection(lb, rb, la, ra);
            
            if (direction > 0) { 
                bstart = bPivot+1; 
            }
            else if (direction < 0) { 
                bend = bPivot-1; 
            }
        }
        

        if ((m+n)%2==0) { 
            return (Math.max(la, lb) + Math.min(ra, rb))/2.0; // float division
        }
        
        return Math.max(lb, la);
    }
    
    private int getVal(int[] arr, int idx) { 
        if (idx < 0) return Integer.MIN_VALUE; 
        if (idx >= arr.length) return Integer.MAX_VALUE; 
        return arr[idx]; 
    }
    
    private int getDirection(int lb, int rb, int la, int ra) { 
        if (la > rb) return 1;  // move to the right
        if (lb > ra) return -1;  // move to the left
        return 0; 
    }
}








