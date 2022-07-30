/*
  
@link https://leetcode.com/problems/merge-k-sorted-lists/

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted list:
    1->1->2->3->4->4->5->6

Example 2:
    Input: lists = []
    Output: []

Example 3:
    Input: lists = [[]]
    Output: []
 
Constraints:
    k == lists.length
    0 <= k <= 104
    0 <= lists[i].length <= 500
    -104 <= lists[i][j] <= 104
    lists[i] is sorted in ascending order.
    The sum of lists[i].length will not exceed 104.
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
    public ListNode mergeKLists(ListNode[] lists) { 
        if (lists.length == 0) return null; 
        
        int interval = 1; 
        while (interval < lists.length) { 
            for (int i=0; i+interval<lists.length; i+=interval*2) {
                lists[i] = mergeTwoLists(lists[i], lists[i+interval]); 
            }
            
            interval *= 2; 
        }
        
        return lists[0];  
    }
        
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) { 
        if (l1==null || l2==null) 
            return l1==null ? l2 : l1; 
        ListNode p1=l1, p2=l2, head=l1, prev=null; 
        if (l1.val > l2.val) { 
            p1 = l2; 
            p2 = l1; 
            head = p1; 
        }
        
        while (p1!=null && p2!=null) { 
            if (p1.val>p2.val) { 
                prev.next = p2; 
                prev = prev.next; 
                p2 = p2.next; 
                prev.next = p1; 
            }
            else { 
                prev = p1; 
                p1 = p1.next; 
            }
        }
        if (p1 == null) prev.next = p2; 
        return head; 
    }
}


class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val); 
        
        for (ListNode n : lists) { 
            if (n != null) { 
                q.add(n);
            }
        }
        
        ListNode head = new ListNode();
        ListNode temp = head; 
        
        while(!q.isEmpty()) { 
            ListNode n = q.poll(); 
            temp.next = n; 
            temp = temp.next; 
            
            if (n.next != null) {
                q.add(n.next);
            }
        }
        
        temp.next = null; 
        return head.next; 
    }
}


class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null; 
        for (ListNode n : lists) { 
            head = mergeTwoLists(head, n);
        }
        return head; 
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) { 
        if (l1==null || l2==null) 
            return l1==null ? l2 : l1; 
        ListNode p1=l1, p2=l2, head=l1, prev=null; 
        if (l1.val > l2.val) { 
            p1 = l2; 
            p2 = l1; 
            head = p1; 
        }
        
        while (p1!=null && p2!=null) { 
            if (p1.val>p2.val) { 
                prev.next = p2; 
                prev = prev.next; 
                p2 = p2.next; 
                prev.next = p1; 
            }
            else { 
                prev = p1; 
                p1 = p1.next; 
            }
        }
        if (p1 == null) prev.next = p2; 
        return head; 
    }
}
