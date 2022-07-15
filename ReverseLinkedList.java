/*

@link https://leetcode.com/problems/reverse-linked-list/

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head; 

        ListNode rest = reverseListRecursive(head.next); 
        head.next.next = head; 
        head.next = null; 

        return rest; 
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head, next = null, prev = null; 
        
        while(curr != null)  { 
            next = curr.next; 
            curr.next = prev; 
            prev = curr; 
            curr = next; 
        }
            
        return prev; 
    }
}
