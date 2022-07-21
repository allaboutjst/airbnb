package simulate_diplomacy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class SimulateDiplomacy {

	public static class Army implements Comparable<Army> {
		String name;
		String location;
		int strength;

		Army(String n, String l) {
			this.name = n;
			this.location = l;
			this.strength = 1;
		}

		@Override
		public int compareTo(Army a) {
			return name.compareTo(a.name);
		}

		@Override
		public String toString() {
			return name + " " + (strength == 0 ? "[dead]" : location);
		}
	}

	public static class Solution {

		List<String> play(List<String> actions) {
			List<Army> armies = new ArrayList<>();
			Map<String, Army> indexes = new HashMap<>();
			List<String[]> supports = new ArrayList<>();
			for (String action : actions) {
				String[] tokens = action.split(" ");
				String name = tokens[0];
				String location = tokens[1];
				Army a = indexes.get(name);
				if (a == null) {
					a = new Army(name, location);
					armies.add(a);
					indexes.put(a.name, a);
				}
				String act = tokens[2];
				switch (act) {
					case "Move":
						a.location = tokens[3];
						break;
					case "Support":
						supports.add(new String[] { a.name, tokens[3] });
						break;
				}
			}

			Map<String, List<Army>> attacks = new HashMap<>();
			for (Army a : armies) {
				attacks.compute(a.location, (s, list) -> {
					if (list == null)
						list = new ArrayList<>();
					list.add(a);
					return list;
				});
			}
			// Deal with the attacks
			for (Map.Entry<String, List<Army>> e : attacks.entrySet()) {
				List<Army> list = e.getValue();
				if (list.size() > 1) {
					for (Army a : list) {
						a.strength = 0;
					}
				}
			}
			// Deal with the support, especially the case where the armies receive more than one support
			for (String[] sup : supports) {
				String s1 = sup[0];
				String s2 = sup[1];
				Army a1 = indexes.get(s1);
				Army a2 = indexes.get(s2);
				if (a1.strength > 0) {
					a2.strength++;
				}
			}
			for (Map.Entry<String, List<Army>> e : attacks.entrySet()) {
				List<Army> list = e.getValue();
				if (list.size() > 1) {
					int strongest = 0;
					int max = Integer.MIN_VALUE;
					for (int i = 0; i < list.size(); i++) {
						Army temp = list.get(i);
						if (temp.strength > max) {
							max = temp.strength;
							strongest = i;
						} else if (temp.strength == max) {
							list.get(strongest).strength = 0;
							temp.strength = 0;
						}
					}
				}
			}
			// Sort the list
			Collections.sort(armies);
			List<String> res = new ArrayList<>();
			for (Army a : armies) {
				res.add(a.toString());
			}
			return res;
		}
	}

	public static class UnitTest {
		@Test
		public void test1() {
			List<String> actions = new ArrayList<>();
			actions.add("A Paris Hold");
			actions.add("B Spain Move Paris");
			actions.add("C London Move Paris");
			List<String> res = new Solution().play(actions);
			Assert.assertEquals("A [dead]", res.get(0));
			Assert.assertEquals("B [dead]", res.get(1));
			Assert.assertEquals("C [dead]", res.get(2));

			actions.clear();
			actions.add("A Paris Support B");
			actions.add("B Spain Move Paris");
			actions.add("C London Move Paris");
			List<String> res2 = new Solution().play(actions);
			Assert.assertEquals("A [dead]", res2.get(0));
			Assert.assertEquals("B [dead]", res2.get(1));
			Assert.assertEquals("C [dead]", res2.get(2));

			actions.clear();
			actions.add("A Brussels Support B");
			actions.add("B Spain Move Paris");
			actions.add("C London Move Paris");
			List<String> res3 = new Solution().play(actions);
			Assert.assertEquals("A Brussels", res3.get(0));
			Assert.assertEquals("B Paris", res3.get(1));
			Assert.assertEquals("C [dead]", res3.get(2));

			actions.clear();
			actions.add("A Brussels Support B");
			actions.add("B Spain Move Paris");
			actions.add("C London Move Paris");
			actions.add("D Brussels Hold");
			List<String> res4 = new Solution().play(actions);
			Assert.assertEquals("A [dead]", res4.get(0));
			Assert.assertEquals("B [dead]", res4.get(1));
			Assert.assertEquals("C [dead]", res4.get(2));
			Assert.assertEquals("D [dead]", res4.get(3));

			actions.clear();
			actions.add("A Brussels Support B");
			actions.add("B Spain Move Paris");
			actions.add("C London Move Paris");
			actions.add("D London Support C");
			List<String> res5 = new Solution().play(actions);
			Assert.assertEquals("A Brussels", res5.get(0));
			Assert.assertEquals("B [dead]", res5.get(1));
			Assert.assertEquals("C [dead]", res5.get(2));
			Assert.assertEquals("D London", res5.get(3));
		}
	}
}
