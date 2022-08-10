/*

 @link https://leetcode.com/problems/kth-largest-element-in-a-stream/

Design a class to find the kth largest element in a stream. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:
    KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
    int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
     
Example 1:
    Input
    ["KthLargest", "add", "add", "add", "add", "add"]
    [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
    Output
    [null, 4, 5, 5, 8, 8]
    Explanation
    KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
    kthLargest.add(3);   // return 4
    kthLargest.add(5);   // return 5
    kthLargest.add(10);  // return 5
    kthLargest.add(9);   // return 8
    kthLargest.add(4);   // return 8
        
Constraints:
    1 <= k <= 104
    0 <= nums.length <= 104
    -104 <= nums[i] <= 104
    -104 <= val <= 104
    At most 104 calls will be made to add.
    It is guaranteed that there will be at least k elements in the array when you search for the kth element.
*/


/** AVL Tree Solution, not efficient as Heap but I wanted to implement it */
class TreeNode {
    int val; 
    TreeNode left, right; 
    int height, bf; // balance factor
    
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val; 
        this.left = left; 
        this.right = right; 
    }
}

class AVLTree {
    private int nodesCount; 
    private TreeNode root; 

    public void insert(int val) {
        root = insert(root, val);
        nodesCount += 1; 
    }
    
    private TreeNode insert(TreeNode node, int val) { 
        if (node == null) return new TreeNode(val); 
        
        if (node.val > val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        
        update(node);
        
        return balance(node);
    }
    
    private void update(TreeNode node) { 
        int lh = node.left == null? -1 : node.left.height; 
        int rh = node.right == null? -1 : node.right.height; 
        
        node.height = 1 + Math.max(lh, rh);
        node.bf = rh - lh; 
    }
    
    private TreeNode balance(TreeNode node) { 
        if (node.bf == -2) { 
            if (node.left.bf <= 0) { 
                return leftLeftHeavy(node);
            }
            return leftRightHeavy(node);
        }
        
        if (node.bf == 2) { 
            if (node.right.bf >= 0) { 
                return rightRightHeavy(node);
            } 
            return rightLeftHeavy(node);
        }
        
        return node;
    }
    
    private TreeNode leftLeftHeavy(TreeNode node) {
        return rotateRight(node);
    }
    
    private TreeNode leftRightHeavy(TreeNode node) {
        node.left = rotateLeft(node.left);
        return leftLeftHeavy(node);
    }

    
    private TreeNode rightRightHeavy(TreeNode node) { 
        return rotateLeft(node);
    }
        
    private TreeNode rightLeftHeavy(TreeNode node) {
        node.right = rotateRight(node.right);
        return rightRightHeavy(node);
    }
    
    
    private TreeNode rotateLeft(TreeNode node) { 
        TreeNode b = node.right; 
        node.right = b.left; 
        b.left = node; 
        
        update(node);
        update(b);
        
        return b; 
    }
    
    private TreeNode rotateRight(TreeNode node) { 
        TreeNode b = node.left; 
        node.left = b.right; 
        b.right = node; 
        
        update(node);
        update(b);
        
        return b; 
    }
    
    public void remove(int val) { 
        if (root == null) return; 
        root = remove(root, val); 
        nodesCount--; 
    }
    
    private TreeNode remove(TreeNode node, int val) { 
        if (node == null) return null; 
        int cmp = Integer.compare(val, node.val);

        if (cmp < 0) { 
            node.left = remove(node.left, val);
        }
        else if (cmp > 0) { 
            node.right = remove(node.right, val);
        }
        else { 
            if (node.left == null) {
                return node.right; // sub-trees are balanced already, so return easily
            } 
            if (node.right == null) {
                return node.left; // sub-trees are balanced already, so return easily
            }
            
            if (node.left.height > node.right.height) { 
                int max = findMax(node.left);
                node.val = max; 
                node.left = remove(node.left, max); 
            }
            else { 
                int min = findMin(node.right);
                node.val = min; 
                node.right = remove(node.right, min);
            }
        }
        
        update(node);
        return balance(node); 
    }
    
    private int findMin(TreeNode node) { 
        while(node.left != null) node = node.left; 
        return node.val; 
    }
        
    private int findMax(TreeNode node) { 
        while(node.right != null) node = node.right; 
        return node.val; 
    }
    
    public int getMin() { 
        TreeNode curr = root; 
        if (curr == null) return Integer.MIN_VALUE; 
        while(curr.left != null) curr = curr.left; 
        return curr.val; 
    }
    
    public int getMax() { 
        TreeNode curr = root; 
        if (curr == null) return Integer.MIN_VALUE; 
        while(curr.right != null) curr = curr.right; 
        return curr.val; 
    }
    
    public int size() {
        return nodesCount; 
    }
    
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        Deque<TreeNode> stack = new ArrayDeque<>(nodesCount); 
        TreeNode trav = root; 
        
        sb.append('['); 
        stack.addFirst(root);
        
        while(!stack.isEmpty()) { 
            while(trav!=null && trav.left!=null) { 
                stack.push(trav.left);
                trav = trav.left; 
            }
            
            TreeNode node = stack.removeFirst();

            if (node.right!=null) {
                stack.push(node.right);
                trav = node.right; 
            }
            
            sb.append(node.val);
            
            if (!stack.isEmpty()) { 
                sb.append(',');
            }
        }
        
        sb.append(']'); 
        
        return sb.toString();
    }
}


class KthLargest {
    private int k; 
    private AVLTree bst; 

    public KthLargest(int k, int[] nums) {
        this.k = k; 
        bst = new AVLTree();  
        
        for (int i : nums) { 
            bst.insert(i);
            if (bst.size() > k) { 
                bst.remove(bst.getMin());
            }
        }
    }
    
    public int add(int val) {
        bst.insert(val);
        if (bst.size() > k) bst.remove(bst.getMin());
        return bst.getMin(); 
    }
}


/** Min Heap */
class KthLargest {
    int k; 
    PriorityQueue<Integer> q = null;
    
    public KthLargest(int k, int[] nums) {
        this.k = k; 
        q = new PriorityQueue<>(k+1); // make capcity k+1 
        
        for (int i : nums) {
            q.offer(i);
            if (q.size() > k) q.poll();
        }
    }
    
    public int add(int val) {
        q.offer(val);
        if (q.size() > k) q.poll(); 
        return q.peek();
    }
}
