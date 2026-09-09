import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    private static final int INF = 1_000_000_000;

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private int readByte() {
            return hasNextByte() ? buffer[ptr++] : -1;
        }

        public int nextInt() {
            int b = readByte();
            while (b <= ' ' && b != -1) {
                b = readByte();
            }
            if (b == -1) return -1;
            int val = 0;
            while (b > ' ') {
                val = val * 10 + (b - '0');
                b = readByte();
            }
            return val;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int t = scanner.nextInt();
        if (t == -1) return;

        while (t-- > 0) {
            int m = scanner.nextInt();
            int a = scanner.nextInt();

            int aeolus = a - 1;
            int ithaca = m - 1;

            int[][] costMatrix = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    costMatrix[i][j] = scanner.nextInt();
                }
            }

            int stateSpace = 1 << (m - 1);
            int[][][] memo = new int[stateSpace][m][2];
            for (int i = 0; i < stateSpace; i++) {
                for (int j = 0; j < m; j++) {
                    Arrays.fill(memo[i][j], INF);
                }
            }

            memo[1][0][0] = 0;
            int completedIntermediateMask = stateSpace - 1;
            int minCost = INF;

            for (int mask = 1; mask < stateSpace; mask += 2) {
                boolean aeolusVisited = (mask & (1 << aeolus)) != 0;

                for (int u = 0; u < m - 1; u++) {
                    for (int bag = 0; bag < 2; bag++) {
                        int stateCost = memo[mask][u][bag];
                        if (stateCost >= INF) continue;

                        if (mask == completedIntermediateMask) {
                            int regularFinish = stateCost + costMatrix[u][ithaca];
                            if (regularFinish < minCost) minCost = regularFinish;

                            if (aeolusVisited && bag == 0) {
                                int discountedFinish = stateCost + costMatrix[u][ithaca] / 2;
                                if (discountedFinish < minCost) minCost = discountedFinish;
                            }
                        } else {
                            for (int v = 1; v < m - 1; v++) {
                                if ((mask & (1 << v)) == 0) {
                                    int nextMask = mask | (1 << v);

                                    int normalTransition = stateCost + costMatrix[u][v];
                                    if (normalTransition < memo[nextMask][v][bag]) {
                                        memo[nextMask][v][bag] = normalTransition;
                                    }

                                    if (aeolusVisited && bag == 0) {
                                        int discountedTransition = stateCost + costMatrix[u][v] / 2;
                                        if (discountedTransition < memo[nextMask][v][1]) {
                                            memo[nextMask][v][1] = discountedTransition;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            out.println(minCost);
        }

        out.flush();
    }
}