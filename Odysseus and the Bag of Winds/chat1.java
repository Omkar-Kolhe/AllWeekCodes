import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int m = fs.nextInt();
            int a = fs.nextInt() - 1;

            long[][] cost = new long[m][m];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    cost[i][j] = fs.nextLong();
                }
            }

            int masks = 1 << m;
            long[][][] dp = new long[masks][m][2];

            for (int mask = 0; mask < masks; mask++) {
                for (int last = 0; last < m; last++) {
                    Arrays.fill(dp[mask][last], INF);
                }
            }

            dp[1][0][0] = 0;

            for (int mask = 1; mask < masks; mask++) {
                if ((mask & 1) == 0) continue;

                boolean reachedA = (mask & (1 << a)) != 0;

                for (int last = 0; last < m; last++) {
                    if ((mask & (1 << last)) == 0) continue;

                    for (int used = 0; used < 2; used++) {
                        long cur = dp[mask][last][used];

                        if (cur == INF) continue;

                        for (int next = 0; next < m; next++) {
                            if ((mask & (1 << next)) != 0) continue;

                            int nextMask = mask | (1 << next);

                            dp[nextMask][next][used] = Math.min(
                                dp[nextMask][next][used],
                                cur + cost[last][next]
                            );

                            if (used == 0 && reachedA) {
                                dp[nextMask][next][1] = Math.min(
                                    dp[nextMask][next][1],
                                    cur + cost[last][next] / 2
                                );
                            }
                        }
                    }
                }
            }

            int full = masks - 1;

            out.append(Math.min(
                dp[full][m - 1][0],
                dp[full][m - 1][1]
            )).append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int ch;
            do {
                ch = read();
            } while (ch <= ' ');

            long sign = 1;
            if (ch == '-') {
                sign = -1;
                ch = read();
            }

            long value = 0;
            while (ch > ' ') {
                value = value * 10 + (ch - '0');
                ch = read();
            }

            return value * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}