/*

@link https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]

Example 2:
    Input: head = [1], n = 1
    Output: []

Example 3: 
    Input: head = [1,2], n = 1
    Output: [1]

*

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode f = head, s = head; 
        
        for (int i=0; i<n; i++) f = f.next; 
        
        if (f == null) return head.next; 
        
        while(f.next != null) { 
            f = f.next; 
            s = s.next; 
        }
        
        s.next = s.next.next; // skip next; element as its the element to be deleted
        return head; 
    }
}





