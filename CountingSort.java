/** 
 *
 * CountingSort with range of positive and negative values;
 *
 * */

class Solution { 
    public void countingSort(int[] arr) { 
        int min = Integer.MAX_VALUE; 
        int max = Integer.MIN_VALUE;
        
        for (int i : arr) { 
            if (min > i) min = i; 
            else if (i > max) max = i; 
        }
        
        int[] output = new int[arr.length];
        int[] count = new int[max-min+1];
        
        for (int i=0; i<arr.length; i++) { 
            count[arr[i]-min] += 1;
        }
        
        for (int i=1; i<count.length; i++) { 
            count[i] += count[i-1];
        }
        
        for (int i=0; i<arr.length; i++) { 
            output[count[arr[i]-min]-1] = arr[i]; 
            count[arr[i]-min]--; 
        }
        
        for (int i=0; i<arr.length; i++) { 
            arr[i] = output[i]; 
        }
    }
}
