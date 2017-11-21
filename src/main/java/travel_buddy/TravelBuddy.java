package travel_buddy;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TravelBuddy {
    /*
        Travel Buddy
        http://www.1point3acres.com/bbs/thread-214074-1-1.html
        Difficulty: Medium
     */
    public class Solution {
        private List<Buddy> buddies;
        private Set<String> myWishList;

        public Solution(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
            this.buddies = new ArrayList<>();
            this.myWishList = myWishList;
            for (String name : friendsWishList.keySet()) {
                Set<String> wishList = friendsWishList.get(name);
                Set<String> intersection = new HashSet<>(wishList);
                intersection.retainAll(myWishList);
                int similarity = intersection.size();
                if (similarity >= wishList.size() / 2) {
                    buddies.add(new Buddy(name, similarity, wishList));
                }
            }
        }

        public List<Buddy> getSortedBuddies() {
            Collections.sort(buddies);
            List<Buddy> res = new ArrayList<>(buddies);
            return res;
        }

        public List<String> recommendCities(int k) {
            List<String> res = new ArrayList<>();
            List<Buddy> buddies = getSortedBuddies();

            int i = 0;
            while (k > 0 && i < buddies.size()) {
                Set<String> diff = new HashSet<>(buddies.get(i).wishList);
                diff.removeAll(myWishList);
                if (diff.size() <= k) {
                    res.addAll(diff);
                    k -= diff.size();
                    i++;
                } else {
                    Iterator<String> it = diff.iterator();
                    while (k > 0) {
                        res.add(it.next());
                        k--;
                    }
                }
            }

            return res;
        }

        class Buddy implements Comparable<Buddy> {
            String name;
            int similarity;
            Set<String> wishList;

            Buddy(String name, int similarity, Set<String> wishList) {
                this.name = name;
                this.similarity = similarity;
                this.wishList = wishList;
            }

            @Override
            public int compareTo(Buddy that) {
                return that.similarity - this.similarity;
            }
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Set<String> myWishList = new HashSet<>(Arrays.asList(new String[]{"a", "b", "c", "d"}));
            Set<String> wishList1 = new HashSet<>(Arrays.asList(new String[]{"a", "b", "e", "f"}));
            Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[]{"a", "c", "d", "g"}));
            Set<String> wishList3 = new HashSet<>(Arrays.asList(new String[]{"c", "f", "e", "g"}));
            Map<String, Set<String>> friendWishLists = new HashMap<>();
            friendWishLists.put("Buddy1", wishList1);
            friendWishLists.put("Buddy2", wishList2);
            friendWishLists.put("Buddy3", wishList3);
            Solution sol = new TravelBuddy().new Solution(myWishList, friendWishLists);
            List<String> res = sol.recommendCities(10);
            assertEquals(3, res.size());
            assertEquals("g", res.get(0));
            assertEquals("e", res.get(1));
            assertEquals("f", res.get(2));
        }
    }
}

