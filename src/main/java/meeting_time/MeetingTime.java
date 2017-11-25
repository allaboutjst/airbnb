package meeting_time;

import java.util.*;

import common.Interval;
import org.junit.*;

import static org.junit.Assert.*;

public class MeetingTime {

    /*
        Meeting Time
        AirBnB Interview Question
     */
    public class Solution {
        class Point implements Comparable<Point> {
            int time;
            boolean isStart;

            Point(int time, boolean isStart) {
                this.time = time;
                this.isStart = isStart;
            }

            @Override
            public int compareTo(Point that) {
                if (this.time != that.time || this.isStart == that.isStart) {
                    return this.time - that.time;
                } else {
                    return this.isStart ? -1 : 1;
                }
            }
        }

        public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
            List<Interval> res = new ArrayList<>();
            List<Point> points = new ArrayList<>();

            for (List<Interval> intervalList : intervals) {
                for (Interval interval : intervalList) {
                    points.add(new Point(interval.start, true));
                    points.add(new Point(interval.end, false));
                }
            }
            Collections.sort(points);

            int count = 0;
            Integer availableStart = null;
            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                if (point.isStart) {
                    count++;
                    if (availableStart == null && i == 0 && count <= intervals.size() - k) {
                        availableStart = point.time;
                    } else if (availableStart != null && count == intervals.size() - k + 1) {
                        res.add(new Interval(availableStart, point.time));
                        availableStart = null;
                    }
                } else {
                    count--;
                    if (count == intervals.size() - k && i < points.size() - 1) {
                        availableStart = point.time;
                    } else if (availableStart != null && i == points.size() - 1 && count <= intervals.size() - k) {
                        res.add(new Interval(availableStart, point.time));
                        availableStart = null;
                    }
                }
            }

            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new MeetingTime().new Solution();
            List<List<Interval>> intervals = new ArrayList<List<Interval>>() {{
                add(new ArrayList<Interval>() {{
                    add(new Interval(1, 3));
                    add(new Interval(6, 7));
                }});
                add(new ArrayList<Interval>() {{
                    add(new Interval(2, 4));
                }});
                add(new ArrayList<Interval>() {{
                    add(new Interval(2, 3));
                    add(new Interval(9, 12));
                }});
            }};
            List<Interval> res = sol.getAvailableIntervals(intervals, 3);
            assertEquals(2, res.size());
            assertEquals(4, res.get(0).start);
            assertEquals(6, res.get(0).end);
            assertEquals(7, res.get(1).start);
            assertEquals(9, res.get(1).end);
        }
    }
}

