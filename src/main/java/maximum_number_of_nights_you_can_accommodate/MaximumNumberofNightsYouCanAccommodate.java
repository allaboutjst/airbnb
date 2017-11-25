package maximum_number_of_nights_you_can_accommodate;

import org.junit.*;

import static org.junit.Assert.*;

public class MaximumNumberofNightsYouCanAccommodate {
    /*
        Maximum Number a Night You Can Accommodate
        AirBnB Interview Question
    */
    public class Solution {
        public int rob(int[] nums) {
            if (nums == null) return 0;
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];

            int f1 = nums[0]; // max sof far, excluding current
            int f2 = Math.max(nums[0], nums[1]); // max so far
            for (int i = 2; i < n; i++) {
                int f = Math.max(f1 + nums[i], f2);
                f1 = f2;
                f2 = f;
            }

            return f2;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new MaximumNumberofNightsYouCanAccommodate().new Solution();
            int[] test1 = {5, 6, 3, 1};
            assertEquals(8, sol.rob(test1));
            int[] test2 = {6, 5, 0, 1, 0, 9};
            assertEquals(16, sol.rob(test2));
            int[] test3 = {5, 1, 1, 5};
            assertEquals(10, sol.rob(test3));
            int[] test4 = {3, 6, 4};
            assertEquals(7, sol.rob(test4));
            int[] test5 = {4, 10, 3, 1, 5};
            assertEquals(15, sol.rob(test5));
        }
    }
}

