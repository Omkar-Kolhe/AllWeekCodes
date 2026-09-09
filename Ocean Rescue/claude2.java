import java.util.*;
import java.io.*;

public class claude2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            char[][] grid = new char[N][];
            for (int i = 0; i < N; i++) grid[i] = br.readLine().toCharArray();

            int[][] dist = new int[N][M];
            for (int[] row : dist) Arrays.fill(row, -1);

            ArrayDeque<int[]> queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'B') {
                        dist[i][j] = 0;
                        queue.add(new int[]{i, j});
                    }
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'S') {
                        ans = Math.max(ans, dist[i][j]);
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}