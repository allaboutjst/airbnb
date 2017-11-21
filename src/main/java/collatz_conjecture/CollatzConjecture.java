package collatz_conjecture;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class CollatzConjecture {
    /*
        Collatz Conjecture
        https://en.wikipedia.org/wiki/Collatz_conjecture
     */
    public class Solution {
        private int findSteps(int num) {
            if (num <= 1) return 1;
            if (num % 2 == 0) return 1 + findSteps(num / 2);
            return 1 + findSteps(3 * num + 1);
        }

        public int findLongestSteps(int num) {
            if (num < 1) return 0;

            int res = 0;
            for (int i = 1; i <= num; i++) {
                int t = findSteps(i);
                res = Math.max(res, t);
            }

            return res;
        }
    }

    /*
        Collatz Conjecture
        https://en.wikipedia.org/wiki/Collatz_conjecture
     */
    public class Solution_2 {
        Map<Integer, Integer> map = new HashMap<>();

        private int findSteps(int num) {
            if (num <= 1) return 1;
            if (map.containsKey(num)) return map.get(num);
            if (num % 2 == 0) num = num / 2;
            else num = 3 * num + 1;
            if (map.containsKey(num)) return map.get(num) + 1;
            int t = findSteps(num);
            map.put(num, t);
            return t + 1;
        }

        public int findLongestSteps(int num) {
            if (num < 1) return 0;

            int res = 0;
            for (int i = 1; i <= num; i++) {
                int t = findSteps(i);
                map.put(i, t);
                res = Math.max(res, t);
            }

            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new CollatzConjecture().new Solution();
            assertEquals(1, sol.findLongestSteps(1));
            assertEquals(20, sol.findLongestSteps(10));
            assertEquals(112, sol.findLongestSteps(37));
            assertEquals(119, sol.findLongestSteps(101));
        }

        @Test
        public void test2() {
            Solution_2 sol = new CollatzConjecture().new Solution_2();
            assertEquals(1, sol.findLongestSteps(1));
            assertEquals(20, sol.findLongestSteps(10));
            assertEquals(112, sol.findLongestSteps(37));
            assertEquals(119, sol.findLongestSteps(101));
        }
    }
}

