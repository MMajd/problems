/* 

@link https://leetcode.com/problems/combinations/

Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

Example 1:
    Input: n = 4, k = 2
    Output:
    [
        [2,4],
        [3,4],
        [2,3],
        [1,2],
        [1,3],
        [1,4],
    ]

Example 2:
    Input: n = 1, k = 1
    Output: [[1]]
 
Constraints:
    1 <= n <= 20
    1 <= k <= n

*/


class Solution {
    List<List<Integer>> ans = new ArrayList<>(); 
    
    public List<List<Integer>> combine(int n, int k) {
        Integer[] data = new Integer[k]; 
        solve(1, n, data, 0);
        return ans; 
    }
    
    
    private void solve(int start, int end, Integer[] data, int index) { 
        if (index == data.length) { 
            ans.add(new ArrayList<>(Arrays.asList(data)));
            return ;
        }
        
        int max= Math.min(end, end+1-data.length+index);
        
        for(int i=start; i<=max; i++) { 
            data[index] = i; 
            solve(i+1, end, data, index+1);
        }
    }
}
