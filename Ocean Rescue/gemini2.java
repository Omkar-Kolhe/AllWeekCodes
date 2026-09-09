import java.io.*;
import java.util.*;

public class gemini2 {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        String tStr = fs.next();
        if (tStr == null) return;
        int t = Integer.parseInt(tStr);
        StringBuilder sb = new StringBuilder();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            
            String[] grid = new String[n];
            int[][] dist = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], -1);
            }
            
            int[] qR = new int[n * m];
            int[] qC = new int[n * m];
            int head = 0, tail = 0;
            
            for (int i = 0; i < n; i++) {
                grid[i] = fs.next();
                for (int j = 0; j < m; j++) {
                    if (grid[i].charAt(j) == 'B') {
                        qR[tail] = i;
                        qC[tail] = j;
                        tail++;
                        dist[i][j] = 0;
                    }
                }
            }
            
            int maxTime = 0;
            
            while (head < tail) {
                int r = qR[head];
                int c = qC[head];
                head++;
                
                if (grid[r].charAt(c) == 'S') {
                    maxTime = Math.max(maxTime, dist[r][c]);
                }
                
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr].charAt(nc) != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        qR[tail] = nr;
                        qC[tail] = nc;
                        tail++;
                    }
                }
            }
            sb.append(maxTime).append("\n");
        }
        System.out.print(sb);
    }
}