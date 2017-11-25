package minimum_vertices_to_traverse_directed_graph;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class MinimumVerticestoTraverseDirectedGraph {
    /*
        Minimum Vertices to Traverse Directed Graph - DFS
        https://cs.stackexchange.com/questions/1698/find-the-minimal-number-of-runs-to-visit-every-edge-of-a-directed-graph
        AirBnB Interview Question
     */
    public class Solution {
        private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start,
                            Set<Integer> visited, Set<Integer> currVisited) {
            currVisited.add(cur);
            visited.add(cur);
            for (int next : nodes.get(cur)) {
                if (res.contains(next) && next != start) {
                    res.remove(next);
                }
                if (!currVisited.contains(next)) {
                    search(res, nodes, next, start, visited, currVisited);
                }
            }
        }

        public List<Integer> getMin(int[][] edges, int n) {
            Map<Integer, Set<Integer>> nodes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                nodes.put(i, new HashSet<>());
            }
            for (int[] edge : edges) {
                nodes.get(edge[0]).add(edge[1]);
            }

            Set<Integer> visited = new HashSet<>();
            Set<Integer> res = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i)) {
                    res.add(i);
                    visited.add(i);
                    search(res, nodes, i, i, visited, new HashSet<>());
                }
            }

            return new ArrayList<>(res);
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new MinimumVerticestoTraverseDirectedGraph().new Solution();
            ////    1->2->3->1, 2->0->0
            ////      0  1  2  3
            ////    0[1, 0, 0, 0]
            ////    1[0, 0, 1, 0]
            ////    2[1, 0, 0, 1]
            ////    3[0, 1, 0, 0]
            int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
            List<Integer> res = sol.getMin(edges, 4);
            System.out.println(res);
            assertEquals(1, res.size());

            ////    0->1->0, 2->3->2->1
            ////      0  1  2  3
            ////    0[0, 1, 0, 0]
            ////    1[1, 0, 0, 0]
            ////    2[0, 1, 0, 1]
            ////    3[0, 0, 1, 0]
            edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
            res = sol.getMin(edges, 4);
            System.out.println(res);
            assertEquals(1, res.size());

            ////    3->2->1->0  0->1 3->1
            ////      0  1  2  3
            ////    0[0, 1, 0, 0]
            ////    1[1, 0, 0, 0]
            ////    2[0, 1, 0, 0]
            ////    3[0, 1, 1, 0]
            edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
            res = sol.getMin(edges, 4);
            System.out.println(res);
            assertEquals(1, res.size());

            ////      0  1  2  3  4  5  6  7  8  9
            ////    0[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            ////    1[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            ////    2[0, 0, 0 ,0, 0, 0, 0, 0, 0, 1]
            ////    3[0, 0, 0, 1, 0, 1, 0, 1, 0, 0]
            ////    4[0, 0, 0, 0, 0, 0 ,0, 0, 1, 0]
            ////    5[0, 0, 0, 0, 0, 0, 0, 0, 1, 0]
            ////    6[0, 0, 0, 0, 0, 0, 1, 0, 0 ,0]
            ////    7[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
            ////    8[0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
            ////    9[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]
            edges = new int[][]{{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
            res = sol.getMin(edges, 10);
            System.out.println(res);
            assertEquals(3, res.size());
        }
    }
}

