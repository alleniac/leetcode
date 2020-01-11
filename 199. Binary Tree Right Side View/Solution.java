public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            final int size = nodeQueue.size();
            result.add(nodeQueue.get(size - 1));
            for (int i = 0; i < size; ++i) {
                TreeNode node = nodeQueue.poll();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
        }

        return result;
    }
}