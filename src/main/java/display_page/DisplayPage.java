package display_page;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class DisplayPage {
    /*
        Host Crowding

        You’re given an array of CSV strings representing search results. Results are sorted
        by a score initially. A given host may have several listings that show up in these results. Suppose we want to show 12 results per page, but we don’t want the same host to dominate
        the results. Write a function that will reorder the list so that a host shows up at most
        once on a page if possible, but otherwise preserves the ordering. Your program should return
        the new array and print out the results in blocks representing the pages.

        Input:
        *  An array of csv strings, with sort score
        *  number of results per page.
     */

    /*
        Display Page - LinkedList + HashSet
        Difficulty: Medium
     */
    public class Solution {
        public List<String> displayPages(List<String> input, int pageSize) {
            List<String> res = new ArrayList<>();
            if (input == null || input.size() == 0) {
                return res;
            }

            List<String> visited = new ArrayList<>();
            Iterator<String> iter = input.iterator();
            boolean reachEnd = false;
            while (iter.hasNext()) {
                String curr = iter.next();
                String hostId = curr.split(",")[0];
                if (!visited.contains(hostId) || reachEnd ) {
                    res.add(curr);
                    visited.add(hostId);
                    iter.remove();
                }

                if (visited.size() == pageSize) {
                    visited.clear();
                    reachEnd = false;
                    if (!input.isEmpty()) {
                        res.add(" ");
                    }
                    iter = input.iterator();
                }

                if (!iter.hasNext()) {
                    iter = input.iterator();
                    reachEnd = true;
                }
            }

            return res;
        }
    }

    /*
        Display Page - LinkedList + HashSet
        fill full for each page
        Difficulty: Medium
     */
    public class Solution_2 {
        public List<String> displayPages(List<String> input, int pageSize) {
            List<String> res = new ArrayList<>();
            if (input == null || input.size() == 0) {
                return res;
            }

            List<String> visited = new ArrayList<>();
            Iterator<String> iter = input.iterator();
            boolean reachEnd = false;
            while (iter.hasNext()) {
                String curr = iter.next();
                String hostId = curr.split(",")[0];
                if (!visited.contains(hostId) || reachEnd ) {
                    res.add(curr);
                    visited.add(hostId);
                    iter.remove();
                }

                if (visited.size() == pageSize) {
                    visited.clear();
                    reachEnd = false;
                    if (!input.isEmpty()) {
                        res.add(" ");
                    }
                    iter = input.iterator();
                }

                if (!iter.hasNext()) {
                    iter = input.iterator();
                    reachEnd = true;
                }
            }

            return res;
        }
    }

    /*
        Display Page - LinkedList + HashSet
        fill full for each page
        Difficulty: Medium
     */
    public class Solution_3 {
        public List<String> displayPages(List<String> input, int pageSize) {
            List<String> res = new ArrayList<>();
            Iterator<String> iter = input.iterator();
            Set<String> set = new HashSet<>();
            boolean reachEnd = false;
            int counter = 0;
            while(iter.hasNext()) {
                String cur = iter.next();
                String id = (cur.split(","))[0];
                if (!set.contains(id) || reachEnd) {
                    res.add(cur);
                    set.add(id);
                    iter.remove();
                    counter++;
                }

                if (counter == pageSize) {
                    if (!input.isEmpty())
                        res.add(" ");
                    set.clear();
                    counter = 0;
                    reachEnd = false;
                    iter = input.iterator();
                }

                if (!iter.hasNext()) {
                    reachEnd = true;
                    iter = input.iterator();
                }
            }

            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new DisplayPage().new Solution();
            String[] strs = new String[]{
                    "1,28,300.1,SanFrancisco",
                    "4,5,209.1,SanFrancisco",
                    "20,7,208.1,SanFrancisco",
                    "23,8,207.1,SanFrancisco",
                    "16,10,206.1,Oakland",
                    "1,16,205.1,SanFrancisco",
                    "6,29,204.1,SanFrancisco",
                    "7,20,203.1,SanFrancisco",
                    "8,21,202.1,SanFrancisco",
                    "2,18,201.1,SanFrancisco",
                    "2,30,200.1,SanFrancisco",
                    "15,27,109.1,Oakland",
                    "10,13,108.1,Oakland",
                    "11,26,107.1,Oakland",
                    "12,9,106.1,Oakland",
                    "13,1,105.1,Oakland",
                    "22,17,104.1,Oakland",
                    "1,2,103.1,Oakland",
                    "28,24,102.1,Oakland",
                    "18,14,11.1,SanJose",
                    "6,25,10.1,Oakland",
                    "19,15,9.1,SanJose",
                    "3,19,8.1,SanJose",
                    "3,11,7.1,Oakland",
                    "27,12,6.1,Oakland",
                    "1,3,5.1,Oakland",
                    "25,4,4.1,SanJose",
                    "5,6,3.1,SanJose",
                    "29,22,2.1,SanJose",
                    "30,23,1.1,SanJose"
            };
            List<String> input = new ArrayList<>(Arrays.asList(strs));
            List<String> result = sol.displayPages(input, 12);
            assertEquals(32, result.size());
            assertEquals("1,28,300.1,SanFrancisco", result.get(0));
            assertEquals("11,26,107.1,Oakland", result.get(11));
            assertEquals(" ", result.get(12));
            assertEquals("1,16,205.1,SanFrancisco", result.get(13));
            assertEquals("2,30,200.1,SanFrancisco", result.get(14));
            assertEquals("25,4,4.1,SanJose", result.get(24));
            assertEquals(" ", result.get(25));
            assertEquals("1,2,103.1,Oakland", result.get(26));
            assertEquals("3,11,7.1,Oakland", result.get(27));
            assertEquals("30,23,1.1,SanJose", result.get(30));
            assertEquals("1,3,5.1,Oakland", result.get(31));
        }

        @Test
        public void test2() {
            Solution_2 sol = new DisplayPage().new Solution_2();
            String[] strs = new String[]{
                    "1,28,310.6,SF",
                    "4,5,204.1,SF",
                    "20,7,203.2,Oakland",
                    "6,8,202.2,SF",
                    "6,10,199.1,SF",
                    "1,16,190.4,SF",
                    "6,29,185.2,SF",
                    "7,20,180.1,SF",
                    "6,21,162.1,SF",
                    "2,18,161.2,SF",
                    "2,30,149.1,SF",
                    "3,76,146.2,SF",
                    "2,14,141.1,San Jose"

            };
            List<String> input = new ArrayList<>(Arrays.asList(strs));
            List<String> result = sol.displayPages(input, 5);
            assertEquals(15, result.size());
            assertEquals("1,28,310.6,SF", result.get(0));
            assertEquals("7,20,180.1,SF", result.get(4));
            assertEquals(" ", result.get(5));
            assertEquals("6,10,199.1,SF", result.get(6));
            assertEquals("6,29,185.2,SF", result.get(10));
            assertEquals(" ", result.get(11));
            assertEquals("6,21,162.1,SF", result.get(12));
            assertEquals("2,14,141.1,San Jose", result.get(14));
        }

        @Test
        public void test3() {
            Solution_3 sol = new DisplayPage().new Solution_3();
            String[] strs = new String[]{
                    "1,28,310.6,SF",
                    "4,5,204.1,SF",
                    "20,7,203.2,Oakland",
                    "6,8,202.2,SF",
                    "6,10,199.1,SF",
                    "1,16,190.4,SF",
                    "6,29,185.2,SF",
                    "7,20,180.1,SF",
                    "6,21,162.1,SF",
                    "2,18,161.2,SF",
                    "2,30,149.1,SF",
                    "3,76,146.2,SF",
                    "2,14,141.1,San Jose"

            };
            List<String> input = new ArrayList<>(Arrays.asList(strs));
            List<String> result = sol.displayPages(input, 5);
            assertEquals(15, result.size());
            assertEquals("1,28,310.6,SF", result.get(0));
            assertEquals("7,20,180.1,SF", result.get(4));
            assertEquals(" ", result.get(5));
            assertEquals("6,10,199.1,SF", result.get(6));
            assertEquals("6,29,185.2,SF", result.get(10));
            assertEquals(" ", result.get(11));
            assertEquals("6,21,162.1,SF", result.get(12));
            assertEquals("2,14,141.1,San Jose", result.get(14));
        }
    }
}

