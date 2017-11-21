package find_median_in_large_integer_file_of_integers;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class FindMedianinLargeIntegerFileofIntegers {
    /*
        Find Median in Large Integer File of Integers
        Difficulty: Medium
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

    /*
        Find Median in Large Integer File of Integers
        Difficulty: Medium
     */
    public class Solution_2 {
        private long search(int[] nums, int k, long left, long right) {
            if (left >= right) {
                return left;
            }
            long guess = left + (right - left) / 2;
            long nextToGuess = right, counter = 0;
            for (int n : nums) {
                if (n <= guess) {
                    counter++;
                } else {
                    nextToGuess = Math.min(nextToGuess, n);
                }
            }

            if (counter == k) {
                return nextToGuess;
            } else if (counter < k) {
                return search(nums, k, guess, right);
            }
            return search(nums, k, left, guess - 1);
        }

        public double findMedian(int[] nums) {
            if (nums == null || nums.length == 0)
                throw new IllegalArgumentException("input can not be null");

            int n = nums.length;
            if (n % 2 == 1) {
                return search(nums, n / 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else {
                return (search(nums, n / 2 - 1, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                        search(nums, n / 2, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2.0;
            }
        }
    }

    /*
        Find Median in Large Integer File of Integers
        Difficulty: Medium
     */
    public class Solution_3 {
        private long search(int[] nums, int k, long left, long right) {
            while (left < right) {
                long guess = left + (right - left) / 2;
                // long guess = (right + left) / 2;
                long rightToGuess = right, counter = 0;
                for (int n : nums) {
                    if (n <= guess) {
                        counter++;
                    } else {
                        rightToGuess = Math.min(rightToGuess, n);
                    }
                }

                if (counter == k) {
                    return rightToGuess;
                } else if (counter < k) {
                    left = guess;
                } else {
                    right = guess - 1;
                }
            }
            return left;
        }

        public double findMedian(int[] nums) {
            if (nums == null || nums.length == 0)
                throw new IllegalArgumentException("input can not be null");

            int n = nums.length;
            if (n % 2 == 1) {
                return search(nums, n / 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else {
                return (search(nums, n / 2 - 1, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                        search(nums, n / 2, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2.0;
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

        @Test
        public void test2() {
            Solution_2 sol = new FindMedianinLargeIntegerFileofIntegers().new Solution_2();
            assertEquals(3.0, sol.findMedian(new int[]{3, -2, 7}), 1E-03);
            assertEquals(5.0, sol.findMedian(new int[]{-100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
            assertEquals(4.5, sol.findMedian(new int[]{4, -100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
        }

        @Test
        public void test3() {
            Solution_3 sol = new FindMedianinLargeIntegerFileofIntegers().new Solution_3();
            assertEquals(3.0, sol.findMedian(new int[]{3, -2, 7}), 1E-03);
            assertEquals(5.0, sol.findMedian(new int[]{-100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
            assertEquals(4.5, sol.findMedian(new int[]{4, -100, 99, 3, 0, 5, 7, 11, 66, -33}), 1E-03);
        }
    }
}

