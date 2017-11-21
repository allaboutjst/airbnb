package preference_list;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class PreferenceList {
    /*
        Preference List

     */
    public class Solution {
        public List<Integer> getPreference(List<List<Integer>> preferences) {
            Map<Integer, Integer> inDegree = new HashMap<>();
            Map<Integer, Set<Integer>> nodes = new HashMap<>();
            for (List<Integer> l : preferences) {
                for (int i = 0; i < l.size() - 1; i++) {
                    int from = l.get(i);
                    int to = l.get(i + 1);
                    if (!nodes.containsKey(from)) {
                        inDegree.put(from, 0);
                        nodes.put(from, new HashSet<>());
                    }
                    if (!nodes.containsKey(to)) {
                        inDegree.put(to, 0);
                        nodes.put(to, new HashSet<>());
                    }
                    if (!nodes.get(from).contains(to)) {
                        Set<Integer> s = nodes.get(from);
                        s.add(to);
                        nodes.put(from, s);
                    }
                    inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
                }
            }
            // System.out.println(inDegree);
            // System.out.println(nodes);
            Queue<Integer> q = new LinkedList<>();
            for (int k : inDegree.keySet()) {
                if (inDegree.get(k) == 0) {
                    q.offer(k);
                }
            }
            List<Integer> res = new ArrayList<>();
            while (!q.isEmpty()) {
                int id = q.poll();
                res.add(id);
                Set<Integer> neighbors = nodes.get(id);
                for (int next : neighbors) {
                    int degree = inDegree.get(next) - 1;
                    inDegree.put(next, degree);
                    if (degree == 0) q.offer(next);
                }
            }
            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new PreferenceList().new Solution();
            List<List<Integer>> preferences = new ArrayList<>();
            List<Integer> p1 = new ArrayList<Integer>() {{
                add(2);
                add(3);
                add(5);
            }};
            List<Integer> p2 = new ArrayList<Integer>() {{
                add(4);
                add(2);
                add(1);
            }};
            List<Integer> p3 = new ArrayList<Integer>() {{
                add(4);
                add(1);
                add(5);
                add(6);
            }};
            List<Integer> p4 = new ArrayList<Integer>() {{
                add(4);
                add(7);
            }};
            preferences.add(p1);
            preferences.add(p2);
            preferences.add(p3);
            preferences.add(p4);
            List<Integer> res = sol.getPreference(preferences);
            // System.out.println(res);
            assertEquals(7, res.size());
            assertEquals(4, (int) res.get(0));
            assertEquals(2, (int) res.get(1));
            assertEquals(7, (int) res.get(2));
            assertEquals(1, (int) res.get(3));
            assertEquals(3, (int) res.get(4));
            assertEquals(5, (int) res.get(5));
            assertEquals(6, (int) res.get(6));

            p1 = new ArrayList<Integer>() {{
                add(3);
                add(5);
                add(7);
                add(9);
            }};
            p2 = new ArrayList<Integer>() {{
                add(2);
                add(3);
                add(8);
            }};
            p3 = new ArrayList<Integer>() {{
                add(5);
                add(8);
            }};
            preferences = new ArrayList<>();
            preferences.add(p1);
            preferences.add(p2);
            preferences.add(p3);
            res = sol.getPreference(preferences);
            // System.out.println(res);
            assertEquals(6, res.size());
        }
    }
}

