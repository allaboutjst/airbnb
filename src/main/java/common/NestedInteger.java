package common;

//import nested_list_weight_sum.NestedListWeightSum;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
    private List<NestedInteger> list;
    private Integer integer;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        this.list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.integer = value;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return integer != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return integer;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.integer = value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        if (this.list != null) {
            this.list.add(ni);
        } else {
            this.list = new ArrayList<>();
            this.list.add(ni);
        }
    }

    // Returns the nested list that this com.sada.linkedin.NestedInteger holds, if it holds a nested list
    // Returns null if this com.sada.linkedin.NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }

    public String printNi() {
        if (this.isInteger()) {
            return String.valueOf(integer);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).printNi());
                if (i != list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
