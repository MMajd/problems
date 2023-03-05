/*
 @link https://leetcode.com/problems/jump-game-iv
 @categories (hash-table/bfs)

 Given an array of integers arr, you are initially positioned at the first index of the array.
In one step you can jump from index i to index:
i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.
Notice that you can not jump outside of the array at any time.

Example 1:
    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    Output: 3
    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:
    Input: arr = [7]
    Output: 0
    Explanation: Start index is the last index. You do not need to jump.

Example 3:
    Input: arr = [7,6,9,6,9,6,9,7]
    Output: 1
    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:
    1 <= arr.length <= 5 * 10^4
    -10^8 <= arr[i] <= 10^8
*/

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) 
            return 0;
        
        Map<Integer, List<Integer>> eqCls = new HashMap<>(); // eq class map
        for (int i = 0; i < n; i++) {
            eqCls.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        int steps = 0;
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int currIdx = q.poll();
                if (currIdx == n - 1) {
                    return steps;
                }

                List<Integer> neighbors = eqCls.get(arr[currIdx]);
                neighbors.add(currIdx - 1);
                neighbors.add(currIdx + 1);
                for (int nei : neighbors) {
                    if (nei < 0 || nei >= n || visited[nei]) continue; 

                    q.offer(nei);
                    visited[nei] = true;
                }
                neighbors.clear();
            }
            steps++;
        }

        return -1;
    }
}

