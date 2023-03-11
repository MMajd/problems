/*
 @link https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree
 @categories (D-and-C/bst/binary-tree) 

 Given the head of a singly linked list where elements are sorted in ascending order, 
convert it to a height-balanced. A height-balanced binary tree is a binary tree 
in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
    Input: head = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: One possible answer is [0,-3,9,-10,null,5], 
    which represents the shown height balanced BST.

Example 2:
    Input: head = []
    Output: []

Example 3:
    Input: [-10,0,5,9]
    Output: [5,0,9,-10]

Constraints:
    The number of nodes in head is in the range [0, 2 * 10^4].
    -10^5 <= Node.val <= 10^5

*/ 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree treeNode.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right); 
 * }
 */

/** No modification on input linkedlist solution */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return toBST(head, null);
    }

    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode treeNode = new TreeNode(slow.val);
        treeNode.left = toBST(head,slow);
        treeNode.right = toBST(slow.next,tail);
        return treeNode;
    }
}

/** Modifying input linkedlist solution */ 
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return toBST(head);
    }

    private TreeNode toBST(ListNode start) { 
        if (start == null) return null; 
        if (start.next == null) return new TreeNode(start.val); 
        ListNode slow = start, fast = start; 
        ListNode prev = start; 
        while (fast != null && fast.next != null) { 
            prev = slow; 
            slow = slow.next; 
            fast = fast.next.next; 
        }
        prev.next = null; 
        ListNode next = slow.next;
        TreeNode treeNode = new TreeNode(slow.val);
        treeNode.left = toBST(start);
        treeNode.right = toBST(next);
        return treeNode; 
    }
}

