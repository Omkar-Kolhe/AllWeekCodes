import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int n = fs.nextInt();
            long d = fs.nextLong();
            long fuel = fs.nextLong();

            long[][] depots = new long[n][2];

            for (int i = 0; i < n; i++) {
                depots[i][0] = fs.nextLong();
                depots[i][1] = fs.nextLong();
            }

            Arrays.sort(depots, Comparator.comparingLong(a -> a[0]));

            PriorityQueue<Long> heap = new PriorityQueue<>(Collections.reverseOrder());

            int i = 0;
            int stops = 0;

            while (i < n && depots[i][0] <= fuel) {
                heap.add(depots[i][1]);
                i++;
            }

            while (fuel < d && !heap.isEmpty()) {
                fuel += heap.poll();
                stops++;

                while (i < n && depots[i][0] <= fuel) {
                    heap.add(depots[i][1]);
                    i++;
                }
            }

            if (fuel >= d)
                out.append(stops).append('\n');
            else
                out.append(-1).append('\n');
        }

        System.out.print(out);
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
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            long value = 0;
            boolean negative = false;

            if (c == '-') {
                negative = true;
                c = read();
            }

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