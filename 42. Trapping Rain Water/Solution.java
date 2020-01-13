class Solution {
    public int trap(int[] heights) {
        final int n = heights.length;
        if (n <= 2) {
            return 0;
        }

        int[] leftMax = new int[n];
        int[] rightMax= new int[n];

        leftMax[0] = heights[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(heights[i], leftMax[i - 1]);
        }

        rightMax[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(heights[i], rightMax[i + 1]);
        }

        int result = 0;
        for (int i = 0; i < n; ++i) {
            result += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }

        return result;
    }
}