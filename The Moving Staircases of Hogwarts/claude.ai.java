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
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= 32);

            long value = 0;
            while (c > 32) {
                value = value * 10 + c - '0';
                c = read();
            }
            return value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder ans = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            long[] energy = new long[n];

            for (int i = 0; i < n; i++) {
                energy[i] = fs.nextLong();
            }

            int s = fs.nextInt();
            int d = fs.nextInt();

            long result;

            if (s == d) {
                result = 0;
            } else {
                long twoBack = 0;
                long oneBack = energy[s + 1];

                for (int floor = s + 2; floor <= d; floor++) {
                    long best = Math.min(twoBack, oneBack);
                    long current = best + energy[floor];
                    twoBack = oneBack;
                    oneBack = current;
                }

                result = oneBack;
            }

            ans.append(result).append('\n');
        }

        System.out.print(ans);
    }
}
