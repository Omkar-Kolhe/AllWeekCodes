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
            int K = Integer.parseInt(st.nextToken());

            char[][] grid = new char[N+1][];
            for (int i = 1; i <= N; i++) {
                String row = br.readLine();
                grid[i] = (" " + row).toCharArray();
            }

            String S = br.readLine();

            int r = 1, c = 1;
            for (int i = 0; i < K; i++) {
                int nr = r, nc = c;
                char ch = S.charAt(i);
                if (ch == 'U') nr--;
                else if (ch == 'D') nr++;
                else if (ch == 'L') nc--;
                else if (ch == 'R') nc++;

                if (nr >= 1 && nr <= N && nc >= 1 && nc <= M && grid[nr][nc] == '.') {
                    r = nr;
                    c = nc;
                }
            }

            sb.append(r).append(" ").append(c).append("\n");
        }

        System.out.print(sb);
    }
}