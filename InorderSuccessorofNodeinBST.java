/** 

  @link https://workat.tech/problem-solving/practice/inorder-successor-bst
  @link https://www.lintcode.com/problem/448/


  Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.
If the given node has no in-order successor in the tree, return null.

It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)

Example 1:
    Input: {1,#,2}, node with value 1
    Output: 2
    Explanation:
      1
       \
        2

Example 2:
    Input: {2,1,3}, node with value 1
    Output: 2
    Explanation: 
        2
       / \
      1   3
*/ 




class Solution {
	/* This is the Node class definition
	
	class Node {
		public Node left;
		public Node right;
		public int data;

		public Node(int data) {
			this.data = data;
		}
	}
	*/

	Node findSuccessor(Node root, Node p) {
		return findSuccessor(root, null, p); 
	}
	
	Node findMin(Node root) { 
		while(root!=null && root.left!=null) root = root.left; 
		
		return root; 
	}
	
	Node findSuccessor(Node root, Node succ, Node p) {
		if (root == null) return succ; 
		
		if (root.data == p.data) { 
			if (root.right != null) 
				return findMin(root.right); 
		}
		
		if (root.data > p.data) { 
			succ = root; 
			return findSuccessor(root.left, succ, p);
		}
		
		return findSuccessor(root.right, succ, p); 
		
	}
}




