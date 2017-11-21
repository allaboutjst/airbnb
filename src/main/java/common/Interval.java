package common;

/*
    Define Interval Class
 */
public class Interval {
  public int start;
  public int end;

  public Interval() {
    start = 0;
    end = 0;
  }

  public Interval(int s, int e) {
    start = s;
    end = e;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Interval that = (Interval) o;
    return start == that.start && end == that.end;
  }

  @Override
  public int hashCode() {
    return 31 * start + end;
  }
}
