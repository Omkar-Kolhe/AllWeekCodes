import java.io.*;
import java.util.*;

class Main {

    static class Result {
        int length;
        int start;
        int end;

        Result(int length, int start, int end) {
            this.length = length;
            this.start = start;
            this.end = end;
        }
    }

    static Result solve(int n, FastScanner fs) throws Exception {
        Map<Long, Integer> earliest = new HashMap<>();
        earliest.put(0L, 0);

        long balance = 0;

        int longest = 0;
        int left = 0;
        int right = 0;

        for (int position = 1; position <= n; position++) {
            char type = fs.next().charAt(0);
            long angle = fs.nextLong();

            balance += type == 'L' ? angle : -angle;

            if (!earliest.containsKey(balance)) {
                earliest.put(balance, position);
                continue;
            }

            int oldPosition = earliest.get(balance);
            int currentLength = position - oldPosition;
            int currentStart = oldPosition + 1;

            boolean better = currentLength > longest;

            if (!better && currentLength == longest) {
                better = left == 0 || currentStart < left;
            }

            if (better) {
                longest = currentLength;
                left = currentStart;
                right = position;
            }
        }

        return new Result(longest, left, right);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder answer = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();

            Result result = solve(n, fs);

            answer.append(result.length)
                    .append(' ')
                    .append(result.start)
                    .append(' ')
                    .append(result.end)
                    .append('\n');
        }

        System.out.print(answer);
    }

    static class FastScanner {
        private final BufferedInputStream input;
        private final byte[] buffer = new byte[1 << 16];
        private int index = 0;
        private int size = 0;

        FastScanner(InputStream stream) {
            input = new BufferedInputStream(stream);
        }

        private int read() throws IOException {
            if (index >= size) {
                size = input.read(buffer);
                index = 0;

                if (size == -1) {
                    return -1;
                }
            }

            return buffer[index++];
        }

        String next() throws IOException {
            StringBuilder word = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= 32);

            while (c > 32) {
                word.append((char) c);
                c = read();
            }

            return word.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}