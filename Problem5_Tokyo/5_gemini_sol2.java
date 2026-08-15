import java.io.*;
import java.util.*;

class Main {
    static class Item implements Comparable<Item> {
        long val;
        int pos;

        Item(long v, int p) {
            val = v;
            pos = p;
        }

        @Override
        public int compareTo(Item o) {
            if (this.val < o.val)
                return -1;
            if (this.val > o.val)
                return 1;
            return Integer.compare(this.pos, o.pos);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && line.trim().isEmpty())
            ;
        if (line == null)
            return;

        int t = Integer.parseInt(line.trim());
        StringBuilder out = new StringBuilder();

        for (int k = 0; k < t; k++) {
            int n = Integer.parseInt(reader.readLine().trim());
            Item[] prefix = new Item[n + 1];
            prefix[0] = new Item(0L, 0);
            long runningSum = 0;

            for (int i = 1; i <= n; i++) {
                String[] parts = reader.readLine().trim().split("\\s+");
                char d = parts[0].charAt(0);
                long a = Long.parseLong(parts[1]);

                if (d == 'L') {
                    runningSum += a;
                } else {
                    runningSum -= a;
                }
                prefix[i] = new Item(runningSum, i);
            }

            Arrays.sort(prefix);

            int ansL = 0;
            int ansS = Integer.MAX_VALUE;
            int ansE = 0;

            int i = 0;
            while (i <= n) {
                int j = i;
                while (j <= n && prefix[j].val == prefix[i].val) {
                    j++;
                }
                if (j - i >= 2) {
                    int len = prefix[j - 1].pos - prefix[i].pos;
                    if (len > ansL) {
                        ansL = len;
                        ansS = prefix[i].pos + 1;
                        ansE = prefix[j - 1].pos;
                    } else if (len == ansL) {
                        if (prefix[i].pos + 1 < ansS) {
                            ansS = prefix[i].pos + 1;
                            ansE = prefix[j - 1].pos;
                        }
                    }
                }
                i = j;
            }

            if (ansL == 0) {
                out.append("0 0 0\n");
            } else {
                out.append(ansL).append(" ").append(ansS).append(" ").append(ansE).append("\n");
            }
        }
        System.out.print(out.toString());
    }
}