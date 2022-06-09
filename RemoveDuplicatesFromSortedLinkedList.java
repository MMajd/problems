

/** 
 *
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 */

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


public class RemoveDuplicatesFromSortedLinkedList { 
     
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) return head;
            
            ListNode h = head; 
            
            while(h.next != null) { 
                if(h.val == h.next.val) { 
                    h.next = h.next.next; 
                } else { 
                    h = h.next; 
                }
            }
            return head; 
        }
    }

}
