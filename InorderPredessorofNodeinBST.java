/*
    @link https://workat.tech/problem-solving/practice/inorder-predecessor-bst
    @link https://www.lintcode.com/problem/915


Given a binary search tree and a node in it, find the in-order predecessor of that node in the BST.
If the given node has no in-order predecessor in the tree, return null

Example 1
    Input: root = {2,1,3}, p = 1
    Output: null

Example 2
    Input: root = {2,1}, p = 2
Output: 1

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

	Node findPredecessor(Node root, Node p) {
		return findPredecessor(root, null, p); 
	}
	
	Node findMax(Node root) { 
		while(root.right != null) root = root.right; 
		return root; 
	}
	
	Node findPredecessor(Node root, Node pre, Node p) {
		if (root == null) return pre; 
		
		if (root.data == p.data) { 
			if (root.left != null) {
				return findMax(root.left); 
			}
		}
		
		if (root.data < p.data) { 
			pre = root; 
			return findPredecessor(root.right, pre, p); 
		}
		
		return findPredecessor(root.left, pre, p); 
	}
}






