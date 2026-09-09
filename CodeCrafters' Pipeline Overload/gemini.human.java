import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            long x = fs.nextLong();
            int[] a = new int[n];
            long sum = 0;

            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
                sum += a[i];
            }

            if (sum == x) {
                out.append("NO\n");
                continue;
            }

            Arrays.sort(a);
            long cur = 0;

            for (int i = 0; i < n - 1; i++) {
                if (cur + a[i] == x) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
                cur += a[i];
            }

            out.append("YES\n");
            for (int v : a) out.append(v).append(' ');
            out.append('\n');
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
            do c = read(); while (c <= 32);
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
}
