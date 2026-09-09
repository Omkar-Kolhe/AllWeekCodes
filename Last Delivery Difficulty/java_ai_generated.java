import java.io.*;
import java.util.*;

public class Main {

    static class Package {
        long processingTime;
        long deadline;

        Package(long processingTime, long deadline) {
            this.processingTime = processingTime;
            this.deadline = deadline;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder output = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int N = fs.nextInt();

            Package[] packages = new Package[N];

            for (int i = 0; i < N; i++) {
                long P = fs.nextLong();
                long D = fs.nextLong();

                packages[i] = new Package(P, D);
            }

            Arrays.sort(packages, Comparator.comparingLong(p -> p.deadline));

            PriorityQueue<Long> maxHeap =
                    new PriorityQueue<>(Collections.reverseOrder());

            long totalTime = 0;

            for (Package current : packages) {
                totalTime += current.processingTime;
                maxHeap.offer(current.processingTime);

                if (totalTime > current.deadline) {
                    totalTime -= maxHeap.poll();
                }
            }

            output.append(maxHeap.size()).append('\n');
        }

        System.out.print(output);
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

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ');

            long sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            long result = 0;

            while (c > ' ') {
                result = result * 10 + (c - '0');
                c = read();
            }

            return result * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
