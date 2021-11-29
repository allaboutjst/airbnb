import java.util.*;
public class ListofListIterator {
    public class Solution_2 implements Iterator<Integer> {
        private Iterator<List<Integer>> rowIter;
        private Iterator<Integer> colIter;

        public Solution_2(List<List<Integer>> vec2d) {
            rowIter = vec2d.iterator();
            colIter = Collections.emptyIterator();
        }

        @Override
        public Integer next() {
            return colIter.next();
        }

        @Override
        public boolean hasNext() {
            while ((colIter == null || !colIter.hasNext()) && rowIter.hasNext())
                colIter = rowIter.next().iterator();
            return colIter != null && colIter.hasNext();
        }

        @Override
        public void remove() {
            while (colIter == null && rowIter.hasNext())
                colIter = rowIter.next().iterator();
            if (colIter != null)
                colIter.remove();
        }
    }
    public static void main(String[] args) {
            List<List<Integer>> test = new ArrayList<>();
            test.add(new ArrayList<Integer>() {{
                add(1);
                add(2);
            }});
            test.add(new ArrayList<Integer>() {{
                add(3);
            }});
            test.add(new ArrayList<Integer>() {{
                add(4);
                add(5);
                add(6);
            }});
            Solution_2 sol = new ListofListIterator().new Solution_2(test);
            if(sol.hasNext()) System.out.println(sol.next());
            sol.remove();
            if(sol.hasNext()) System.out.println(sol.next());
            sol.remove();
            if(sol.hasNext()) sol.next();
            if(sol.hasNext()) sol.next();
            sol.remove();

            while(sol.hasNext()) System.out.println(sol.next());

            System.out.println("----------");
            sol = new ListofListIterator().new Solution_2(test);
            while(sol.hasNext()) System.out.println(sol.next()); // prints 3,5,6 since 1,2,4 have been removed
        }
}
