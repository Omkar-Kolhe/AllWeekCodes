import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

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
            do c = read(); while (c <= 32);
            long result = 0;
            while (c > 32) {
                result = result * 10 + c - '0';
                c = read();
            }
            return result;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int n = fs.nextInt();
            long x = fs.nextLong();
            int[] w = new int[n];
            long total = 0;

            for (int i = 0; i < n; i++) {
                w[i] = fs.nextInt();
                total += w[i];
            }

            if (total == x) {
                sb.append("NO\n");
                continue;
            }

            Arrays.sort(w);
            long prefix = 0;

            for (int i = 0; i < n; i++) {
                if (prefix + w[i] == x && i + 1 < n) {
                    int temp = w[i];
                    w[i] = w[i + 1];
                    w[i + 1] = temp;
                }
                prefix += w[i];
            }

            sb.append("YES\n");
            for (int value : w) {
                sb.append(value).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
