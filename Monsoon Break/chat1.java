import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            long[] a = new long[n];
            long min = Long.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                a[i] = fs.nextLong();
                min = Math.min(min, a[i]);
            }

            int minCount = 0;
            boolean hasNonMultiple = false;

            for (long x : a) {
                if (x == min) {
                    minCount++;
                }

                if (x % min != 0) {
                    hasNonMultiple = true;
                }
            }

            if (minCount == 1 || hasNonMultiple) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len == -1) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ');

            long value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}