import java.io.*;
import java.util.*;

public class chat1 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        final int MAXH = 100000;

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] divisors = new ArrayList[MAXH + 1];

        for (int i = 0; i <= MAXH; i++) {
            divisors[i] = new ArrayList<>();
        }

        // Precompute every possible jump length for every H value.
        for (int d = 2; d <= MAXH; d++) {
            for (int x = d; x <= MAXH; x += d) {
                divisors[x].add(d);
            }
        }

        int T = fs.nextInt();
        StringBuilder ans = new StringBuilder();

        while (T-- > 0) {
            int N = fs.nextInt();

            int[] H = new int[N];
            for (int i = 0; i < N; i++) {
                H[i] = fs.nextInt();
            }

            boolean[] dp = new boolean[N];
            dp[0] = true;

            for (int j = 1; j < N; j++) {
                for (int d : divisors[H[j]]) {
                    if (d > j) break;

                    if (dp[j - d]) {
                        dp[j] = true;
                        break;
                    }
                }
            }

            ans.append(dp[N - 1] ? "YES\n" : "NO\n");
        }

        System.out.print(ans);
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

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int num = 0;
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
            return num;
        }
    }
}