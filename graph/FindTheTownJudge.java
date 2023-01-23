/*
 @link https://leetcode.com/problems/find-the-town-judge
 @categories (graph/counting/hash-table)

 In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
- The town judge trusts nobody.
- Everybody (except for the town judge) trusts the town judge.
- There is exactly one person that satisfies properties 1 and 2.

You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

Example 1:
    Input: n = 2, trust = [[1,2]]
    Output: 2

Example 2:
    Input: n = 3, trust = [[1,3],[2,3]]
    Output: 3

Example 3:
    Input: n = 3, trust = [[1,3],[2,3],[3,1]]
    Output: -1
 
Constraints:
    1 <= n <= 1000
    0 <= trust.length <= 104
    trust[i].length == 2
    All the pairs of trust are unique.
    ai != bi
    1 <= ai, bi <= n
*/

class Solution {
    public int findJudge(int n, int[][] trust) {
        int ans = -1; 
        int[] count = new int[n+1]; 

        for (int[] e : trust) { 
            count[e[0]] -= 1; 
            count[e[1]] += 1; 
        }

        for (int i=1; i<=n; i++) { 
            if (count[i] == n-1) {
                ans = i; 
                break; 
            }
        }

        return ans; 
    }
}

class Solution {
    public int findJudge(int n, int[][] trust) {
        if (n == 1) return n; 

        int ans = -1;
        Map<Integer, List<Integer>> children = new HashMap<>(); 
        Map<Integer, List<Integer>> trusts = new HashMap<>(); 

        for (int[] e : trust) { 
            int a = e[0]; 
            int b = e[1]; 
            trusts.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            children.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        for (int c : children.keySet()) { 
            if (children.get(c).size() + 1 == n && !trusts.containsKey(c)) {
                return c;
            }
        }

        return ans; 
    }
}
