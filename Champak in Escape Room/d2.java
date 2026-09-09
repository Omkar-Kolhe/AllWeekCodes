import java.util.*;

public class d2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            
            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                grid[i] = sc.next().toCharArray();
            }
            
            String S = sc.next();
            
            int r = 0, c = 0;
            
            for (int i = 0; i < K; i++) {
                char ch = S.charAt(i);
                int nr = r, nc = c;
                
                if (ch == 'U') nr--;
                else if (ch == 'D') nr++;
                else if (ch == 'L') nc--;
                else if (ch == 'R') nc++;
                
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == '.') {
                    r = nr;
                    c = nc;
                }
            }
            
            System.out.println((r + 1) + " " + (c + 1));
        }
        
        sc.close();
    }
}