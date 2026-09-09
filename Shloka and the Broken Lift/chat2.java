import java.io.*;
import java.util.*;

public class chat2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] H = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            boolean[] dp = new boolean[N + 1];
            dp[1] = true;

            for (int j = 3; j <= N; j++) {
                for (int d = 1; d * d <= H[j]; d++) {
                    if (H[j] % d != 0) continue;

                    int d1 = d;
                    int d2 = H[j] / d;

                    if (d1 >= 2 && d1 < j && dp[j - d1]) {
                        dp[j] = true;
                    }

                    if (d2 != d1 && d2 >= 2 && d2 < j && dp[j - d2]) {
                        dp[j] = true;
                    }

                    if (dp[j]) break;
                }
            }

            out.append(dp[N] ? "YES\n" : "NO\n");
        }

        System.out.print(out);
    }
}