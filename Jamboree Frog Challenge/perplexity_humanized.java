import java.io.*;
import java.util.*;

class Main {
    static class Line {
        long m, b;

        Line(long m, long b) {
            this.m = m;
            this.b = b;
        }

        long value(long x) {
            return m * x + b;
        }
    }

    // Java has no built-in __int128, so the exact comparison is implemented
    // with BigInteger for the rare hull-removal check.
    static boolean removeMiddle(Line a, Line b, Line c) {
        java.math.BigInteger left = java.math.BigInteger.valueOf(b.b - a.b)
                .multiply(java.math.BigInteger.valueOf(b.m - c.m));
        java.math.BigInteger right = java.math.BigInteger.valueOf(c.b - b.b)
                .multiply(java.math.BigInteger.valueOf(a.m - b.m));
        return left.compareTo(right) >= 0;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long C = fs.nextLong();

        long[] h = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = fs.nextLong();
        }

        long[] dp = new long[n];
        ArrayDeque<Line> hull = new ArrayDeque<>();

        hull.addLast(new Line(-2 * h[0], h[0] * h[0]));

        for (int j = 1; j < n; j++) {
            long x = h[j];

            while (hull.size() >= 2) {
                Line first = hull.peekFirst();
                hull.removeFirst();
                Line second = hull.peekFirst();

                if (first.value(x) >= second.value(x)) {
                    continue;
                }

                hull.addFirst(first);
                break;
            }

            dp[j] = x * x + C + hull.peekFirst().value(x);

            Line next = new Line(-2 * x, dp[j] + x * x);

            while (hull.size() >= 2) {
                Line last = hull.removeLast();
                Line before = hull.peekLast();

                if (removeMiddle(before, last, next)) {
                    continue;
                }

                hull.addLast(last);
                break;
            }

            hull.addLast(next);
        }

        System.out.println(dp[n - 1]);
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
            int c;
            do c = read(); while (c <= ' ');

            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }

            long value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return negative ? -value : value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
