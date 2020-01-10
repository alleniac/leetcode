
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> colToList = new HashMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        nodeQueue.add(root);
        colQueue.add(0);

        int min = 0;
        int max = 0;

        while (!nodeQueue.isEmpty()) {
            TreeNode crt = nodeQueue.poll();
            int colIndex = colQueue.poll();
            min = Math.min(min, colIndex);
            max = Math.max(max, colIndex);

            if (!colToList.containsKey(colIndex)) {
                List<Integer> list = new ArrayList<>();
                colToList.put(colIndex, list);
            }

            colToList.get(colIndex).add(crt.val);

            if (crt.left != null) {
                nodeQueue.add(crt.left);
                colQueue.add(colIndex - 1);
            }

            if (crt.right != null) {
                nodeQueue.add(crt.right);
                colQueue.add(colIndex + 1);
            }
        }

        for (int i = min; i <= max; ++i) {
            List<Integer> list = colToList.get(i);
            result.add(list);
        }

        return result;
    }
}