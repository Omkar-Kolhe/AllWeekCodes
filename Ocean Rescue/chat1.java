import java.io.*;
import java.util.*;

public class chat1 {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        StringBuilder out = new StringBuilder();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (T-- > 0) {
            int N = fs.nextInt();
            int M = fs.nextInt();

            char[][] grid = new char[N][M];
            int[] dist = new int[N * M];

            Arrays.fill(dist, -1);

            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int r = 0; r < N; r++) {
                grid[r] = fs.next().toCharArray();

                for (int c = 0; c < M; c++) {
                    if (grid[r][c] == 'B') {
                        int id = r * M + c;
                        dist[id] = 0;
                        q.add(id);
                    }
                }
            }

            int answer = 0;

            // All bases are inserted initially.
            // Therefore this is a multi-source BFS.
            while (!q.isEmpty()) {
                int id = q.poll();

                int r = id / M;
                int c = id % M;

                if (grid[r][c] == 'S') {
                    answer = Math.max(answer, dist[id]);
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;

                    if (grid[nr][nc] == '#')
                        continue;

                    int nid = nr * M + nc;

                    if (dist[nid] != -1)
                        continue;

                    dist[nid] = dist[id] + 1;
                    q.add(nid);
                }
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len == -1)
                    return -1;
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}