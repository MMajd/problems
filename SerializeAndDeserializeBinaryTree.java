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
public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}



// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//
//
//
