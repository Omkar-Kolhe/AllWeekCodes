import java.util.*;
import java.io.*;

public class claude2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] H = new int[N + 1];
            for (int i = 1; i <= N; i++) H[i] = Integer.parseInt(st.nextToken());

            boolean[] reachable = new boolean[N + 1];
            reachable[1] = true;

            for (int j = 2; j <= N; j++) {
                int h = H[j];
                for (int d = 1; (long) d * d <= h; d++) {
                    if (h % d != 0) continue;
                    int d1 = d, d2 = h / d;
                    if (d1 >= 2 && j - d1 >= 1 && reachable[j - d1]) {
                        reachable[j] = true;
                        break;
                    }
                    if (d2 >= 2 && d2 != d1 && j - d2 >= 1 && reachable[j - d2]) {
                        reachable[j] = true;
                        break;
                    }
                }
            }

            sb.append(reachable[N] ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }
}