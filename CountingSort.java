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
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        
        int[] output = new int[arr.length];
        int[] count = new int[max-min+1];
        
        for (int x : arr) { 
            count[x-min] += 1;
        }
        
        for (int i=1; i<count.length; i++) { 
            count[i] += count[i-1];
        }
        
        for (int x : arr) { 
            output[count[x-min]-1] = x;
            count[x-min]--; 
        }
        
        for (int i=0; i<arr.length; i++) { 
            arr[i] = output[i]; 
        }
    }
}
