/*
 @link https://leetcode.com/problems/linked-list-random-node
 @categories (reservoir-sampling/linked-list) 

 Given a singly linked list, return a random node's value from the linked list. 
Each node must have the same probability of being chosen. Implement the Solution class:
Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
int getRandom() Chooses a node randomly from the list and returns its value. 
All the nodes of the list should be equally likely to be chosen.
 
Example 1:
    Input
    ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
    [[[1, 2, 3]], [], [], [], [], []]
    Output
    [null, 1, 3, 2, 2, 3]

    Explanation
    Solution solution = new Solution([1, 2, 3]);
    solution.getRandom(); // return 1
    solution.getRandom(); // return 3
    solution.getRandom(); // return 2
    solution.getRandom(); // return 2
    solution.getRandom(); // return 3
    // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.

Constraints:
    The number of nodes in the linked list will be in the range [1, 10^4].
    -10^4 <= Node.val <= 10^4
    At most 104 calls will be made to getRandom.

Follow up:
    What if the linked list is extremely large and its length is unknown to you?
    Could you solve this efficiently without using extra space?
*/

class Solution {
    private final int k = 10; 
    int cnt = 0;

    ListNode curr;
    List<Integer> list; 

    public Solution(ListNode head) {
        this.head = head; 
        this.curr = head; 
        list = new ArrayList<>(k);

        for (int i=0; i<k & curr!=null; i++) { 
            list.add(curr.val);
            curr = curr.next; 
            cnt += 1; 
        }
    }
    
    public int getRandom() {
        Random rand = new Random(); 
        int idx = rand.nextInt(list.size()); 
        int ret = list.get(idx);
        if (list.size() < k || curr == null) { 
            return ret; 
        }

        cnt += 1; 
        int ridx = rand.nextInt(cnt);
        if (ridx < k) { 
            list.set(ridx, curr.val); 
        }
        curr = curr.next; 
        return ret; 
    }
}

class Solution {
    private ListNode head;
    private Random rand;

    public Solution(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }
    
    public int getRandom() {
        ListNode curr = this.head;
        int val = curr.val;
        for (int i = 1; curr.next != null; i++) {
            curr = curr.next;
            if (rand.nextInt(i + 1) == 1) {
                val = curr.val;
            }
        }
        return val;
    }
}
