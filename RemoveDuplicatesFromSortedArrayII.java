/**
 * 
 * Read the problem statement first 
 *
 * 1st Solution approach can be considered a sliding window of size 2 
 *
 * 2nd Solution approach is generalization from the same proble with duplicates larger than 1 
 *
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/ 
 *
 **/

public class RemoveDuplicatesFromSortedArrayII { 

    /** Generalization method 1, sliding window */ 

    public int removeDuplicates(int[] A, int k) {
        int lastWrite = k

        for (int i=k; i<A.length; i++) {
            if (A[lastwrite - k] != A[i]) {
                A[lastWrite++] = A[i];
            }
        }

        return lastWrite;
    }

    /** 
     * Generalization method 2, counting */ 
     */
    int removeDuplicates(int A[], int n, int k) {
        if (n <= k) return n;

        int i = 1, j = 1;
        int cnt = 1;
        while (j < n) {
            if (A[j] != A[j-1]) {
                cnt = 1;
                A[i++] = A[j];
            }
            else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
    }
}
