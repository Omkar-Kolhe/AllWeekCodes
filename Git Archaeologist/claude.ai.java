import java.io.*;
import java.util.*;

public class Main {
    static int[][] ancestor;
    static long[][] maximum;
    static int[] depth;
    static long[] risk;
    static int levels;

    static int[] query(int a, int b) {
        long maxValue = Math.max(risk[a], risk[b]);
        int u = a;
        int v = b;

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int delta = depth[u] - depth[v];

        for (int k = levels - 1; k >= 0; k--) {
            if ((delta & (1 << k)) != 0) {
                maxValue = Math.max(maxValue, maximum[k][u]);
                u = ancestor[k][u];
            }
        }

        if (u != v) {
            for (int k = levels - 1; k >= 0; k--) {
                if (ancestor[k][u] != ancestor[k][v]) {
                    maxValue = Math.max(maxValue, maximum[k][u]);
                    maxValue = Math.max(maxValue, maximum[k][v]);
                    u = ancestor[k][u];
                    v = ancestor[k][v];
                }
            }

            maxValue = Math.max(maxValue, risk[u]);
            maxValue = Math.max(maxValue, risk[v]);
            maxValue = Math.max(maxValue, risk[ancestor[0][u]]);
            u = ancestor[0][u];
        }

        int distance = depth[a] + depth[b] - 2 * depth[u];
        return new int[]{u, distance, (int) maxValue};
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder answer = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int n = fs.nextInt();
            int q = fs.nextInt();

            risk = new long[n];
            for (int i = 0; i < n; i++) risk[i] = fs.nextLong();

            ArrayList<Integer>[] tree = new ArrayList[n];
            for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

            for (int i = 0; i < n - 1; i++) {
                int parent = fs.nextInt();
                int child = fs.nextInt();
                tree[parent].add(child);
            }

            levels = 1;
            while ((1 << levels) <= n) levels++;

            ancestor = new int[levels][n];
            maximum = new long[levels][n];
            depth = new int[n];

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(0);
            maximum[0][0] = risk[0];

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int child : tree[node]) {
                    depth[child] = depth[node] + 1;
                    ancestor[0][child] = node;
                    maximum[0][child] = Math.max(risk[child], risk[node]);
                    queue.add(child);
                }
            }

            for (int k = 1; k < levels; k++) {
                for (int node = 0; node < n; node++) {
                    int middle = ancestor[k - 1][node];
                    ancestor[k][node] = ancestor[k - 1][middle];
                    maximum[k][node] = Math.max(maximum[k - 1][node], maximum[k - 1][middle]);
                }
            }

            while (q-- > 0) {
                int a = fs.nextInt();
                int b = fs.nextInt();
                int[] result = query(a, b);
                answer.append(result[0]).append(' ')
                      .append(result[1]).append(' ')
                      .append(result[2]).append('\n');
            }
        }

        System.out.print(answer);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        private int read() throws IOException {
            if (pointer >= length) {
                length = in.read(buffer);
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
