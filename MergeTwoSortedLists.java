/*
  
 @link https://leetcode.com/problems/merge-two-sorted-lists/ 
  
  
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) { 
            return list1 == null ? list2 : list1; 
        }
        
        ListNode p1=list1, p2=list2, head=list1, prev1 = null; 
        
        if (p1.val > p2.val) { 
            p1 = list2; 
            p2 = list1; 
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

