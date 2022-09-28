/** 
  @link https://leetcode.com/problems/palindrome-linked-list

  To be solved need to know reverse list algo & slow-fast-pointers alog 


Problem Stmt:
  Given the head of a singly linked list, return true if it is a palindrome.


Example 1:
    Input: head = [1,2,2,1]
    Output: true

Example 2:
    Input: head = [1,2]
    Output: false

Constraints:
    The number of nodes in the list is in the range [1, 10^5].
    0 <= Node.val <= 9

*/ 


/* O(N), Space of O(1) */ 
class Solution { 
    public boolean isPalindrome(ListNode head) { 
        if (head == null) return true; 

        boolean result = true; 

        ListNode firstHalfEnd = endOfFirstHalf(head); 
        ListNode secondHalfHead = reverseList(firstHalfEnd.next); 

        ListNode p1=head, p2=secondHalfHead; 

        while(result && p2!=null) { 
            if (p1.val != p2.val) result = false; 

            p1 = p1.next; 
            p2 = p2.next; 
        }

        firstHalfEnd.next = reverseList(secondHalfHead); 

        return result; 
    }

    private ListNode endOfFirstHalf(ListNode head) { 
        ListNode s=head, f=head; 

        // this is work in even sized linkedlist 
        // but works the same as 
        // f!=null && f.next!= null in odd sized
        // linkedlist 
        while(f.next!=null && f.next.next!=null) { 
            s = s.next; 
            f = f.next.next; 
        }

        return s; 
    }

    private ListNode reverseList(ListNode head) { 
        ListNode curr=head, prev=null, next=null; 

        while(curr!=null) { 
            next = curr.next; 
            curr.next = prev; 
            prev = curr; 
            curr = next; 
        }

        return prev;
    }
}


/* O(N), Space of O(N) */ 
class Solution1 { 
    public boolean isPalindrome(ListNod head) { 
        ListNode d = head; 
        List<ListNode> arr = new ArrayList<>(); 

        while(d!=null) { 
            arr.add(d);
            d = d.next; 
        }


        int left = 0, right = arr.size()-1; 

        while(left<right) {
            if (!arr.get(left).equals(arr.get(right))) { 
                return false; 
            }

            left += 1; 
            right -= 1; 
        }


        return true; 
    }
}
