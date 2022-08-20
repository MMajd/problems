| Problem                                                                     | DataStructure / Algo                                  |
| --------------------------------------------------------------------------- | ----------------------------------------------------- |
| Range Min Query                                                             | Segment Tree                                          |
| Range Sum Query                                                             | Segment Tree                                          |
| Range GCD Query                                                             | Segment Tree                                          |
| Asked to enumerate/permuatate/generate combinations                         | DP / Backtracking                                     |
| Ways to do something                                                        | DP / Backtracking                                     |
| Given starting state and end state and a goal to either max/min the cost    | DP                                                    |
| LongestCommonSubsequence (Min delete ops to make 2 strings the same)        | DP                                                    |
| LinkedList cycle begin / has loop                                           | Floyd loop detection                                  |
| LinkedList intersection                                                     | Introduce a loop and find element that loop begins at |
| Longest Substring Without Repeating Char / Maximum Erasure Value            | Sliding window                                        |
| Minimum Operations to Reduce X to Zero / Minimum Size Subarray Sum          | Sliding window                                        |
| Design Dictionary / Word search ..etc                                       | Trie DS                                               |
| Find Shortest Path / Some goal & start given / expolore tree or graph       | DP - BFS                                              |
| Find path to some target / Some goal & start given / expolore tree or graph | DFP - Memoization - DFS                               |
| Unique Valid Binary Trees / Valid Parenthses / Any other of Catalan Numbers | DP - Memoization - Catalan Numbers formula            |

---

**Problems notes**

- Given an array or a graph filled with specific set of numbers (ex: 0, 1 or -1, 0, INF) and asked to find nearest 0 for each 1 or something similiar use BFS and start by adding all zeros to the queue and implement the DFS template

  > - the best approach is to take the reverse route and to add all zeros to our queue and mark them as visited cells
  > - afte that use BFS on them and unvisited cells stage by stage
  > - if we to solve this problem same way as water and land meaning solving for each cell every time we will get TLE

- Rolling hash method (check all binary codes of size k problem)
  [link](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/discuss/2092553/Explaining-the-Rolling-Hash-Method-or-Guide)

- Minimum operations to reduce X to zero / Minimum size subarray sum
  - Covert the problem to finding the subarray with max length and sum equal to original sum - x | sum(nums) - x or sum(nums) - targetSum

**Bit manipulation notes**

- X is power of 2 when (x & (x-1)) == 0
- X mod power of 2 (x mod 2^n) = (x & (2^n - 1)) <br>
  Modulo in general returns the remainder of a value after division. So x mod 4, for example, returns 0, 1, 2 or 3 depending on x. These possible values can be represented using two bits in binary (00, 01, 10, 11) - another way to do x mod 4 is to simply set all the bits to zero in x except the last two ones.<br>
  this last note is valid also for modulo 10 and 10^n <br>
  347 % 10 = 7, since n = 1 <br>
  347 % 100 = 47, since n = 2

- X rightmost set bit, if x is not 0, y = x & ~ (x-1)
- Right propogate rightmost set bit in x, eg. x=0b0100 => x=0b0111, y = x || ((x & ~(x-1)) -1)

**Effective Techniques**

- Two pointers techinque is effective in certain situations such as give sorted array, want to remove duplicates, n-sum problems ...etc
- Example code

    ```java
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
    ```

- Calatan Numbers formula
- [Wiki](https://en.wikipedia.org/wiki/Catalan_number)

    ```math
    \begin{equation*}
    C(N)   = {1 / (N+1)} * {2N \choose N}
    \end{equation*}
    \begin{equation*}
    C(N)   =  {2N \choose N} - {2N \choose (N-1)}
    \end{equation*}
    ```

- Backtracking Template

    ```python
    """
    Backtracking is a general algorithm for finding solutions to some computational problems,
    notably constraint satisfaction problems, that incrementally builds candidates to the solutions,
    and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution [this is called pruning]

    """
    def backtrack(candidate):
        if find_solution(candidate):
            output(candidate)
            return

        # iterate all possible candidates.
        for next_candidate in list_of_candidates:
            if is_valid(next_candidate):
                # try this partial candidate solution
                place(next_candidate)
                # given the candidate, explore further.
                backtrack(next_candidate)
                # backtrack
                remove(next_candidate)

    ```

- Java goto like syntax 

    ```java

    LABEL: // our code will break and jump up here

    for (int i=0; i<XX; i++) { 
        for (int j=0; j<YY; j++) { 
            // ... after doing some stuff
            // ... we can check some variable to be true if so we can do
            // continue LABEL;
            break LABEL;
        }
    }
    ```


**Websites** 

- [Programiz] (https://www.programiz.com/)

**Articles**

- [Segement Tree](https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/)
- [Sorted Insert for Circular Linkedlist](https://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/)


**TODO**


| Problem         | Priorty | link                                                                           | Difficulity |
| --------------- | ------- | ------------------------------------------------------------------------------ |-------------
| Skyline Problem | Medium  | [like](https://leetcode.com/problems/the-skyline-problem/)                     | Hard 	   |
| Kth Smallest Element in a Sorted Matrix | low     | [link](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Medium |
| Closest Binary Search Tree Value        | Medium  | [link](Unavaliable) | Medium/Hard |
| Word Latter                             | Medium  | [link](https://leetcode.com/problems/word-ladder-ii/) | Hard | 
| Search in a Sorted Array of Unknow Size | Medium  | [link](Unavailable) | Medium/Hard | 
| Substring with Concat. of All Words     | Medium  | [link](https://leetcode.com/problems/substring-with-concatenation-of-all-words/) | Hard | 
| Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts | Medium  | [link](https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/) | Medium | 
