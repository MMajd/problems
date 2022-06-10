/**
 * 
 * Read the problem statement first 
 *
 * solution approach can be considered a sliding window of size 2 
 *
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/ 
 *
 **/

public class RemoveDuplicatesFromSortedArrayII { 
    public int removeDuplicates(int[] A) {
        int i = 2;
        for (int j = 2; j < A.length; j++)
            if (A[j] != A[i-2])
                A[i++] = A[j];
        return i;
    }
}
