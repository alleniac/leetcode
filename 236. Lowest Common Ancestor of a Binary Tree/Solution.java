class Solution {
    private class Result {
        TreeNode lca;
        boolean findP;
        boolean findQ;
        public Result(TreeNode lca, boolean findP, boolean findQ) {
            this.lca = lca;
            this.findP = findP;
            this.findQ = findQ;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return dfs(root, p, q).lca;
    }

    private Result dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Result(null, false, false);
        }

        Result leftResult = dfs(node.left, p, q);
        Result rightResult = dfs(node.right, p, q);

        TreeNode lca = null;
        if (leftResult.lca != null) {
            lca = leftResult.lca;
        } else if (rightResult.lca != null) {
            lca = rightResult.lca;
        }
        if (lca != null) {
            return new Result(lca, true, true);
        }

        boolean findP = node.val == p.val || leftResult.findP || rightResult.findP;
        boolean findQ = node.val == q.val || leftResult.findQ || rightResult.findQ;
        if (findP && findQ) {
            lca = node;
        }
        return new Result(lca, findP, findQ);
    }
}