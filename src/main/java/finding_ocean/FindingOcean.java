package finding_ocean;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class FindingOcean {
    /*
        Finding Ocean
        AirBnB Interview Question
     */
    public class Solution {
        public void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
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
            List<String> testData = new ArrayList<String>() {{
                add("WWWLLLW");
                add("WWLLLWW");
                add("WLLLLWW");
            }};
            char[][] map = new char[testData.size()][testData.get(0).length()];
            for (int i = 0; i < testData.size(); i++)
                for (int j = 0; j < testData.get(i).length(); j++)
                    map[i][j] = testData.get(i).charAt(j);

            sol.floodFill(map, 0, 0, 'W', 'O');
            assertEquals('O', map[0][0]);


            testData = new ArrayList<String>() {{
                add("LLLLLLLLLLLLLLLLLLLL");
                add("LLLLLLLLLLLLLLLLLLLL");
                add("LLLLLLLLLLLLLLWLLLLL");
                add("LLWWLLLLLLLLLLLLLLLL");
                add("LLWWLLLLLLLLLLLLLLLL");
                add("LLLLLLLLLLLLLLLLLLLL");
                add("LLLLLLLWWLLLLLLLLLLL");
                add("LLLLLLLLWWLLLLLLLLLL");
                add("LLLLLLLLLWWWLLLLLLLL");
                add("LLLLLLLLLLWWWWWWLLLL");
                add("LLLLLLLLLLWWWWWWLLLL");
                add("LLLLLLLLLLWWWWWWLLLL");
                add("LLLLWWLLLLWWWWWWLLLL");
                add("LLLLWWWLLLWWWWWWWWWW");
                add("LLLLLWWWWWWWWWWWLLLL");
                add("LLLLLLLLLLLLLLWWWWLL");
                add("LLLLLLLLLLLLLLWLLLLL");
                add("LLLLWLLLLLLLLLLLLWLL");
                add("LLLLLLLLLLLLLLLLLLWL");
            }};

            map = new char[testData.size()][testData.get(0).length()];
            for (int i = 0; i < testData.size(); i++)
                for (int j = 0; j < testData.get(i).length(); j++)
                    map[i][j] = testData.get(i).charAt(j);
            sol.floodFill(map, 9, 12, 'W', 'O');
            assertEquals('O', map[9][11]);
        }
    }
}

