/* 

@link https://leetcode.com/problems/add-two-numbers

 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.

Example 2:
    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1=l1, p2=l2; 
        ListNode head = new ListNode(0); 
        ListNode curr = head; 
        int sum = head.val; 
        
        
        while (p1!=null || p2!=null || sum!=0) { 
            if (p1!=null) { 
                sum += p1.val; 
                p1 = p1.next; 
            }
            if (p2!=null) { 
                sum += p2.val; 
                p2 = p2.next; 
            }
            
            curr.next = new ListNode(sum%10); 
            sum /= 10; 
            curr = curr.next; 
        }
        
        return head.next; 
    }
}

