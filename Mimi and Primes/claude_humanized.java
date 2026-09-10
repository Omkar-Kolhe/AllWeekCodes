import java.io.*;
import java.util.*;

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static long largestPrimeFactor(long value) {
        long answer = -1;

        if (value % 2 == 0) {
            answer = 2;
            while (value % 2 == 0) {
                value /= 2;
            }
        }

        for (long factor = 3; factor * factor <= value; factor += 2) {
            if (value % factor == 0) {
                answer = factor;
                while (value % factor == 0) {
                    value /= factor;
                }
            }
        }

        if (value > 1) {
            answer = value;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            long common = 0;

            for (int i = 0; i < n; i++) {
                common = gcd(common, in.nextLong());
            }

            out.append(largestPrimeFactor(common)).append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr;
        private int len;

        FastScanner(InputStream input) {
            this.input = input;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = input.read(buffer);
                ptr = 0;
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            long number = 0;
            while (c > ' ') {
                number = number * 10 + c - '0';
                c = read();
            }
            return number;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
