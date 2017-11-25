package palindrome_pairs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PalindromePairs {
    /*
        Palindrome Pairs - HashMap
        AirBnB Interview Question
     */
    public class Solution {
        private boolean isPalindrome(String s) {
            for (int i = 0; i < s.length() / 2; ++i)
                if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                    return false;
            return true;
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            if (words == null) return res;
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) map.put(words[i], i);
            for (int i = 0; i < words.length; i++) {
                int left = 0, right = 0;
                while (left <= right) {
                    String s = words[i].substring(left, right);
                    Integer j = map.get(new StringBuilder(s).reverse().toString());
                    if (j != null && i != j && isPalindrome(words[i].substring(left == 0 ? right : 0, left == 0 ? words[i].length() : left)))
                        res.add(Arrays.asList(left == 0 ? new Integer[]{i, j} : new Integer[]{j, i}));
                    if (right < words[i].length()) right++;
                    else left++;
                }
            }
            return res;
        }
    }

    /*
        Palindrome Pairs - Trie
        AirBnB Interview Question
     */
    public class Solution_2 {
        class TrieNode {
            TrieNode[] next;
            int index;
            List<Integer> list;

            TrieNode() {
                next = new TrieNode[26];
                index = -1;
                list = new ArrayList<>();
            }
        }

        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                if (root.next[word.charAt(i) - 'a'] == null) {
                    root.next[word.charAt(i) - 'a'] = new TrieNode();
                }

                if (isPalindrome(word, 0, i)) {
                    root.list.add(index);
                }

                root = root.next[word.charAt(i) - 'a'];
            }

            root.list.add(index);
            root.index = index;
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) return false;
            }

            return true;
        }

        private void search(String[] words, int i, TrieNode root, List<List<Integer>> list) {
            for (int j = 0; j < words[i].length(); j++) {
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    list.add(Arrays.asList(i, root.index));
                }

                root = root.next[words[i].charAt(j) - 'a'];
                if (root == null) return;
            }

            for (int j : root.list) {
                if (i == j) continue;
                list.add(Arrays.asList(i, j));
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();

            TrieNode root = new TrieNode();
            for (int i = 0; i < words.length; i++) {
                addWord(root, words[i], i);
            }

            for (int i = 0; i < words.length; i++) {
                search(words, i, root, res);
            }

            return res;
        }
    }
    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new PalindromePairs().new Solution();
            String[] test = new String[]{"bat", "tab", "cat"};
            List<List<Integer>> rslt = sol.palindromePairs(test);
            assertEquals(2, rslt.size());

            test = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
            rslt = sol.palindromePairs(test);
            assertEquals(4, rslt.size());

            test = new String[]{"a", ""};
            rslt = sol.palindromePairs(test);
            assertEquals(2, rslt.size());
        }

        @Test
        public void test2() {
            Solution_2 sol = new PalindromePairs().new Solution_2();
            String[] test = new String[]{"bat", "tab", "cat"};
            List<List<Integer>> rslt = sol.palindromePairs(test);
            assertEquals(2, rslt.size());

            test = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
            rslt = sol.palindromePairs(test);
            assertEquals(4, rslt.size());

            test = new String[]{"a", ""};
            rslt = sol.palindromePairs(test);
            assertEquals(2, rslt.size());
        }
    }
}

