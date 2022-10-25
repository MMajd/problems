/* 
 @link https://leetcode.com/problems/set-mismatch/
 @categories (sorting/hash-table/bit-manipulation) 
 
 You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.


Example 1:
    Input: nums = [1,2,2,4]
    Output: [2,3]

Example 2:
    Input: nums = [1,1]
    Output: [1,2]
 
Constraints:
    2 <= nums.length <= 10^4
    1 <= nums[i] <= 10^4


*/ 


class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length; 
        int d = -1; 
        int actual = 0, expected = (n * (n+1))/2;
        
        for (int x : nums) {
            if (nums[Math.abs(x)-1] < 0) {
                d = Math.abs(x);
            }
            else { 
                nums[Math.abs(x)-1] *= -1; 
            }
            
            actual += Math.abs(x);
        }
        
        return new int[]{d , d+(expected-actual)}; 
    }
}

public class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = -1, missing = 1;
        for (int n: nums) {
            if (nums[Math.abs(n) - 1] < 0)
                dup = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0)
                missing = i + 1;
        }
        return new int[]{dup, missing};
    }
}

class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length; 
        int d = -1; 
        int actual = 0, expected = (n * (n+1))/2;
        
        Set<Integer> set = new HashSet<>(); 
        
        for (int x : nums) { 
            if (!set.add(x)) d = x; 
            actual += x; 
        }
        
        return new int[]{d , d+(expected-actual)}; 
    }
}

public class Solution {
    public int[] findErrorNums(int[] nums) {
        Map < Integer, Integer > map = new HashMap();
        int dup = -1, missing = 1;
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(i)) {
                if (map.get(i) == 2)
                    dup = i;
            } else
                missing = i;
        }
        return new int[]{dup, missing};
    }
}


