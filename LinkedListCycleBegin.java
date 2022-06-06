/** 
 * 
 * Problem 
 * @link: https://leetcode.com/problems/linked-list-cycle-ii/ 
 *
 *
 * Explanation
 * @link: https://umairsaeed.com/finding-the-start-of-a-loop-in-a-circular-linked-list
 */

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
      val = x;
      next = null;
  }
}


public class LinkedListCycleBegin { 
    public static void main(String[] args) { }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, start = null; 
        
        while(fast != null && fast.next != null) { 
            slow = slow.next; 
            fast = fast.next.next; 
            
            if (fast == slow) { 
                start = head; 
                while (start != slow)  { 
                    start = start.next; 
                    slow = slow.next; 
                }
                break;
            }
        }
        
        return start; 
    }
}
