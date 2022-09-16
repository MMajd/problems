/*
 @link https://leetcode.com/problems/maximum-swap

 You are given an integer num. 
You can swap two digits at most once to get the maximum valued number.
Return the maximum valued number you can get.


Example 1:
    Input: num = 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.

Example 2:
    Input: num = 9973
    Output: 9973
    Explanation: No swap.

Constraints:
    0 <= num <= 10^8
  
*/

class Solution1 {
    public int maximumSwap(int num) {        
        char [] digits = Integer.toString(num).toCharArray();
        int [] buckets = new int [10];
        
        for (int i =0;i<digits.length;i++) {
            buckets[digits[i]-'0']=i;
        }
        
        for (int i = 0; i< digits.length;i++){            
            for (int k = buckets.length-1;k > digits[i] - '0';k--){                
                if (buckets[k] > i ) {
                    char value = digits[i];
                    digits[i]= digits[buckets[k]];
                    digits[buckets[k]]=value;
                    return Integer.valueOf(new String(digits));
                } 
            }  
        }   
        return num;
    }
}


class Solution2 {
    public int maximumSwap(int num) {
        List<Integer> digits = new ArrayList<>(); 
        
        while (num > 0) { 
            digits.add(num % 10);
            num /= 10; 
        }
        
        for (int i=digits.size()-1; i>0; i--){ 
            int idx = i; 
            
            for (int j=i-1; j>=0; j--) {
                idx = Integer.compare(digits.get(j), digits.get(idx)) >= 0 ? j : idx;
            }
            
            if (Integer.compare(digits.get(i), digits.get(idx))!=0) { 
                Integer temp = digits.get(idx);
                digits.set(idx, digits.get(i));
                digits.set(i, temp);
                
                break;
            }
        }
        
        int ans = 0; 
        
        for (int i=digits.size()-1; i>=0; i--){ 
            ans = ans * 10 + digits.get(i);
        }
        
        return ans; 
    }
}



