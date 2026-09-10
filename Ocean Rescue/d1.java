import java.io.*;
import java.util.*;

public class d1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (T-- > 0) {
            String[] first = br.readLine().split(" ");
            int N = Integer.parseInt(first[0]);
            int M = Integer.parseInt(first[1]);
            
            char[][] grid = new char[N][M];
            Queue<int[]> q = new LinkedList<>();
            int[][] dist = new int[N][M];
            
            for (int i = 0; i < N; i++) {
                String row = br.readLine();
                Arrays.fill(dist[i], -1);
                for (int j = 0; j < M; j++) {
                    grid[i][j] = row.charAt(j);
                    if (grid[i][j] == 'B') {
                        q.add(new int[]{i, j});
                        dist[i][j] = 0;
                    }
                }
            }
            
            int ans = 0;
            
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                
                if (grid[r][c] == 'S') {
                    ans = Math.max(ans, dist[r][c]);
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M &&
                        grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            
            System.out.println(ans);
        }
    }
}