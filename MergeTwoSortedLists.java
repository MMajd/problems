/*
  
 @link https://leetcode.com/problems/merge-two-sorted-lists
  
 You are given the heads of two sorted linked lists l1 and l2.

 Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

 Return the head of the merged linked list.

 Example 1:
    Input: l1 = [1,2,4], l2 = [1,3,4]
    Output: [1,1,2,3,4,4]

Example 2:
    Input: l1 = [], l2 = []
    Output: []

Example 3:
    Input: l1 = [], l2 = [0]
    Output: [0]


** COULD USE RECURSION HERE AS OUR RANGE IS SMALL 

Constraints:
    The number of nodes in both lists is in the range [0, 50].
    -100 <= Node.val <= 100
    Both l1 and l2 are sorted in non-decreasing order.

  
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
    
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) { 
            return l1 == null ? l2 : l1; 
        }
        
        if (l1.val < l2.val)  { 
            l1.next = mergeTwoLists(l1.next, l2);
            return l1; 
        }
        
        l2.next = mergeTwoLists(l1, l2.next);
        return l2; 
    }
    
    public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) { 
            return l1 == null ? l2 : l1; 
        }
        
        ListNode p1=l1, p2=l2, head=l1, prev1 = null; 
        
        if (p1.val > p2.val) { 
            p1 = l2; 
            p2 = l1; 
            head = p1; 
        }
        
        while(p1!= null && p2!=null) { 
            if (p1.val>p2.val) { 
                prev1.next = p2; 
                prev1 = prev1.next; 
                p2 = p2.next; 
                prev1.next = p1; 
            } else { 
                prev1 = p1; 
                p1 = p1.next; 
            }
        }
        
        if (p1 == null) prev1.next = p2; 
        
        return head; 
    }
}

