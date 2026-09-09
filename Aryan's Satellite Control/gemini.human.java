import java.io.*;
import java.util.*;

public class Main {
    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void add(int i, int value) {
            for (i++; i <= n; i += i & -i) bit[i] += value;
        }

        int sum(int i) {
            int res = 0;
            for (i++; i > 0; i -= i & -i) res += bit[i];
            return res;
        }

        int kth(int k) {
            int idx = 0;
            int step = Integer.highestOneBit(n);
            for (; step > 0; step >>= 1) {
                int next = idx + step;
                if (next <= n && bit[next] < k) {
                    idx = next;
                    k -= bit[next];
                }
            }
            return idx;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            long[][] jobs = new long[n][2];
            long[] ends = new long[n + 1];
            ends[0] = 0;

            for (int i = 0; i < n; i++) {
                jobs[i][0] = fs.nextLong();
                jobs[i][1] = fs.nextLong();
                ends[i + 1] = jobs[i][1];
            }

            Arrays.sort(jobs, Comparator.comparingLong(a -> a[1]));
            Arrays.sort(ends);

            long[] unique = new long[ends.length];
            int m = 0;
            for (long value : ends) {
                if (m == 0 || unique[m - 1] != value)
                    unique[m++] = value;
            }

            Fenwick fw = new Fenwick(m);
            fw.add(lowerBound(unique, m, 0), k);

            int answer = 0;

            for (long[] job : jobs) {
                int idx = upperBound(unique, m, job[0]) - 1;
                if (idx < 0) continue;

                int count = fw.sum(idx);
                if (count == 0) continue;

                int chosen = fw.kth(count);
                fw.add(chosen, -1);
                fw.add(lowerBound(unique, m, job[1]), 1);
                answer++;
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }

    static int lowerBound(long[] a, int n, long x) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] < x) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    static int upperBound(long[] a, int n, long x) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] <= x) l = mid + 1;
            else r = mid;
        }
        return l;
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
