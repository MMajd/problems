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
        int lastWrite = 2;
        for (int i = 2; i < A.length; i++)
            if (A[i] != A[lastWrite-2])
                A[lastWrite++] = A[i];
        return lastWrite;
    }
}
