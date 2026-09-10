import java.io.*;
import java.util.*;

public class chat2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            char[][] grid = new char[N][M];
            int[][] dist = new int[N][M];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], -1);
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                grid[i] = br.readLine().toCharArray();

                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'B') {
                        dist[i][j] = 0;
                        q.add(i * M + j);
                    }
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                int cur = q.poll();
                int r = cur / M;
                int c = cur % M;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;

                    if (grid[nr][nc] == '#')
                        continue;

                    if (dist[nr][nc] != -1)
                        continue;

                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(nr * M + nc);
                }
            }

            int answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'S') {
                        answer = Math.max(answer, dist[i][j]);
                    }
                }
            }

            out.append(answer).append('\n');
        }

        System.out.print(out);
    }
}