/**
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-array
 *
    All what we need is to have pointer to the last valid position, where all elements before it not duplicated and another pointer to keep going through array elements with
    In this solution we have wirteIdx which defines the last valid position to write non-duplicate in it, and i which is the loop variable
    We start looping throught array starting from index 1, and compare each element with its predessor if the array not equal we simply increase the write the ith element in the writeIdx and increase it by one
    Suppose we have an array like this one [0, 0, 1, 1, 2, 3, ...]
    in the first iteration index 1 element = index 0 element = which is 0, we do no thing and go to the second index, when comparing arr[2] with arr[1] the are not equal, so we enter the if condition and write the 2-ed index element in that last write element which is the starting write element = 1, after that we increase the write element, our array now looks like this [0, 1, 1 (write-index-here), 1, 2, 3]
    we go the third element and compare it the previous elment which is 1 = 1, we keep looping and go to the 4th element which is 2 and 2 != 1, thus we write 2 in the last valid write index which is index 2, and after that increase it by 1, after this step our array should look like this [0, 1, 2, 1(write-index), 1, 2, 3, ...]
    We keep repeating these operations till we process all the elements in the array and return the last valid write index

 */
class Solution {
    public int removeDuplicates(int[] arr) {
        int writeIdx = 1; 
        
        for (int i=1; i<arr.length; i++) { 
            if (arr[i] != arr[i-1]) { 
                arr[writeIdx++] =  arr[i]; 
            }
        }
        
        return writeIdx; 
    }
}

