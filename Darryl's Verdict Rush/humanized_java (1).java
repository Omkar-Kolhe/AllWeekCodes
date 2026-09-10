import java.io.*;
import java.util.*;

public class Main {
    static class Job {
        long p, d;

        Job(long p, long d) {
            this.p = p;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            Job[] jobs = new Job[n];

            for (int i = 0; i < n; i++) {
                jobs[i] = new Job(fs.nextLong(), fs.nextLong());
            }

            Arrays.sort(jobs, Comparator.comparingLong(job -> job.d));

            TreeMap<Long, Integer> loads = new TreeMap<>();
            int answer = 0;

            for (Job job : jobs) {
                long limit = job.d - job.p;
                Long best = loads.floorKey(limit);

                if (best == null) {
                    loads.merge(job.p, 1, Integer::sum);
                    answer++;
                } else {
                    removeOne(loads, best);
                    loads.merge(best + job.p, 1, Integer::sum);
                }
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }

    static void removeOne(TreeMap<Long, Integer> map, long key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
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
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            long value = 0;
            while (c > ' ') {
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
