package hilbert_curve;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class HilbertCurve {
    /*
        Hilbert Curve
        https://en.wikipedia.org/wiki/Hilbert_curve
        AirBnB Interview Question
     */
    public class Solution {
        public int hilbertCurve(int x, int y, int iter) {
            if (iter == 0) return 1;
            int len = 1 << (iter - 1);
            int num = 1 << (2 * (iter - 1));

            if (x >= len && y >= len) {
                // 3 Shape is identical with previous iteration
                return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
            } else if (x < len && y >= len) {
                // 2 Shape is identical with previous iteration
                return num + hilbertCurve(x, y - len, iter - 1);
            } else if (x < len && y < len) {
                // 1 Clock-wise rotate 90
                return hilbertCurve(y, x, iter - 1);
            } else {
                // 4 Anti-Clockwise rotate 90
                return 3 * num + hilbertCurve(len - y - 1, 2 * len - x - 1, iter - 1);
            }
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new HilbertCurve().new Solution();
            assertEquals(3, sol.hilbertCurve(1, 1, 2));
            assertEquals(2, sol.hilbertCurve(0, 1, 1));
            assertEquals(9, sol.hilbertCurve(2, 2, 2));
        }
    }
}

