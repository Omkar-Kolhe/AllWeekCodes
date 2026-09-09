import java.io.*;
import java.util.*;

public class gemini1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        StringBuilder out = new StringBuilder();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                grid[i] = br.readLine().toCharArray();
            }
            
            int[][] dist = new int[n][m];
            for (int[] row : dist) Arrays.fill(row, -1);
            
            // Simulating queue for blazing fast performance
            int[] qr = new int[n * m];
            int[] qc = new int[n * m];
            int head = 0, tail = 0;
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 'B') {
                        dist[i][j] = 0;
                        qr[tail] = i;
                        qc[tail] = j;
                        tail++;
                    }
                }
            }
            
            int maxTime = 0;
            
            while (head < tail) {
                int r = qr[head];
                int c = qc[head];
                head++;
                
                if (grid[r][c] == 'S') {
                    maxTime = Math.max(maxTime, dist[r][c]);
                }
                
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        qr[tail] = nr;
                        qc[tail] = nc;
                        tail++;
                    }
                }
            }
            out.append(maxTime).append("\n");
        }
        System.out.print(out);
    }
}