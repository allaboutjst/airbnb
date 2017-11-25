package boggle_game;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class BoggleGame {
    /*
        Boggle Game
        AirBnB Interview Question
     */
    public class Solution {
        private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        private String path2Word(char[][] board, List<int[]> curPath) {
            StringBuilder sb = new StringBuilder();
            for (int[] coor : curPath) {
                sb.append(board[coor[0]][coor[1]]);
            }
            return sb.toString();
        }

        private void search(List<List<int[]>> paths, char[][] board, int x, int y, Trie trie,
                            boolean[][] visited, List<int[]> curPath) {
            String curWord = path2Word(board, curPath);
            ReturnType flag = trie.search(curWord);
            if (!flag.hasPrefix) {
                return;
            }
            if (flag.hasWord) {
                paths.add(new ArrayList<>(curPath));
            }

            int m = board.length;
            int n = board[0].length;

            for (int[] dir : dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];

                if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                    continue;
                }

                visited[xx][yy] = true;
                curPath.add(new int[]{xx, yy});
                search(paths, board, xx, yy, trie, visited, curPath);
                curPath.remove(curPath.size() - 1);
                visited[xx][yy] = false;
            }
        }

        private void searchWords(List<String> res, List<String> curWords, List<List<int[]>> paths,
                                 int start, boolean[][] visited, char[][] board) {
            if (start == paths.size()) {
                if (curWords.size() > res.size()) {
                    res.clear();
                    res.addAll(curWords);
                }
                return;
            }

            for (int i = start; i < paths.size(); i++) {
                boolean canUse = true;
                for (int[] coor : paths.get(i)) {
                    if (visited[coor[0]][coor[1]]) {
                        canUse = false;
                        break;
                    }
                }

                if (canUse) {
                    for (int[] coor : paths.get(i)) {
                        visited[coor[0]][coor[1]] = true;
                    }
                    curWords.add(path2Word(board, paths.get(i)));
                    searchWords(res, curWords, paths, i + 1, visited, board);
                    curWords.remove(curWords.size() - 1);
                    for (int[] coor : paths.get(i)) {
                        visited[coor[0]][coor[1]] = false;
                    }
                }
            }
        }

        public List<String> findMostStr(char[][] board, Set<String> dict) {
            List<List<int[]>> paths = new ArrayList<>();

            Trie trie = new Trie();
            for (String word : dict) {
                trie.insert(word);
            }

            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    List<int[]> curPath = new ArrayList<>();
                    curPath.add(new int[]{i, j});
                    search(paths, board, i, j, trie, visited, curPath);
                }
            }

            List<String> res = new ArrayList<>();
            searchWords(res, new ArrayList<>(), paths, 0, new boolean[m][n], board);

            return res;
        }

        class ReturnType {
            boolean hasPrefix;
            boolean hasWord;

            ReturnType(boolean hasPrefix, boolean hasWord) {
                this.hasPrefix = hasPrefix;
                this.hasWord = hasWord;
            }
        }

        class TrieNode {
            char c;
            boolean isEnd;
            Map<Character, TrieNode> children;

            public TrieNode(char c, boolean isEnd) {
                this.c = c;
                this.isEnd = isEnd;
                this.children = new HashMap<>();
            }
        }

        class Trie {
            private TrieNode root;

            public Trie() {
                this.root = new TrieNode(' ', false);
            }

            public void insert(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!cur.children.containsKey(c)) {
                        cur.children.put(c, new TrieNode(c, false));
                    }
                    cur = cur.children.get(c);
                }
                cur.isEnd = true;
            }

            public ReturnType search(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!cur.children.containsKey(c)) {
                        return new ReturnType(false, false);
                    }
                    cur = cur.children.get(c);
                }
                return new ReturnType(true, cur.isEnd);
            }
        }
    }


    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new BoggleGame().new Solution();
            char[][] board = {
                    {'o', 'a', 't', 'h'},
                    {'e', 't', 'a', 'e'},
                    {'i', 'h', 'k', 'r'},
                    {'i', 'f', 'l', 'v'}
            };
            Set<String> dict = new HashSet<>();
            dict.add("oath");
            dict.add("pea");
            dict.add("eat");
            dict.add("rain");
            List<String> res = sol.findMostStr(board, dict);
            assertEquals(2, res.size());
            assertEquals("oath", res.get(0));
            assertEquals("eat", res.get(1));
        }
    }
}

