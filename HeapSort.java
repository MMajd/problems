/********************************** 
 *   HEAP SORT IMPLEMENTATION     * 
 *   TIME: O(nlogn) SPACE: O(1)   * 
 **********************************/


public class Solution {
    public int[] sortArray(int[] arr) {
        int n = arr.length; 

        for (int i=n-1/2; i>=0; --i) {
            heapify(arr, i, n);
        }

        for (int i=n-1; i>=0; --i) {
            swap(arr, i, 0);
            heapify(arr, 0, i);
        }

        return arr; 
    }


    private void iterativeHeapify(int[] arr, int i, int len) {
        int idx, left, right; 

        while(i < len) {
            idx = i; 
            left = 2*i + 1; 
            right = 2*i + 2; 

            if (left < len && arr[idx] < arr[left]) idx = left; 
            if (right < len && arr[idx] < arr[right]) idx = right; 

            if (idx == i) break; 

            swap(arr, i, idx);
            i = idx; 
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int idx = i; 
        int left = 2*i+1; 
        int right = 2*i+2; 

        if (left < len && arr[idx] < arr[left]) idx = left; 
        if (right < len && arr[idx] < arr[right]) idx = right; 

        if (idx != i) { 
            swap(arr, i, idx);
            heapify(arr, idx, n);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = t; 
    }
}
