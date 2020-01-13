class Solution {
    public void nextPermutation(int[] nums) {
        final int n = nums.length;
        int index = 0;
        // From the end of the array, find the first element that is strictly smaller than 
        // its next element.
        for (int i = n - 2; i >= -1; --i) {
            if (i == -1) {
                Arrays.sort(nums);
                return;
            }
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }

        // From the end of the array to `index`, find the first element that is strictly 
        // larger than nums[index], and swap it with nums[index].
        for (int i = n - 1; i > index; --i) {
            if (nums[i] > nums[index]) {
                swap(nums, index, i);
                break;
            }
        }

        // Sort the array after nums[index]
        Arrays.sort(nums, index + 1, n);

        return;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        return;
    }
}