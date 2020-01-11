class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        final int n = matrix.length;
        final int m = matrix[0].length;
        Integer[][] existed = new Integer[n][m];
        int result = 0;
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                result = Math.max(result, dfs(matrix, i, j, existed));
            }
        }

        return result;
    }

    private int dfs(int[][] matrix, int i, int j, Integer[][] existed) {
        // base case
        if (existed[i][j] != null) {
            return existed[i][j];
        }

        final int n = matrix.length;
        final int m = matrix[0].length;
        int result = 1;

        // up
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            result = Math.max(result, dfs(matrix, i - 1, j, existed) + 1);
        }

        // down
        if (i + 1 < n && matrix[i + 1][j] > matrix[i][j]) {
            result = Math.max(result, dfs(matrix, i + 1, j, existed) + 1);
        }

        // left
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            result = Math.max(result, dfs(matrix, i, j - 1, existed) + 1);
        }

        // right
        if (j + 1 < m && matrix[i][j + 1] > matrix[i][j]) {
            result = Math.max(result, dfs(matrix, i, j + 1, existed) + 1);
        }

        existed[i][j] = result;

        return result;
    }
}