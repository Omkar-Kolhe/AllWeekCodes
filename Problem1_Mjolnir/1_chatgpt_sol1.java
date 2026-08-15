import java.io.*;
import java.util.*;

class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder result = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            long r = fs.nextLong();

            long sCap = fs.nextLong();
            long wCap = fs.nextLong();

            int[] impact = new int[n];
            int[] cost = new int[n];

            long totalResistance = r;

            for (int i = 0; i < n; i++) {
                int s = fs.nextInt();
                int w = fs.nextInt();
                int c = fs.nextInt();

                impact[i] = Math.max(0, s - w);
                cost[i] = c;

                totalResistance += impact[i];
            }

            long maximumResistance = sCap + wCap;

            if (totalResistance <= maximumResistance) {
                result.append(0).append('\n');
                continue;
            }

            int required = (int) (totalResistance - maximumResistance);

            int[] dp = new int[required + 1];
            Arrays.fill(dp, INF);
            dp[0] = 0;

            for (int i = 0; i < n; i++) {
                for (int removed = required; removed >= 0; removed--) {
                    if (dp[removed] == INF) {
                        continue;
                    }

                    int newRemoved = Math.min(
                            required,
                            removed + impact[i]);

                    dp[newRemoved] = Math.min(
                            dp[newRemoved],
                            dp[removed] + cost[i]);
                }
            }

            result.append(dp[required] == INF ? -1 : dp[required])
                    .append('\n');
        }

        System.out.print(result);
    }

    static class FastScanner {
        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        FastScanner(InputStream input) {
            this.input = input;
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = input.read(buffer);
                pointer = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        long nextLong() throws IOException {
            int ch;

            do {
                ch = read();
            } while (ch <= ' ');

            long value = 0;

            while (ch > ' ') {
                value = value * 10 + (ch - '0');
                ch = read();
            }

            return value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}