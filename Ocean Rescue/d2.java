import java.util.*;

public class d2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                grid[i] = sc.next().toCharArray();
            }
            
            int[][] dist = new int[N][M];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], -1);
            }
            
            Queue<int[]> q = new LinkedList<>();
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'B') {
                        dist[i][j] = 0;
                        q.offer(new int[]{i, j});
                    }
                }
            }
            
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        q.offer(new int[]{nr, nc});
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
            
            System.out.println(ans);
        }
        
        sc.close();
    }
}