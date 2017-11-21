package guess_number;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class GuessNumber {
    /*
        Guess Number
        Difficulty: Medium
     */
    public class Solution {
        String target;

        public Solution(String target) {
            this.target = target;
        }

        private int guessServer(String guess) {
            int res = 0;
            Map<Character, Integer> targetMap = new HashMap<>();
            for (char c : target.toCharArray()) targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
            Map<Character, Integer> guessMap = new HashMap<>();
            for (char c : guess.toCharArray()) guessMap.put(c, guessMap.getOrDefault(c, 0) + 1);
            for (char k : guessMap.keySet()) {
                if (targetMap.containsKey(k))
                    res += Math.min(guessMap.get(k), targetMap.get(k));
            }
            return res;
        }

        private String genNumber(List<Integer> guessed, int c) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < guessed.size(); i++)
                sb.append(guessed.get(i));
            for (int i = guessed.size(); i < 4; i++)
                sb.append(c);
            return sb.toString();
        }

        private String genNumber(List<Integer> guessed) {
            if (guessed == null || guessed.size() == 0) return "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < guessed.size(); i++)
                sb.append(guessed.get(i));
            return sb.toString();
        }

        public String guess() {
            List<Integer> res = new ArrayList<>();
            List<Integer> cands = new ArrayList<Integer>(){{
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                // insert(6);
            }};
            // System.out.println("\nstart to guess " + target + " ...");
            // System.out.println("res: " + res);
            // System.out.println("candList: " + candList);
            int counter = 0;
            Iterator<Integer> iter = cands.iterator();
            while (iter.hasNext() && res.size() < 4) {
                int cand = iter.next();
                counter++;
                int guessedCount = res.size();
                String guessCand = genNumber(res, cand);
                int guessRes = guessServer(guessCand);
                // System.out.println("cand: " + cand);
                // System.out.println("guessRes: " + guessRes);
                if (guessRes == guessedCount) {
                    iter.remove();
                } else if (guessRes > guessedCount) {
                    for (int i=guessedCount; i< guessRes; i++) {
                        res.add(cand);
                    }
                    iter.remove();
                } else {
                    // something wrong here
                    return genNumber(res);
                }
            }
            if (res.size() < 4) {
                for (int i=res.size(); i<4; i++)
                    res.add(6);
            }

            // System.out.println("guessed " + counter + " times");
            return genNumber(res);
        }
    }

    public static class UnitTest {
        @Test
        public void test() {
            String guess = "2345";
            Solution sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());

            guess = "3456";
            sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());

            guess = "4536";
            sol = new GuessNumber().new Solution(guess);
            char[] res = guess.toCharArray();
            Arrays.sort(res);
            assertEquals(new String(res), sol.guess());

            guess = "1111";
            sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());

            guess = "6666";
            sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());

            guess = "2266";
            sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());

            guess = "5566";
            sol = new GuessNumber().new Solution(guess);
            assertEquals(guess, sol.guess());
        }
    }
}

