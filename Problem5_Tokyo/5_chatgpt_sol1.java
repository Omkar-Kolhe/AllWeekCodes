import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            HashMap<Long, Integer> first = new HashMap<>();
            first.put(0L, 0);

            long prefix = 0;

            int bestLength = 0;
            int bestStart = 0;
            int bestEnd = 0;

            for (int i = 1; i <= n; i++) {
                char direction = fs.next().charAt(0);
                long angle = fs.nextLong();

                if (direction == 'L') {
                    prefix += angle;
                } else {
                    prefix -= angle;
                }

                Integer previous = first.get(prefix);

                if (previous == null) {
                    first.put(prefix, i);
                } else {
                    int length = i - previous;
                    int start = previous + 1;

                    if (length > bestLength ||
                            (length == bestLength &&
                                    (bestStart == 0 || start < bestStart))) {
                        bestLength = length;
                        bestStart = start;
                        bestEnd = i;
                    }
                }
            }

            out.append(bestLength)
                    .append(' ')
                    .append(bestStart)
                    .append(' ')
                    .append(bestEnd)
                    .append('\n');
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

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}