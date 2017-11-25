package sliding_game;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class SlidingGame {
    /*
        Sliding Game
        AirBnB Interview Question
     */
    public class Solution {
        private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        private int[][] matrix;
        private int m;
        private int n;
        private int originX;
        private int originY;
        private String recovered;

        public Solution(int[][] matrix) {
            this.matrix = matrix;
            this.m = matrix.length;
            this.n = matrix[0].length;
            int[][] recoveredMatrix = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        this.originX = i;
                        this.originY = j;
                    }

                    recoveredMatrix[i][j] = i * n + j;
                }
            }
            this.recovered = getMatrixString(recoveredMatrix);
        }

        public boolean canSolve() {
            Queue<int[]> items = new LinkedList<>();
            Queue<String> matrix = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            String stringMatrix = getMatrixString(this.matrix.clone());
            items.offer(new int[]{originX, originY});
            matrix.offer(stringMatrix);
            visited.add(stringMatrix);

            while (!items.isEmpty()) {
                int size = items.size();
                for (int i = 0; i < size; i++) {
                    int[] curElement = items.poll();
                    String curMatrixString = matrix.poll();
                    int x = curElement[0];
                    int y = curElement[1];

                    if (curMatrixString.equals(recovered)) {
                        return true;
                    }

                    for (int k = 0; k < dirs.length; k++) {
                        int xx = x + dirs[k][0];
                        int yy = y + dirs[k][1];

                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                            continue;
                        }

                        int[][] newMatrix = recoverMatrixString(curMatrixString);
                        int temp = newMatrix[x][y];
                        newMatrix[x][y] = newMatrix[xx][yy];
                        newMatrix[xx][yy] = temp;
                        String newMatrixString = getMatrixString(newMatrix);
                        if (visited.contains(newMatrixString)) {
                            continue;
                        }

                        items.offer(new int[]{xx, yy});
                        matrix.offer(newMatrixString);
                        visited.add(newMatrixString);
                    }
                }
            }

            return false;
        }

        public List<String> getSolution() {
            String[] pathWord = {"Down", "Right", "Up", "Left"};

            Queue<int[]> items = new LinkedList<>();
            Queue<String> matrix = new LinkedList<>();
            Queue<List<String>> path = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            String stringMatrix = getMatrixString(this.matrix.clone());
            items.offer(new int[]{originX, originY});
            matrix.offer(stringMatrix);
            path.offer(new ArrayList<>());
            visited.add(stringMatrix);

            while (!items.isEmpty()) {
                int size = items.size();
                for (int i = 0; i < size; i++) {
                    int[] curElement = items.poll();
                    String curMatrixString = matrix.poll();
                    List<String> curPath = path.poll();
                    int x = curElement[0];
                    int y = curElement[1];

                    if (curMatrixString.equals(recovered)) {
                        return curPath;
                    }

                    for (int k = 0; k < dirs.length; k++) {
                        int xx = x + dirs[k][0];
                        int yy = y + dirs[k][1];

                        if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                            continue;
                        }

                        int[][] newMatrix = recoverMatrixString(curMatrixString);
                        int temp = newMatrix[x][y];
                        newMatrix[x][y] = newMatrix[xx][yy];
                        newMatrix[xx][yy] = temp;
                        String newMatrixString = getMatrixString(newMatrix);
                        if (visited.contains(newMatrixString)) {
                            continue;
                        }

                        List<String> newPath = new ArrayList<>(curPath);
                        newPath.add(pathWord[k]);

                        items.offer(new int[]{xx, yy});
                        matrix.offer(newMatrixString);
                        path.offer(newPath);
                        visited.add(newMatrixString);
                    }
                }
            }

            return new ArrayList<>();
        }

        private String getMatrixString(int[][] matrix) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(matrix[i][j]).append(",");
                }
            }
            return sb.toString();
        }

        private int[][] recoverMatrixString(String str) {
            String[] parts = str.split(",");
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[i][j] = Integer.parseInt(parts[i * n + j]);
                }
            }
            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            int[][] matrix = {
                    {3, 1, 4},
                    {6, 2, 0},
                    {7, 5, 8}
            };
            Solution sol = new SlidingGame().new Solution(matrix);
            assertTrue(sol.canSolve());
            List<String> res = sol.getSolution();
            System.out.println(res);
            assertEquals(11, (int) res.size());
            assertEquals("Left", res.get(0));
            assertEquals("Down", res.get(1));
            assertEquals("Left", res.get(2));
            assertEquals("Up", res.get(3));
            assertEquals("Up", res.get(4));
            assertEquals("Right", res.get(5));
            assertEquals("Right", res.get(6));
            assertEquals("Down", res.get(7));
            assertEquals("Left", res.get(8));
            assertEquals("Up", res.get(9));
            assertEquals("Left", res.get(10));
        }
    }
}

