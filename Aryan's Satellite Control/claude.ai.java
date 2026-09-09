import java.io.*;
import java.util.*;

public class Main {
    static class FenwickTree {
        private final int[] tree;

        FenwickTree(int n) {
            tree = new int[n + 1];
        }

        void update(int index, int delta) {
            for (index++; index < tree.length; index += index & -index)
                tree[index] += delta;
        }

        int query(int index) {
            int result = 0;
            for (index++; index > 0; index -= index & -index)
                result += tree[index];
            return result;
        }

        int findKth(int k) {
            int index = 0;
            int step = Integer.highestOneBit(tree.length - 1);

            while (step != 0) {
                int next = index + step;
                if (next < tree.length && tree[next] < k) {
                    index = next;
                    k -= tree[next];
                }
                step >>= 1;
            }

            return index;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder output = new StringBuilder();

        int testCases = fs.nextInt();

        while (testCases-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            long[][] intervals = new long[n][2];
            long[] coordinates = new long[n + 1];
            coordinates[0] = 0;

            for (int i = 0; i < n; i++) {
                intervals[i][0] = fs.nextLong();
                intervals[i][1] = fs.nextLong();
                coordinates[i + 1] = intervals[i][1];
            }

            Arrays.sort(intervals, Comparator.comparingLong(a -> a[1]));
            Arrays.sort(coordinates);

            long[] values = new long[n + 1];
            int count = 0;

            for (long value : coordinates) {
                if (count == 0 || values[count - 1] != value)
                    values[count++] = value;
            }

            FenwickTree tree = new FenwickTree(count);
            tree.update(lowerBound(values, count, 0), k);

            int answer = 0;

            for (long[] interval : intervals) {
                int last = upperBound(values, count, interval[0]) - 1;

                if (last < 0)
                    continue;

                int available = tree.query(last);
                if (available == 0)
                    continue;

                int selected = tree.findKth(available);
                tree.update(selected, -1);
                tree.update(lowerBound(values, count, interval[1]), 1);
                answer++;
            }

            output.append(answer).append('\n');
        }

        System.out.print(output);
    }

    static int lowerBound(long[] array, int size, long value) {
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] < value) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    static int upperBound(long[] array, int size, long value) {
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] <= value) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    static class FastScanner {
        private final InputStream input = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        private int read() throws IOException {
            if (pointer >= length) {
                length = input.read(buffer);
                pointer = 0;
                if (length == -1) return -1;
            }
            return buffer[pointer++];
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
