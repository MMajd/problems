import java.util.*; 

class BinarySearchTree { 
    private static class Node { 
        int key; 
        Node left, right; 
        
        public Node(int key) {
            this.key = key; 
            this.left = null; 
            this.right = null; 
        }

        public Node(int key, Node left, Node right) {
            this.key = key; 
            this.left = left; 
            this.right = right; 
        }
    }

    Node root; 

    public BinarySearchTree() { 
        this.root = null; 
    }

    private Node insertKey(Node root, int key) { 
        if (root == null) { 
            return new Node(key);
        }

        if (key > root.key) { 
            root.right = insertKey(root.right, key);
        }
        else { 
            root.left = insertKey(root.left, key);
        }

        return root; 
    }

    public void insert(int key) {
        root = insertKey(root, key); 
    }

    private void inorder(Node root) {
        if (root == null) {
            System.out.println();
            return; 
        }

        inorder(root.left);
        System.out.print(root.key + " -> ");
        inorder(root.right);
    }

    public void inorder() { 
        inorder(root);
    }

    private int minValue(Node root) { 
        int min = root.key; 
        while(root.left != null) { 
            min = root.left.key; 
            root = root.left; 
        }

        return min; 
    }

    private Node deleteKey(Node root, int key) { 
        if (root == null) return root; 

        if (key > root.key) {
            root.right = deleteKey(root.right, key);
        }
        else if (key < root.key) {
            root.left = deleteKey(root.left, key);
        }
        else {
            if (root.left == null) return root.right; 
            else if (root.right == null) return root.left; 

            root.key = minValue(root.right); 
            root.right = deleteKey(root.right, root.key);
        }

        return root; 
    }

    public void deleteKey(int key) {
        root = deleteKey(root, key);
    }

      // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(8);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(7);
        tree.insert(10);
        tree.insert(14);
        tree.insert(4);

        System.out.print("Inorder traversal: ");
        tree.inorder();

        System.out.println("\n\nAfter deleting 10");
        tree.deleteKey(10);
        System.out.print("Inorder traversal: ");
        tree.inorder();
    }

}

