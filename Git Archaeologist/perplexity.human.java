import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        int t = fs.nextInt();

        while (t-- > 0) {
            int n = fs.nextInt();
            int q = fs.nextInt();

            long[] risk = new long[n];
            for (int i = 0; i < n; i++) risk[i] = fs.nextLong();

            ArrayList<Integer>[] children = new ArrayList[n];
            for (int i = 0; i < n; i++) children[i] = new ArrayList<>();

            for (int i = 0; i < n - 1; i++) {
                int p = fs.nextInt();
                int v = fs.nextInt();
                children[p].add(v);
            }

            int log = 1;
            while ((1 << log) <= n) log++;

            int[][] up = new int[log][n];
            long[][] mx = new long[log][n];
            int[] depth = new int[n];

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(0);
            mx[0][0] = risk[0];

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : children[u]) {
                    depth[v] = depth[u] + 1;
                    up[0][v] = u;
                    mx[0][v] = Math.max(risk[v], risk[u]);
                    queue.add(v);
                }
            }

            for (int k = 1; k < log; k++) {
                for (int v = 0; v < n; v++) {
                    int mid = up[k - 1][v];
                    up[k][v] = up[k - 1][mid];
                    mx[k][v] = Math.max(mx[k - 1][v], mx[k - 1][mid]);
                }
            }

            while (q-- > 0) {
                int a = fs.nextInt();
                int b = fs.nextInt();
                int x = a, y = b;
                long best = Math.max(risk[a], risk[b]);

                if (depth[x] < depth[y]) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                }

                int diff = depth[x] - depth[y];
                for (int k = 0; k < log; k++) {
                    if ((diff & (1 << k)) != 0) {
                        best = Math.max(best, mx[k][x]);
                        x = up[k][x];
                    }
                }

                if (x != y) {
                    for (int k = log - 1; k >= 0; k--) {
                        if (up[k][x] != up[k][y]) {
                            best = Math.max(best, mx[k][x]);
                            best = Math.max(best, mx[k][y]);
                            x = up[k][x];
                            y = up[k][y];
                        }
                    }

                    best = Math.max(best, risk[x]);
                    best = Math.max(best, risk[y]);
                    best = Math.max(best, risk[up[0][x]]);
                    x = up[0][x];
                }

                int distance = depth[a] + depth[b] - 2 * depth[x];
                out.append(x).append(' ').append(distance).append(' ').append(best).append('\n');
            }
        }

        System.out.print(out);
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
            long x = 0;
            while (c > 32) {
                x = x * 10 + c - '0';
                c = read();
            }
            return x;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
