package find_case_combinations_of_a_string;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class FindCaseCombinationsofaString {
    /*
        Find Case Combinations of a String
        AirBnB Interview Question
     */
    public class Solution {
        private boolean isBitSet(int n, int offset) {
            return (n >> offset & 1) != 0;
        }

        public List<String> strComb(String text) {
            List<String> res = new ArrayList<>();
            if (text == null || text.length() == 0) {
                return res;
            }
            char[] chars = text.toCharArray();
            for (int i = 0, n = (int) Math.pow(2, chars.length); i < n; i++) {
                char[] curr = new char[chars.length];
                for (int j = 0; j < chars.length; j++) {
                    curr[j] = (isBitSet(i, j)) ? Character.toUpperCase(chars[j]) : Character.toLowerCase(chars[j]);
                }
                res.add(new String(curr));
            }
            return res;
        }

    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new FindCaseCombinationsofaString().new Solution();
            List<String> res = sol.strComb("AirBnB");
            assertEquals(64, res.size());
            assertEquals("airbnb", res.get(0));
            assertEquals("Airbnb", res.get(1));
            assertEquals("aIRBNB", res.get(62));
            assertEquals("AIRBNB", res.get(63));
        }
    }
}

