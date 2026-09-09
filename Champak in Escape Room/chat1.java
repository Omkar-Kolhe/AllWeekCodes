import java.io.*;

public class chat1 {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int N = fs.nextInt();
            int M = fs.nextInt();
            int K = fs.nextInt();

            char[][] grid = new char[N][M];

            for (int i = 0; i < N; i++) {
                grid[i] = fs.next().toCharArray();
            }

            String S = fs.next();

            int r = 0, c = 0;

            for (int i = 0; i < K; i++) {
                char move = S.charAt(i);

                int nr = r;
                int nc = c;

                if (move == 'U') nr--;
                else if (move == 'D') nr++;
                else if (move == 'L') nc--;
                else nc++;

                if (nr >= 0 && nr < N &&
                    nc >= 0 && nc < M &&
                    grid[nr][nc] == '.') {
                    r = nr;
                    c = nc;
                }
            }

            out.append(r + 1).append(' ')
               .append(c + 1).append('\n');
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
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int ch;

            do {
                ch = read();
            } while (ch <= ' ');

            while (ch > ' ') {
                sb.append((char) ch);
                ch = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}