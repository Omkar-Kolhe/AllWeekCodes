import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            long[] e = new long[n];

            for (int i = 0; i < n; i++) e[i] = fs.nextLong();

            int s = fs.nextInt();
            int d = fs.nextInt();

            if (s == d) {
                out.append(0).append('\n');
                continue;
            }

            long prev2 = 0;
            long prev1 = e[s + 1];

            for (int i = s + 2; i <= d; i++) {
                long cur = e[i] + Math.min(prev1, prev2);
                prev2 = prev1;
                prev1 = cur;
            }

            out.append(prev1).append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr, len;

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
            int c;
            do c = read(); while (c <= ' ');
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long x = 0;
            while (c > ' ') {
                x = x * 10 + c - '0';
                c = read();
            }
            return x * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
