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
            do {
                c = read();
            } while (c <= ' ');

            long value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
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
        StringBuilder result = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int n = fs.nextInt();
            long[][] jobs = new long[n][2];

            for (int i = 0; i < n; i++) {
                jobs[i][0] = fs.nextLong();
                jobs[i][1] = fs.nextLong();
            }

            Arrays.sort(jobs, Comparator.comparingLong(a -> a[1]));

            TreeMap<Long, Integer> loads = new TreeMap<>();
            int containers = 0;

            for (long[] job : jobs) {
                long p = job[0];
                long d = job[1];

                Long load = loads.floorKey(d - p);

                if (load == null) {
                    loads.merge(p, 1, Integer::sum);
                    containers++;
                } else {
                    int count = loads.get(load);
                    if (count == 1) {
                        loads.remove(load);
                    } else {
                        loads.put(load, count - 1);
                    }

                    loads.merge(load + p, 1, Integer::sum);
                }
            }

            result.append(containers).append('\n');
        }

        System.out.print(result);
    }
}
