/* 
 
   @link https://leetcode.com/problems/remove-linked-list-elements/

   Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
    Input: head = [1,2,6,3,4,5,6], val = 6
    Output: [1,2,3,4,5]

Example 2:
    Input: head = [7,7,7,7], val = 7
    Output: []

Constraints:
    # f nodes in the list is in the range [10^4].
    # 1 <= Node.val <= 50
    # 0 <= val <= 50

*/

/** my solution */ 
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode curr=head, prev=null;

        while(curr!=null) { 
            if (curr.val == val) { 
                if (prev == null) head = head.next; 
                else prev.next = curr.next; 
            }
            else { 
                prev = curr; 
            }
            
            curr = next; 
        }
        
        return head; 
    }
}

/** Another solution  */
public class Solution1 {
    public ListNode removeElements(ListNode head, int val) {
        // node.val betn [0,50] 
        ListNode fakeHead = new ListNode(-1); 
        fakeHead.next = head;

        ListNode curr = head, prev = fakeHead;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }

        return fakeHead.next;
    }
}

