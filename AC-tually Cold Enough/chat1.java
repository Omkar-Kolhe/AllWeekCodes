import java.io.*;
import java.util.*;

public class chat1 {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        long D = fs.nextLong();
        int K = fs.nextInt();

        long[] H = new long[N];
        for (int i = 0; i < N; i++) {
            H[i] = fs.nextLong();
        }

        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        if (K > N) {
            System.out.println(-1);
            return;
        }

        long[] need = new long[N];
        int[] parent = new int[N];
        int[] depth = new int[N];

        Arrays.fill(parent, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        parent[0] = 0;

        need[0] = H[0];

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;

                long currentNeed = H[v] + (long) depth[v] * D;

                need[v] = Math.max(need[u], currentNeed);

                queue.add(v);
            }
        }

        Arrays.sort(need);

        System.out.println(need[K - 1]);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len == -1) {
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

            long num = 0;

            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }

            return num;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}