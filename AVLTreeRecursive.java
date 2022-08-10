/**
 * Simple implementation of AVL Tree
 *
 * @auther mmajd 
 */


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

class BST {
    private int nodesCount; 
    private TreeNode root; 
    
    public int height() { 
        return root.height; 
    }

    public boolean isEmpty() { 
        return size() == 0; 
    }

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
    
    // can be done in one step 
    private TreeNode leftRightHeavy(TreeNode node) {
        node.left = rotateLeft(node.left);
        return leftLeftHeavy(node);
    }

    
    private TreeNode rightRightHeavy(TreeNode node) { 
        return rotateLeft(node);
    }
        
    // can be done in one step 
    private TreeNode rightLeftHeavy(TreeNode node) {
        node.right = rotateRight(node.right);
        return rightRightHeavy(node);
    }
    
    private TreeNode rotateLeft(TreeNode node) { 
        TreeNode parentToBe = node.right; 
        node.right = parentToBe.left; 
        parentToBe.left = node; 
        
        update(node);
        update(parentToBe);
        
        return parentToBe; 
    }
    
    private TreeNode rotateRight(TreeNode node) { 
        TreeNode parentToBe = node.left; 
        node.left = parentToBe.right; 
        parentToBe.right = node; 
        
        update(node);
        update(parentToBe);
        
        return parentToBe; 
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
        if (root == null) return "[]"; 

        TreeNode trav = root; 
        StringBuilder sb = new StringBuilder(); 
        Deque<TreeNode> stack = new ArrayDeque<>(nodesCount); 
        
        sb.append('['); 
        stack.addFirst(trav);
        
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


