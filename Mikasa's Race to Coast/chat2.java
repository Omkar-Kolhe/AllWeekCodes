import java.io.*;
import java.util.*;

public class Main {
    static class Depot {
        long position;
        long fuel;

        Depot(long position, long fuel) {
            this.position = position;
            this.fuel = fuel;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder answer = new StringBuilder();

        int testCases = fs.nextInt();

        while (testCases-- > 0) {
            int n = fs.nextInt();
            long destination = fs.nextLong();
            long fuel = fs.nextLong();

            Depot[] depots = new Depot[n];

            for (int i = 0; i < n; i++) {
                long position = fs.nextLong();
                long availableFuel = fs.nextLong();
                depots[i] = new Depot(position, availableFuel);
            }

            Arrays.sort(depots, Comparator.comparingLong(d -> d.position));

            PriorityQueue<Long> available = new PriorityQueue<>(
                Collections.reverseOrder()
            );

            int index = 0;
            int stops = 0;

            while (index < n && depots[index].position <= fuel) {
                available.offer(depots[index].fuel);
                index++;
            }

            while (fuel < destination) {
                if (available.isEmpty()) {
                    break;
                }

                fuel += available.poll();
                stops++;

                while (index < n && depots[index].position <= fuel) {
                    available.offer(depots[index].fuel);
                    index++;
                }
            }

            if (fuel >= destination) {
                answer.append(stops);
            } else {
                answer.append(-1);
            }

            answer.append('\n');
        }

        System.out.print(answer);
    }

    static class FastScanner {
        private final InputStream input;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        FastScanner(InputStream input) {
            this.input = input;
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = input.read(buffer);
                pointer = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ');

            boolean negative = false;

            if (c == '-') {
                negative = true;
                c = read();
            }

            long value = 0;

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