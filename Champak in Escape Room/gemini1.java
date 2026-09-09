import java.io.*;
import java.util.*;

public class gemini1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        StringBuilder out = new StringBuilder();
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                grid[i] = br.readLine().toCharArray();
            }
            
            String s = br.readLine();
            
            int r = 0, c = 0;
            for (int i = 0; i < k; i++) {
                char move = s.charAt(i);
                int nr = r, nc = c;
                
                if (move == 'U') nr--;
                else if (move == 'D') nr++;
                else if (move == 'L') nc--;
                else if (move == 'R') nc++;
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#') {
                    r = nr;
                    c = nc;
                }
            }
            
            out.append((r + 1)).append(" ").append((c + 1)).append("\n");
        }
        System.out.print(out);
    }
}