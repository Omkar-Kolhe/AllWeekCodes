import java.io.*;
import java.util.*;

public class Main {
    static boolean canTransport(long[] w, int d, long capacity) {
        int days = 1;
        long current = 0;

        for (long x : w) {
            if (current + x <= capacity) {
                current += x;
            } else {
                days++;
                current = x;
            }
        }

        return days <= d;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int d = fs.nextInt();

            long[] w = new long[n];
            long left = 0;
            long right = 0;

            for (int i = 0; i < n; i++) {
                w[i] = fs.nextLong();
                left = Math.max(left, w[i]);
                right += w[i];
            }

            while (left < right) {
                long mid = left + (right - left) / 2;

                if (canTransport(w, d, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            out.append(left).append('\n');
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
                value = value * 10 + c - '0';
                c = read();
            }

            return value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}