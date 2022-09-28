/* 
 *
 * @link https://leetcode.com/problems/linked-list-cycle-ii/
 *
 */


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode s = head, f = head; 
        
        while(f != null && f.next != null) { 
            s = s.next; 
            f = f.next.next; 

            if (s == f) { 
                ListNode p1 = s; 
                ListNode p2 = head; 
                
                while(p1 != p2) { 
                    p1 = p1.next; 
                    p2 = p2.next; 
                }
                
                return p1; 
            }
        }
        
        return null; 
    }
}
