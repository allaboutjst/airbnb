package water_land;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class WaterLand {
    /*
        Water Land / Water Drop / Pour Water
        AirBnB Interview Question
     */
    public class Solution {
        public void pourWater(int[] heights, int location, int water) {
            int[] waters = new int[heights.length];
            int pourLocation;

            while (water > 0) {
                int left = location - 1;
                while (left >= 0) {
                    if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                        break;
                    }
                    left--;
                }
                if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
                    pourLocation = left + 1;
                    waters[pourLocation]++;
                    water--;
                    continue;
                }

                int right = location + 1;
                while (right < heights.length) {
                    if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
                        break;
                    }
                    right++;
                }
                if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
                    pourLocation = right - 1;
                    waters[pourLocation]++;
                    water--;
                    continue;
                }

                pourLocation = location;
                waters[pourLocation]++;
                water--;
            }

            print(heights, waters);
        }

        private void print(int[] heights, int[] waters) {
            int n = heights.length;

            int maxHeight = 0;
            for (int i = 0; i < n; i++) {
                maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
            }

            for (int height = maxHeight; height >= 0; height--) {
                for (int i = 0; i < n; i++) {
                    if (height <= heights[i]) {
                        System.out.print("+");
                    } else if (height > heights[i] && height <= heights[i] + waters[i]) {
                        System.out.print("W");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new WaterLand().new Solution();
            int[] waterLand = new int[]{5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
            sol.pourWater(waterLand, 5, 1);
            sol.pourWater(waterLand, 5, 5);
            sol.pourWater(waterLand, 5, 10);
            sol.pourWater(waterLand, 5, 20);
            sol.pourWater(waterLand, 5, 30);
            sol.pourWater(waterLand, 5, 50);
            sol.pourWater(waterLand, 5, 100);

            waterLand = new int[]{5, 4, 2, 1, 3, 2, 2, 1, 0, 1, 4, 3};
            sol.pourWater(waterLand, 4, 1);
            sol.pourWater(waterLand, 4, 5);
            sol.pourWater(waterLand, 4, 10);
            sol.pourWater(waterLand, 4, 20);
            sol.pourWater(waterLand, 4, 30);
            sol.pourWater(waterLand, 4, 50);
            sol.pourWater(waterLand, 4, 100);
        }
    }
}

