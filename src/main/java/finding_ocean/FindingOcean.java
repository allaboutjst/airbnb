package finding_ocean;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class FindingOcean {
    /*
        Finding Ocean
     */
    public class Solution {
        private void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
            if (board[i][j] != oldColor || board[i][j] == newColor) {
                return;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i * board[0].length + j);
            board[i][j] = newColor;

            while (!queue.isEmpty()) {
                int pos = queue.poll();
                int m = pos / board[0].length;
                int n = pos % board[0].length;

                if (m + 1 < board.length && board[m + 1][n] == oldColor) {
                    queue.add((m + 1) * board[0].length + n);
                    board[m + 1][n] = newColor;
                }
                if (m - 1 >= 0 && board[m - 1][n] == oldColor) {
                    queue.add((m - 1) * board[0].length + n);
                    board[m - 1][n] = newColor;
                }
                if (n + 1 < board[0].length && board[m][n + 1] == oldColor) {
                    queue.add(m * board[0].length + n + 1);
                    board[m][n + 1] = newColor;
                }
                if (n - 1 >= 0 && board[m][n - 1] == oldColor) {
                    queue.add(m * board[0].length + n - 1);
                    board[m][n - 1] = newColor;
                }
            }
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new FindingOcean().new Solution();
            char[][] map = new char[][]{
                    {'W', 'W', 'W', 'L', 'L', 'L', 'W'},
                    {'W', 'W', 'L', 'L', 'L', 'W', 'W'},
                    {'W', 'L', 'L', 'L', 'L', 'W', 'W'}
            };
            sol.floodFill(map, 0, 0, 'W', 'O');
            assertEquals('O', map[0][0]);

//            String s = "LLLLLLLLLLLLLLLLLLLL";
//            points.insert("LLLLLLLLLLLLLLLLLLLL");
//            points.insert("LLLLLLLLLLLLLLWLLLLL");
//            points.insert("LLWWLLLLLLLLLLLLLLLL");
//            points.insert("LLWWLLLLLLLLLLLLLLLL");
//            points.insert("LLLLLLLLLLLLLLLLLLLL");
//            points.insert("LLLLLLLWWLLLLLLLLLLL");
//            points.insert("LLLLLLLLWWLLLLLLLLLL");
//            points.insert("LLLLLLLLLWWWLLLLLLLL");
//            points.insert("LLLLLLLLLLWWWWWWLLLL");
//            points.insert("LLLLLLLLLLWWWWWWLLLL");
//            points.insert("LLLLLLLLLLWWWWWWLLLL");
//            points.insert("LLLLWWLLLLWWWWWWLLLL");
//            points.insert("LLLLWWWLLLWWWWWWWWWW");
//            points.insert("LLLLLWWWWWWWWWWWLLLL");
//            points.insert("LLLLLLLLLLLLLLWWWWLL");
//            points.insert("LLLLLLLLLLLLLLWLLLLL");
//            points.insert("LLLLWLLLLLLLLLLLLWLL");
//            points.insert("LLLLLLLLLLLLLLLLLLWL");
        }
    }
}

