/* 
 
 @link https://leetcode.com/problems/rotate-list/

 Given the head of a linked list, rotate the list to the right by k places.


Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]
    Example 2:

Example 2:
    Input: head = [0,1,2], k = 4
    Output: [2,0,1]
 

Constraints:
    The number of nodes in the list is in the range [0, 500].
    -100 <= Node.val <= 100
    0 <= k <= 2 * 10^9

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null || k==0) return head; 
        
        ListNode tail=head; 
        int size = 1; 
        
        while(tail.next!=null) { 
            tail=tail.next;
            size+=1; 
        }
        
        k %= size; 
        
        // return early if there no need to do any thing
        if (k==0) return head;  
        
        tail.next = head; 
        
        int i= size - k - 1;  // new tail index
        
        while(--i>=0) head = head.next; 
        
        tail = head; 
        head = head.next; 
        tail.next = null; 
        
        return head; 
    }
}
