import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private int readByte() {
            return hasNextByte() ? buffer[ptr++] : -1;
        }

        public long nextLong() {
            int b = readByte();
            while (b <= ' ' && b != -1) {
                b = readByte();
            }
            if (b == -1) return -1;
            long val = 0;
            while (b > ' ') {
                val = val * 10 + (b - '0');
                b = readByte();
            }
            return val;
        }

        public int nextInt() {
            return (int) nextLong();
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int t = scanner.nextInt();
        if (t != -1) {
            for (int i = 0; i < t; i++) {
                long n = scanner.nextLong();
                long m = scanner.nextLong();

                long maxElements = (m + 1) / 2;
                if (n > maxElements) {
                    out.println(-1);
                } else {
                    long start = m - n + 1;
                    for (int j = 0; j < n; j++) {
                        out.print(start + j);
                        if (j + 1 < n) {
                            out.print(" ");
                        }
                    }
                    out.println();
                }
            }
        }
        out.flush();
    }
}