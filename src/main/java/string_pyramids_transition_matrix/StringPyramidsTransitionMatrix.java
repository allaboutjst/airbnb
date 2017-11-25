package string_pyramids_transition_matrix;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class StringPyramidsTransitionMatrix {
    /*
        String Pyramids Transition Matrix
        AirBnB Interview Question
     */
    public class Solution {
        Map<String, Set<Character>> map;
        private Map<String, Boolean> cache;
        final String SEP = "###";

        Solution(String[] line) {
            cache = new HashMap<>();
            map = new HashMap<>();
            for (String s : line) {
                String[] splitted = s.split(",");
                String key = splitted[0] + SEP + splitted[1];
                Set<Character> set = new HashSet<>();
                for (char c : splitted[2].toCharArray())
                    set.add(c);
                map.put(key, set);
            }
        }

        private void getNextLevel(List<String> res, String curr, int start, StringBuilder sb) {
            if (start == curr.length() - 1) {
                res.add(new String(sb));
                return;
            }
            for (int i = start; i < curr.length() - 1; i++) {
                String key = curr.charAt(i) + SEP + curr.charAt(i + 1);
                for (char c : map.get(key)) {
                    sb.append(c);
                    getNextLevel(res, curr, start + 1, sb);
                    sb.setLength(sb.length() - 1);
                }
            }
        }

        private boolean search(String input, String current) {
            if (cache.containsKey(input)) return cache.get(input);
            if (current.length() == 1) {
                cache.put(current, input.contains(current));
                return cache.get(current);
            }

            List<String> cands = new ArrayList<>();
            getNextLevel(cands, current, 0, new StringBuilder());
            for (String cand : cands) {
                // System.out.println(cand);
                if (cache.containsKey(cand)) return cache.get(cand);
                boolean res = search(input, cand);
                if (res) {
                    cache.put(cand, true);
                    return true;
                }
            }

            return false;
        }

        public boolean check(String input) {
            cache.clear();
            return search(input, input);
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            String[] lines = {
                    "A,A,AC", "A,B,CD", "A,C,D", "A,D,B",
                    "B,A,B", "B,B,C", "B,C,A", "B,D,CD",
                    "C,A,A", "C,B,C", "C,C,D", "C,D,B",
                    "D,A,BC", "D,B,D", "D,C,A", "D,D,C"
            };
            Solution sol = new StringPyramidsTransitionMatrix().new Solution(lines);
            // sol.check("ABCD");
            assertTrue(sol.check("ABCD"));
            assertTrue(sol.check("AACC"));
            assertTrue(sol.check("AAAA"));
            assertFalse(sol.check("CCCC"));
            assertFalse(sol.check("DDDD"));
        }
    }
}

