import java.io.*;

public class d1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            String[] first = br.readLine().split(" ");
            int N = Integer.parseInt(first[0]);
            int M = Integer.parseInt(first[1]);
            int K = Integer.parseInt(first[2]);
            
            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                grid[i] = br.readLine().toCharArray();
            }
            
            String S = br.readLine();
            
            int r = 0, c = 0; // 0-indexed
            
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
    }
}