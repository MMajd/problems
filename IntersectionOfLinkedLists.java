/**
 * Intersection detection of singly linked listed solution
 *
 * leetcode https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * @solution1: First solution uses length property of each list and find which 
 * one is longer and advances its head by the difference in length 
 * then we start comparing elements with each other till we find the 
 * element that exists in the two lists and return it 
 * if we finish the while loop without finding intersection element return null; 
 *
 * @solution2: if its guaranteed that the lists do not have cycles in them
 * we can introduce a cycle the attach the end of one list to the start of the other
 * and use Floyd's cycle finder algorithm more in that algorithm here 
 * @link: https://umairsaeed.com/posts/2011-06-23-finding-the-start-of-a-loop-in-a-circular-linked-list/ 
 */

public IntersectionOfLinkedLists { 
    public static void main() { 
    }

}


private class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }


/** 
 A:         a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:    b1 → b2 → b3


-Introduce a cycle 

    A:       a1 → a2
                    ↘
                     c1 → c2 → c3-|
                   ↗              |
B:    b1 → b2 → b3                |
        ↖-------------------------|
 */

private class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        // find last node of list A (c3)
        ListNode endA = headA;
        while (endA.next != null) {
            endA = endA.next;
        }
        // join c3 to b1 making a c1...c3-b1...b3-c1 loop (if b3 indeed points to c1)
        endA.next = headB;

        ListNode start = null; // if there's no cycle this will stay null
        // Floyd's cycle finder
        ListNode slow = headA, fast = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // found a cycle
                // reset to beginning to find cycle start point (c1)
                start = headA;
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                break;
            }
        }
        // unjoin c3-b1
        endA.next = null;
        return start;
    }
}

private class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        int len1 = 0, len2 = 0;
        while (p1 != null) {
            p1 = p1.next;
            len1++;
        }
        while (p2 != null) {
            p2 = p2.next;
            len2++;
        }
        p1 = headA;
        p2 = headB;
        if (len1 > len2) {
            for (int i = 0;i < len1 - len2; i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 0;i < len2 - len1; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}


private class Solution3 { 
    private Map<ListNode, Integer> ht; 

    public ListNode getIntersectionNode(ListNode a, ListNode b) { 
        ht = new HashMap<>(); 

        while(a != null) { 
            ht.put(a, ht.getOrDefault(a, 0) + 1);
            a = a.next; 
        }

        while(b != null) { 
            if (ht.containsKey(b)) return b; 
            ht.put(b, ht.getOrDefault(b, 0) + 1);
            b = b.next; 
        }

        return null; 
    }

}

