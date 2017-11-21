package round_prices;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class RoundPrices {
    /*
        Round Prices
        http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146539
        Difficulty: Medium
     */
    public class Solution {
        // 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
        // 转的时候按照num本身与ceil的距离排序
        public int[] roundUp(double[] arr) {
            int n = arr.length;
            NumWithDiff[] arrWithDiff = new NumWithDiff[n];
            double sum = 0.0;
            int floorSum = 0;
            for (int i = 0; i < n; i++) {
                int floor = (int) arr[i];
                int ceil = floor;
                if (floor < arr[i]) ceil++; // 查是不是4.0这种floor/ceil都是本身的
                floorSum += floor;
                sum += arr[i];
                arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]); // 把ceil都放在数组里面进行比较
            }

            int num = (int) Math.round(sum);
            int diff = num - floorSum;
            Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
                @Override
                public int compare(NumWithDiff n1, NumWithDiff n2) {
                    if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
                    else return 1;
                }
            });
            // Arrays.sort(arrWithDiff, (a, b) -> (Double.compare(b.diffWithCeil, a.diffWithCeil)));

            int[] res = new int[n];
            int i = 0;
            for (; i < diff; i++) {
                res[i] = arrWithDiff[i].num; // 这些放ceil
            }
            for (; i < n; i++) {
                res[i] = arrWithDiff[i].num - 1; // 剩下的只放floor
            }
            return res;
        }

        class NumWithDiff {
            int num;
            double diffWithCeil;

            public NumWithDiff(int n, double c) {
                this.num = n;
                this.diffWithCeil = c;
            }
        }
    }

    /*
        Round Prices
        http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146539
        Difficulty: Medium
     */
    public class Solution_2 {
        public int[] roundUp(double[] prices) {
            if (prices == null || prices.length == 0) {
                return new int[0];
            }

            int[] res = new int[prices.length];

            double sum = 0;
            int roundSum = 0;
            Number[] numbers = new Number[prices.length];
            for (int i = 0; i < prices.length; i++) {
                numbers[i] = new Number(prices[i], i);
                sum += prices[i];
                roundSum += (int) Math.round(prices[i]);
                res[i] = (int) Math.round(prices[i]);
            }
            int sumRound = (int) Math.round(sum);

            if (sumRound == roundSum) {
                return res;
            } else if (sumRound > roundSum) {
                Arrays.sort(numbers, (a, b) -> (Double.compare(b.frac, a.frac)));
                int count = sumRound - roundSum;
                for (int i = 0; i < prices.length; i++) {
                    Number num = numbers[i];
                    if (num.frac < 0.5 && count > 0) {
                        res[num.index] = (int) Math.ceil(num.val);
                        count--;
                    } else {
                        res[num.index] = (int) Math.round(num.val);
                    }
                }
            } else {
                Arrays.sort(numbers, (a, b) -> (Double.compare(a.frac, b.frac)));
                int count = roundSum - sumRound;
                for (int i = 0; i < prices.length; i++) {
                    Number num = numbers[i];
                    if (num.frac >= 0.5 && count > 0) {
                        res[num.index] = (int) Math.floor(num.val);
                        count--;
                    } else {
                        res[num.index] = (int) Math.round(num.val);
                    }
                }
            }

            return res;
        }

        class Number {
            double val;
            double frac;
            int index;

            Number(double val, int index) {
                this.val = val;
                this.frac = val - Math.floor(val);
                this.index = index;
            }
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new RoundPrices().new Solution();
            double[] arr = {1.2, 3.7, 2.3, 4.8};
            int[] res = sol.roundUp(arr);
            assertEquals(5, res[0]);
            assertEquals(4, res[1]);
            assertEquals(2, res[2]);
            assertEquals(1, res[3]);

            arr = new double[]{1.2, 2.3, 3.4};
            res = sol.roundUp(arr);
            System.out.println(res);
            assertEquals(4, res[0]);
            assertEquals(2, res[1]);
            assertEquals(1, res[2]);

            arr = new double[]{1.2, 3.7, 2.3, 4.8};
            res = sol.roundUp(arr);
            assertEquals(5, res[0]);
            assertEquals(4, res[1]);
            assertEquals(2, res[2]);
            assertEquals(1, res[3]);

            arr = new double[]{1.2, 2.5, 3.6, 4.0};
            res = sol.roundUp(arr);
            assertEquals(4, res[0]);
            assertEquals(3, res[1]);
            assertEquals(2, res[2]);
            assertEquals(1, res[3]);
        }

        @Test
        public void test2() {
            Solution_2 sol = new RoundPrices().new Solution_2();
            double[] arr = {1.2, 2.3, 3.4};
            int[] res = sol.roundUp(arr);
            System.out.println(res);
            assertEquals(1, res[0]);
            assertEquals(2, res[1]);
            assertEquals(4, res[2]);

            arr = new double[]{1.2, 3.7, 2.3, 4.8};
            res = sol.roundUp(arr);
            assertEquals(1, res[0]);
            assertEquals(4, res[1]);
            assertEquals(2, res[2]);
            assertEquals(5, res[3]);

            arr = new double[]{1.2, 2.5, 3.6, 4.0};
            res = sol.roundUp(arr);
            assertEquals(1, res[0]);
            assertEquals(2, res[1]);
            assertEquals(4, res[2]);
            assertEquals(4, res[3]);

            arr = new double[]{2.5, 2.3, 3.1, 6.5};
            res = sol.roundUp(arr);
            assertEquals(2, res[0]);
            assertEquals(2, res[1]);
            assertEquals(3, res[2]);
            assertEquals(7, res[3]);

            arr = new double[]{2.9, 2.3, 1.4, 3, 6};
            res = sol.roundUp(arr);
            assertEquals(3, res[0]);
            assertEquals(2, res[1]);
            assertEquals(2, res[2]);
            assertEquals(3, res[3]);
            assertEquals(6, res[4]);

            arr = new double[]{-0.4, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3};
            res = sol.roundUp(arr);
            assertEquals(0, res[0]);
            assertEquals(2, res[1]);
            assertEquals(2, res[2]);
            assertEquals(2, res[3]);
            assertEquals(1, res[4]);
            assertEquals(1, res[5]);
            assertEquals(1, res[6]);
            assertEquals(1, res[7]);
            assertEquals(1, res[8]);
            assertEquals(1, res[9]);
            assertEquals(1, res[10]);
        }
    }
}

