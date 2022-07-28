class Solution {
	public void quickSort(int[] arr) {
		qsort(arr, 0, arr.length-1); 
	}
	
	private void qsort(int[] arr, int left, int right) { 
		if (left >= right) return; 
        
        int p = partition(arr, left, right); 
        
		qsort(arr, left, p-1); 
		qsort(arr, p, right); 
	}
    
    private int partition(int[] arr, int left, int right) {
        int pivot = randomize(arr, left, right);
        
        while (left <= right) {
            while (arr[left] < pivot) left+=1; 
            while (arr[right] > pivot) right-=1; 
            
            if (left <= right) {
                swap(arr, left++, right--);
            }
        }
        return left;
    }
    
    private int randomize(int[] arr, int left, int right) {
        int pidx = left+(right-left)/2;
        return arr[pidx];
    }
    
    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}
