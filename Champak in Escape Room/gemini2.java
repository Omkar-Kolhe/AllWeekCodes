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
        
        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            int k = fs.nextInt();
            
            String[] grid = new String[n];
            for (int i = 0; i < n; i++) {
                grid[i] = fs.next();
            }
            
            String s = fs.next();
            int r = 0, c = 0;
            
            for (int i = 0; i < k; i++) {
                char move = s.charAt(i);
                int nr = r;
                int nc = c;
                
                if (move == 'U') nr--;
                else if (move == 'D') nr++;
                else if (move == 'L') nc--;
                else if (move == 'R') nc++;
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr].charAt(nc) == '.') {
                    r = nr;
                    c = nc;
                }
            }
            sb.append(r + 1).append(" ").append(c + 1).append("\n");
        }
        System.out.print(sb);
    }
}