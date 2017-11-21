package ip_range_to_cidr;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class IPRangetoCIDR {
    /*
        IP range to CIDR
        https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
        http://www.ipaddressguide.com/cidr
        https://stackoverflow.com/questions/33443914/how-to-convert-ip-address-range-to-cidr-in-java
        Difficulty: Medium
     */
    public class Solution {
        private long ipToLong(String strIP) {
            long[] ip = new long[4];
            String[] ipSec = strIP.split("\\.");
            for (int k = 0; k < 4; k++) {
                ip[k] = Long.valueOf(ipSec[k]);
            }

            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        }

        private String longToIP(long longIP) {
            StringBuffer sb = new StringBuffer("");
            sb.append(String.valueOf(longIP >>> 24));
            sb.append(".");
            sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
            sb.append(".");
            sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
            sb.append(".");
            sb.append(String.valueOf(longIP & 0x000000FF));

            return sb.toString();
        }

        public List<String> ipRange2Cidr(String startIp, int range) {
            // check parameters
            String a = "";
            long start = ipToLong(startIp);
            long end = start + range - 1;
            List<String> res = new ArrayList<>();
            while (start <= end) {
                // identify the location of first 1's from lower bit to higher bit of start IP
                // e.g. 00000001.00000001.00000001.01101100, return 4 (100)
                long locOfFirstOne = start & (-start);
                int curMask = 32 - (int) (Math.log(locOfFirstOne) / Math.log(2));

                // calculate how many IP addresses between the start and end
                // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
                // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
                double currRange = Math.log(end - start + 1) / Math.log(2);
                int currRangeMask = 32 - (int) Math.floor(currRange);

                // why max?
                // if the currRangeMask is larger than curMask
                // which means the numbers of IPs from start to end is smaller than mask range
                // so we can't use as many as bits we want to mask the start IP to avoid exceed the end IP
                // Otherwise, if currRangeMask is smaller than curMask, which means number of IPs is larger than mask range
                // in this case we can use curMask to mask as many as IPs from start we want.
                curMask = Math.max(currRangeMask, curMask);

                // Add to results
                String ip = longToIP(start);
                res.add(ip + "/" + curMask);
                // We have already included 2^(32 - curMask) numbers of IP into result
                // So the next roundUp start must insert that number
                start += Math.pow(2, (32 - curMask));
            }
            return res;
        }
    }

    /*
        IP range to CIDR
        https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
        http://www.ipaddressguide.com/cidr
        Difficulty: Medium
     */
    public class Solution_2 {
        private long ipToLong(String ip) {
            long res = 0;
            String[] ips = ip.split("\\.");
            for (int i = 0; i < 4; i++) {
                res += (long) Integer.valueOf(ips[i]) << ((4 - i - 1) * 8);
            }
            return res;
        }

        private String longToIp(long num) {
            String[] ips = new String[4];
            ips[0] = String.valueOf(num >> 24);
            ips[1] = String.valueOf((num & 0x00FFFFFF) >> 16);
            ips[2] = String.valueOf((num & 0x0000FFFF) >> 8);
            ips[3] = String.valueOf(num & 0x000000FF);
            return String.join(".", ips);
        }

        public List<String> ipRange2Cidr(String startIp, int range) {
            long start = ipToLong(startIp);
            long end = start + range - 1;
            List<String> res = new ArrayList<>();
            while (start <= end) {
                long locOfFirstOne = start & (-start);
                int curMask = 32 - (int) (Math.log(locOfFirstOne) / Math.log(2));
                double currRange = Math.log(end - start + 1) / Math.log(2);
                int currRangeMask = 32 - (int) (Math.log(end - start + 1) / Math.log(2));
                curMask = Math.max(curMask, currRangeMask);
                res.add(longToIp(start) + "/" + curMask);
                start += (int) Math.pow(2, (32-curMask));
            }
            return res;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new IPRangetoCIDR().new Solution();
            List<String> res = sol.ipRange2Cidr("255.0.0.7", 10);
            assertEquals(3, res.size());
            assertEquals("255.0.0.7/32", res.get(0));
            assertEquals("255.0.0.8/29", res.get(1));
            assertEquals("255.0.0.16/32", res.get(2));

            res = sol.ipRange2Cidr("1.1.1.0", 4);
            assertEquals(1, res.size());
            assertEquals("1.1.1.0/30", res.get(0));

            res = sol.ipRange2Cidr("1.1.1.1", 4);
            assertEquals(3, res.size());
            assertEquals("1.1.1.1/32", res.get(0));
            assertEquals("1.1.1.2/31", res.get(1));
            assertEquals("1.1.1.4/32", res.get(2));
        }

        @Test
        public void test2() {
            Solution_2 sol = new IPRangetoCIDR().new Solution_2();
            List<String> res = sol.ipRange2Cidr("255.0.0.7", 10);
            assertEquals(3, res.size());
            assertEquals("255.0.0.7/32", res.get(0));
            assertEquals("255.0.0.8/29", res.get(1));
            assertEquals("255.0.0.16/32", res.get(2));

            res = sol.ipRange2Cidr("1.1.1.0", 4);
            assertEquals(1, res.size());
            assertEquals("1.1.1.0/30", res.get(0));

            res = sol.ipRange2Cidr("1.1.1.1", 4);
            assertEquals(3, res.size());
            assertEquals("1.1.1.1/32", res.get(0));
            assertEquals("1.1.1.2/31", res.get(1));
            assertEquals("1.1.1.4/32", res.get(2));
        }
    }
}

