/* 
  
 @link https://leetcode.com/problems/create-maximum-number/
 
 @link https://www.youtube.com/watch?v=l3f1NlmAdL0&list=PLzQkb09g99PwljZ7MToXb8gYmBSEsnY9c&index=70
 
 @link https://www.youtube.com/watch?v=l3f1NlmAdL0&list=PLzQkb09g99PwljZ7MToXb8gYmBSEsnY9c&index=71
  
 You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.

Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.

Return an array of the k digits representing the answer.


Example 1:
    Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
    Output: [9,8,6,5,3]

Example 2:
    Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
    Output: [6,7,6,0,4]

Example 3:
    Input: nums1 = [3,9], nums2 = [8,9], k = 3
    Output: [9,8,9]

Constraints:
    m == nums1.length
    n == nums2.length
    1 <= m, n <= 500
    0 <= nums1[i], nums2[i] <= 9
    1 <= k <= m + n
 */





class Solution {
    public int[] maxNumber(int[] n1, int[] n2, int k) {
        int[] ans = new int[k]; 
        
        for (int i=0; i<=k; i++) {
            int j = k-i; 
            
            if (i >n1.length || j >n2.length) continue; 
            
            int[] m1 = maxLen(n1, i); 
            int[] m2 = maxLen(n2, j); 
            
            int[] res = new int[k]; 
            
            int a = 0, b = 0, c = 0; 
            
            while (a<m1.length || b<m2.length) {
                if (a >= m1.length) res[c++] = m2[b++]; 
                else if (b >= m2.length) res[c++] = m1[a++];
                else if (m1[a] < m2[b]) res[c++] = m2[b++]; 
                else if (m1[a] > m2[b]) res[c++] = m1[a++]; 
                else { 
                    if (greater(m1, m2, a, b)) res[c++] = m1[a++]; 
                    else res[c++] = m2[b++]; 
                }
            }
            
            ans = greater(res, ans, 0, 0) ? res : ans; 
            
        }
        
        return ans; 
    }
    
    private int[] maxLen (int[] nums, int k) { 
        int n = nums.length; 
        
        Deque<Integer> stack = new ArrayDeque(k);
        
        for (int i=0; i<n; i++) { 
            while (!stack.isEmpty()
                   && nums[i] > stack.peek()
                   && k-stack.size() < n-i) stack.pop();
            
            if (stack.size() < k) stack.push(nums[i]);
        }
        
        int[] ans = new int[k]; 
        
        for (int i=k-1; i>=0; i--) ans[i] = stack.pop();
        
        return ans; 
    }
    
    private boolean greater(int[] n1, int[] n2, int i, int j) { 
        while (i<n1.length || j<n2.length) {
            if (i >= n1.length) return false; 
            else if (j >= n2.length) return true; 
            else if (n1[i] < n2[j]) return false; 
            else if (n1[i] > n2[j]) return true; 
            i++; j++; 
        }
        
        return true; 
    }
}
