
/**
 * Problem 
 * @link: https://leetcode.com/problems/linked-list-cycle/ 
 *
 *
 * Explanation: 
 * @link: https://umairsaeed.com/finding-the-start-of-a-loop-in-a-circular-linked-list/# 
 *
 */
public class LinkedListCycleDetection { 
    public static void main(String[] args) {}

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head; 
        
        while(fast != null && fast.next != null) { 
            slow = slow.next; 
            fast = fast.next.next; 
            
            if (fast == slow) return true; 
        }
        
        return false; 
    }


}
