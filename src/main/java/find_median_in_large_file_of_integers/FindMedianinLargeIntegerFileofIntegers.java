package find_median_in_large_file_of_integers;

import org.junit.*;

import static org.junit.Assert.*;

public class FindMedianinLargeIntegerFileofIntegers {
    /*
        Find Median in Large Integer File of Integers
        AirBnB Interview Question
     */
    public class Solution {
        private long search(int[] nums, int k, long left, long right) {
            if (left >= right) {
                return left;
            }

            long res = left;
            long guess = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= guess) {
                    count++;
                    res = Math.max(res, num);
                }
            }

            if (count == k) {
                return res;
            } else if (count < k) {
                return search(nums, k, Math.max(res + 1, guess), right);
            } else {
                return search(nums, k, left, res);
            }
        }

        public double findMedian(int[] nums) {
            int len = 0;
            for (int num : nums) {
                len++;
            }

            if (len % 2 == 1) {
                return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else {
                return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                        search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
            }
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new FindMedianinLargeIntegerFileofIntegers().new Solution();
            assertEquals(3.0, sol.findMedian(new int[]{3, -2, 7}), 1E-03);
            assertEquals(5.0, sol.findMedian(new int[]{-100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
            assertEquals(4.5, sol.findMedian(new int[]{4, -100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
        }
    }
}

