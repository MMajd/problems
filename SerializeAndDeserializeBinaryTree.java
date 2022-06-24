/** 
 *
 * @link https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * I tried to solve this problem using post & inorder to serialize the tree and to deserialize it back 
 * but there was sum edge cases, so I've abonded that approch
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public String serialize(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        serializeHelper(root,result);
        return result.toString();
    }

    private void serializeHelper(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            result.add(null);
            return;
        }
        result.add(root.val);
        serializeHelper(root.left,result);
        serializeHelper(root.right,result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArray = data.substring(1,data.length()-1).split(", ");
        Deque<String> strList = new LinkedList<String>(Arrays.asList(strArray)); 
        
        return deserializeHelper(strList);
    }
    
    private TreeNode deserializeHelper(Deque<String> strList){
        if (strList.size() == 0) return null;
        String str = strList.poll();
        
        if (str.equals("null")) return null;
        
        TreeNode currentRoot = new TreeNode(Integer.parseInt(str));
        currentRoot.left = deserializeHelper(strList);
        currentRoot.right = deserializeHelper(strList);
        
        return currentRoot;
    }
}


/** BFS **/ 
public String serialize(TreeNode root) {
    StringBuilder result = new StringBuilder();
    Deque<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    while (queue.size() > 0) {
        TreeNode curr = queue.removeFirst();
        if (curr != null) {
            result.append(curr.val + ",");
            queue.addLast(curr.left);
            queue.addLast(curr.right);
        } else {
            result.append("null,");
        }
    }
    result.setLength(result.length() - 1);    // Remove the superfluous "," at the end.
    return result.toString();
}


public TreeNode deserialize(String data) {
    if (data.equals("null")) return null;
    String[] nodes = data.split(",");
    Deque<TreeNode> queue = new ArrayDeque<>();
    TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
    queue.addLast(root);
    int i = 1;
    while (queue.size() > 0) {
        TreeNode curr = queue.removeFirst();
        curr.left = nodes[i].equals("null") ? null : new TreeNode(Integer.parseInt(nodes[i]));
        curr.right = nodes[i + 1].equals("null") ? null : new TreeNode(Integer.parseInt(nodes[i + 1]));
        if (curr.left != null) queue.addLast(curr.left);
        if (curr.right != null) queue.addLast(curr.right);
        i += 2;
    }
    return root;
}



// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//
//
//
