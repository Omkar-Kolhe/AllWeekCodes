import java.io.*;
import java.util.*;

public class ai_perplexity_java {
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

    static void solve() throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();
            long[][] jobs = new long[N][2];
            for (int i = 0; i < N; i++) {
                jobs[i][0] = sc.nextLong(); // P
                jobs[i][1] = sc.nextLong(); // D
            }

            Arrays.sort(jobs, Comparator.comparingLong(a -> a[1])); // by deadline

            // Max-heap for processing times
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            long currentTime = 0;

            for (long[] job : jobs) {
                long p = job[0];
                long d = job[1];

                currentTime += p;
                maxHeap.add(p);

                if (currentTime > d) {
                    long largest = maxHeap.poll();
                    currentTime -= largest;
                }
            }

            sb.append(maxHeap.size()).append('\n');
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solve();
    }
}
