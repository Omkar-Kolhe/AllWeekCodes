import java.io.*;
import java.util.*;

public class chat2 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        long D = fs.nextLong();
        int K = fs.nextInt();

        long[] H = new long[N];
        for (int i = 0; i < N; i++) {
            H[i] = fs.nextLong();
        }

        ArrayList<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parent = new int[N];
        int[] depth = new int[N];
        long[] need = new long[N];
        int[] order = new int[N];

        Arrays.fill(parent, -1);

        int size = 0;
        order[size++] = 0;
        parent[0] = -2;

        for (int i = 0; i < size; i++) {
            int u = order[i];

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                order[size++] = v;
            }
        }

        need[0] = H[0];

        for (int i = 1; i < N; i++) {
            int u = order[i];

            long current = H[u] + (long) depth[u] * D;
            need[u] = Math.max(need[parent[u]], current);
        }

        Arrays.sort(need);

        System.out.println(need[K - 1]);
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
            do c = read(); while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }

            return res * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}