import java.io.*;
import java.util.*;

public class humanized_perplexity_java {

    // Simple fast scanner for competitive programming style I/O
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
            while ((c = read()) <= ' ') {
                if (c == -1) return Long.MIN_VALUE;
            }
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    /**
     * For one test case, compute the maximum number of packages
     * that can be delivered without missing their deadlines.
     */
    static int solveOneCase(int n, long[][] packages, FastScanner unused) {
        // packages[i] = {processingTime, deadline}
        // Sort by deadline ascending
        Arrays.sort(packages, Comparator.comparingLong(a -> a[1]));

        // Max-heap of processing times of currently selected packages
        PriorityQueue<Long> selected = new PriorityQueue<>(Collections.reverseOrder());
        long totalTime = 0;

        for (long[] pkg : packages) {
            long p = pkg[0];
            long d = pkg[1];

            totalTime += p;
            selected.add(p);

            // If we exceed the deadline, remove the heaviest package
            if (totalTime > d) {
                long heaviest = selected.poll();
                totalTime -= heaviest;
            }
        }

        return selected.size();
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int t = sc.nextInt();
        StringBuilder out = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            int n = sc.nextInt();
            long[][] packages = new long[n][2];
            for (int i = 0; i < n; i++) {
                packages[i][0] = sc.nextLong(); // P
                packages[i][1] = sc.nextLong(); // D
            }

            int ans = solveOneCase(n, packages, sc);
            out.append(ans).append('\n');
        }

        System.out.print(out);
    }
}
