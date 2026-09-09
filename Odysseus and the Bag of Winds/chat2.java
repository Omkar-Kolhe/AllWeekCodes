import java.io.*;
import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(System.in);
        StringBuilder result = new StringBuilder();

        int testCases = input.nextInt();

        while (testCases-- > 0) {
            int n = input.nextInt();
            int aeolus = input.nextInt() - 1;

            long[][] travelTime = new long[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    travelTime[i][j] = input.nextLong();
                }
            }

            int numberOfMasks = 1 << n;

            long[][][] dp = new long[numberOfMasks][n][2];

            for (int mask = 0; mask < numberOfMasks; mask++) {
                for (int city = 0; city < n; city++) {
                    dp[mask][city][0] = INF;
                    dp[mask][city][1] = INF;
                }
            }

            dp[1][0][0] = 0;

            for (int mask = 1; mask < numberOfMasks; mask++) {
                if ((mask & 1) == 0) {
                    continue;
                }

                boolean aeolusVisited = (mask & (1 << aeolus)) != 0;

                for (int current = 0; current < n; current++) {
                    if ((mask & (1 << current)) == 0) {
                        continue;
                    }

                    for (int bagUsed = 0; bagUsed <= 1; bagUsed++) {
                        long currentCost = dp[mask][current][bagUsed];

                        if (currentCost == INF) {
                            continue;
                        }

                        for (int next = 0; next < n; next++) {
                            if ((mask & (1 << next)) != 0) {
                                continue;
                            }

                            int newMask = mask | (1 << next);

                            long normalCost =
                                currentCost + travelTime[current][next];

                            if (normalCost < dp[newMask][next][bagUsed]) {
                                dp[newMask][next][bagUsed] = normalCost;
                            }

                            if (!aeolusVisited && bagUsed == 0) {
                                continue;
                            }

                            if (bagUsed == 0) {
                                long reducedCost =
                                    currentCost + travelTime[current][next] / 2;

                                if (reducedCost < dp[newMask][next][1]) {
                                    dp[newMask][next][1] = reducedCost;
                                }
                            }
                        }
                    }
                }
            }

            int allVisited = numberOfMasks - 1;

            long answer = Math.min(
                dp[allVisited][n - 1][0],
                dp[allVisited][n - 1][1]
            );

            result.append(answer).append('\n');
        }

        System.out.print(result);
    }

    private static class FastInput {
        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];
        private int position = 0;
        private int length = 0;

        FastInput(InputStream input) {
            this.input = input;
        }

        private int read() throws IOException {
            if (position >= length) {
                length = input.read(buffer);
                position = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[position++];
        }

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ');

            long sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;

            while (c > ' ') {
                value = value * 10 + c - '0';
                c = read();
            }

            return value * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}