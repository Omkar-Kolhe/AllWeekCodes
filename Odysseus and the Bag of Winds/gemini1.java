import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = br.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            int m = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            int aeolus = a - 1;
            int ithaca = m - 1;

            int[][] cost = new int[m][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    while (st == null || !st.hasMoreTokens()) {
                        st = new StringTokenizer(br.readLine());
                    }
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int numMasks = 1 << (m - 1);
            int[][][] dp = new int[numMasks][m][2];
            for (int i = 0; i < numMasks; i++) {
                for (int j = 0; j < m; j++) {
                    Arrays.fill(dp[i][j], INF);
                }
            }

            dp[1][0][0] = 0;
            int finalMask = numMasks - 1;
            int answer = INF;

            for (int mask = 1; mask < numMasks; mask += 2) {
                boolean hasBag = (mask & (1 << aeolus)) != 0;

                for (int u = 0; u < m - 1; u++) {
                    for (int bag = 0; bag < 2; bag++) {
                        int cur = dp[mask][u][bag];
                        if (cur >= INF) continue;

                        if (mask == finalMask) {
                            answer = Math.min(answer, cur + cost[u][ithaca]);
                            if (hasBag && bag == 0) {
                                answer = Math.min(answer, cur + cost[u][ithaca] / 2);
                            }
                        } else {
                            for (int v = 1; v < m - 1; v++) {
                                if ((mask & (1 << v)) == 0) {
                                    int nextMask = mask | (1 << v);

                                    if (cur + cost[u][v] < dp[nextMask][v][bag]) {
                                        dp[nextMask][v][bag] = cur + cost[u][v];
                                    }

                                    if (hasBag && bag == 0) {
                                        int discounted = cur + cost[u][v] / 2;
                                        if (discounted < dp[nextMask][v][1]) {
                                            dp[nextMask][v][1] = discounted;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            out.println(answer);
        }

        out.flush();
    }
}