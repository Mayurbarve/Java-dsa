import java.util.Arrays;

void main() {

    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] nums2 = {2, 5, 6};
    int n = 3;

    TwoPointerMerge(nums1, m, nums2, n);
}
        public static void TwoPointerMerge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int k = m + n - 1;

            while (j >= 0) {
                if (i >= 0 && nums1[i] > nums2[j]) {
                    nums1[k--] = nums1[i--];
                } else {
                    nums1[k--] = nums2[j--];
                }
            }
        }

        public void burtForceMerge(int[] nums1, int m, int[] nums2, int n) {
            for (int j = 0, i = m; j < n; j++) {
                nums1[i] = nums2[j];
                i++;
            }
            Arrays.sort(nums1);
        }
