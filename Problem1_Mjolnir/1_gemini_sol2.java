
// 1_gemini_sol2.java
import java.io.*;
import java.util.*;

class Main {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class Avenger {
        long impact;
        long cost;

        Avenger(long s, long w, long c) {
            this.impact = (s > w) ? (s - w) : 0;
            this.cost = c;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        String tStr = scanner.next();
        if (tStr == null)
            return;
        int testCases = Integer.parseInt(tStr);

        PrintWriter out = new PrintWriter(System.out);

        while (testCases-- > 0) {
            int n = scanner.nextInt();
            long r = scanner.nextLong();

            long sCap = scanner.nextLong();
            long wCap = scanner.nextLong();

            long allowedImpact = sCap + wCap - r;

            Avenger[] arr = new Avenger[n];
            long totalTime = 0;

            for (int i = 0; i < n; i++) {
                long s = scanner.nextLong();
                long w = scanner.nextLong();
                long c = scanner.nextLong();
                arr[i] = new Avenger(s, w, c);
                totalTime += c;
            }

            if (allowedImpact < 0) {
                out.println("-1");
                continue;
            }

            int limit = (int) allowedImpact;
            long[][] dp = new long[2][limit + 1];

            for (int i = 1; i <= n; i++) {
                int current = i % 2;
                int previous = (i - 1) % 2;
                long itemWeight = arr[i - 1].impact;
                long itemValue = arr[i - 1].cost;

                for (int w = 0; w <= limit; w++) {
                    dp[current][w] = dp[previous][w];
                    if (w >= itemWeight) {
                        dp[current][w] = Math.max(dp[current][w], dp[previous][w - (int) itemWeight] + itemValue);
                    }
                }
            }

            out.println(totalTime - dp[n % 2][limit]);
        }
        out.flush();
    }
}