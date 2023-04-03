/*
 @link https://leetcode.com/problems/naming-a-company
 @categories (hash-table/enumeration) 

 You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. 
The process of naming a company is as follows:
- Choose 2 distinct names from ideas, call them ideaA and ideaB.
- Swap the first letters of ideaA and ideaB with each other.
- If both of the new names are not found in the original ideas, 
  then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
- Otherwise, it is not a valid name.
Return the number of distinct valid names for the company.

Example 1:
    Input: ideas = ["coffee","donuts","time","toffee"]
    Output: 6
    Explanation: The following selections are valid:
    - ("coffee", "donuts"): The company name created is "doffee conuts".
    - ("donuts", "coffee"): The company name created is "conuts doffee".
    - ("donuts", "time"): The company name created is "tonuts dime".
    - ("donuts", "toffee"): The company name created is "tonuts doffee".
    - ("time", "donuts"): The company name created is "dime tonuts".
    - ("toffee", "donuts"): The company name created is "doffee tonuts".
    Therefore, there are a total of 6 distinct company names.

    The following are some examples of invalid selections:
    - ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
    - ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
    - ("coffee", "toffee"): Both names formed after swapping already exist in the original array.

Example 2:
    Input: ideas = ["lack","back"]
    Output: 0
    Explanation: There are no valid selections. Therefore, 0 is returned.

Constraints:
    2 <= ideas.length <= 5 * 10^4
    1 <= ideas[i].length <= 10
    ideas[i] consists of lowercase English letters.
    All the strings in ideas are unique.
*/

class Solution {
    public long distinctNames(String[] ideas) {
        Set<String>[] groups = new Set[26];
        Arrays.setAll(groups, i -> new HashSet<>());

        for (String idea : ideas) {
            groups[idea.charAt(0)-'a'].add(idea.substring(1));
        }

        long ans = 0;
        for (int i = 0; i < 25; ++i) {
            for (int j = i + 1; j < 26; ++j) {
                long common = 0;
                for (String ideaA : groups[i]) {
                    if (groups[j].contains(ideaA)) {
                        common++;
                    }
                }
                ans += 2 * (groups[i].size() - common) * (groups[j].size() - common);
            }
        }
        return ans;
    }
}

/** Not that efficient, but kept for the idea behind it*/ 
class Solution {
    public long distinctNames(String[] ideas) {
        Set<String> seen = new HashSet<>(Arrays.asList(ideas));
        int[][] count = new int[26][26]; 

        /** O(N * 26 * 10) */
        for (String idea : ideas) {
            int i = idea.charAt(0)-'a'; 
            for (int j=0; j<26; j++) {
                String newIdea = (char)(j + 'a') + idea.substring(1);
                if (!seen.contains(newIdea)) {
                    count[i][j] += 1;
                }
            }
        }
        long ans = 0; 
        for (int i=0; i<25; i++) {
            for (int j=i+1; j<26; j++) { 
                ans += 2 * count[i][j] * count[j][i];  // i - j can play together
            }
        }
        return ans; 
    }
}

