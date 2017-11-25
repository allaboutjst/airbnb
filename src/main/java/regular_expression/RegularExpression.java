package regular_expression;

import org.junit.*;

import static org.junit.Assert.*;

public class RegularExpression {
    /*
        Regular Expression
        AirBnB Interview Question
     */
    public class Solution {
        public boolean regMatch(String source, String pattern) {
            if (pattern.length() == 0) return source.length() == 0;
            if (pattern.length() == 1) {
                if (source.length() > 1 || source.length() == 0) return false;
                return source.charAt(0) == pattern.charAt(0);
            }

            if (source.length() != 0 && (pattern.charAt(0) == '.' || pattern.charAt(0) == source.charAt(0))) {
                if (pattern.charAt(1) == '*') {
                    return regMatch(source.substring(1), pattern) || regMatch(source, pattern.substring(2));
                } else if (pattern.charAt(1) == '+') {
                    return regMatch(source.substring(1), pattern.substring(2)) || regMatch(source.substring(1), pattern.substring(2));
                } else {
                    return regMatch(source.substring(1), pattern.substring(1));
                }
            }
            return pattern.charAt(1) == '*' && regMatch(source, pattern.substring(2));
        }
    }

    /*
        Regular Expression - Dynamic Programming
        AirBnB Interview Question
     */
    public class Solution_2 {
        public boolean regMatch(String s, String p) {
            if (s == null || p == null) return false;
            boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
            dp[0][0] = true;
            for (int i = 1; i <= p.length(); i++) {
                if (p.charAt(i - 1) == '*' && dp[i - 2][0]) dp[i][0] = true;
            }

            for (int i = 1; i <= p.length(); i++) {
                for (int j = 1; j <= s.length(); j++) {
                    if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(i - 1) == '*' || p.charAt(i - 1) == '+') {
                        if (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1)) {
                            if (p.charAt(i - 1) == '*') {
                                dp[i][j] = dp[i - 2][j] || dp[i - 2][j - 1] || dp[i][j - 1];
                            } else {
                                dp[i][j] = dp[i - 2][j - 1] || dp[i][j - 1];
                            }
                        } else {
                            dp[i][j] = p.charAt(i - 1) == '*' && dp[i - 2][j];
                        }
                    }
                }
            }
            return dp[p.length()][s.length()];
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new RegularExpression().new Solution();
            assertTrue(sol.regMatch("saaaa", "s+a*"));
            assertFalse(sol.regMatch("saaaa", "s+b*"));
            assertFalse(sol.regMatch("saaaab", "s+a*"));
            assertFalse(sol.regMatch("saaaab", "s+b*"));
        }

        @Test
        public void test2() {
            Solution_2 sol = new RegularExpression().new Solution_2();
            assertTrue(sol.regMatch("saaaa", "s+a*"));
            assertFalse(sol.regMatch("saaaa", "s+b*"));
            assertFalse(sol.regMatch("saaaab", "s+a*"));
            assertFalse(sol.regMatch("saaaab", "s+b*"));
        }
    }
}

