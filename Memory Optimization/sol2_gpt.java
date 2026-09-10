import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) {
                    return -1;
                }
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        StringBuilder out = new StringBuilder();

        while (T-- > 0) {
            int N = fs.nextInt();

            HashMap<Integer, Integer> maxExponent = new HashMap<>();

            for (int i = 0; i < N; i++) {
                int x = fs.nextInt();

                int exponent = 0;

                while ((x & 1) == 0) {
                    x >>= 1;
                    exponent++;
                }

                int previous = maxExponent.getOrDefault(x, 0);
                maxExponent.put(x, Math.max(previous, exponent));
            }

            long answer = 0;

            for (int exponent : maxExponent.values()) {
                answer += exponent;
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }
}