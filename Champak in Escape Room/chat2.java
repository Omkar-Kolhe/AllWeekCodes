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
            int K = Integer.parseInt(st.nextToken());

            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++)
                grid[i] = br.readLine().toCharArray();

            String S = br.readLine().trim();

            int r = 0, c = 0;

            for (char ch : S.toCharArray()) {
                int nr = r, nc = c;

                if (ch == 'U') nr--;
                else if (ch == 'D') nr++;
                else if (ch == 'L') nc--;
                else nc++;

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == '.') {
                    r = nr;
                    c = nc;
                }
            }

            out.append(r + 1).append(' ').append(c + 1).append('\n');
        }

        System.out.print(out);
    }
}