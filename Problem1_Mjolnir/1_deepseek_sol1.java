import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            String[] parts = br.readLine().trim().split("\\s+");
            int N = Integer.parseInt(parts[0]);
            int R = Integer.parseInt(parts[1]);

            parts = br.readLine().trim().split("\\s+");
            int Scap = Integer.parseInt(parts[0]);
            int Wcap = Integer.parseInt(parts[1]);

            int[] impact = new int[N];
            int[] cost = new int[N];
            long totalImpact = 0;
            int totalCost = 0;

            for (int i = 0; i < N; i++) {
                parts = br.readLine().trim().split("\\s+");
                int S = Integer.parseInt(parts[0]);
                int W = Integer.parseInt(parts[1]);
                int C = Integer.parseInt(parts[2]);
                impact[i] = Math.max(0, S - W);
                cost[i] = C;
                totalImpact += impact[i];
                totalCost += C;
            }

            long requiredRemove = (long) R + totalImpact - (long) (Scap + Wcap);

            if (requiredRemove <= 0) {
                pw.println(0);
                continue;
            }

            if (requiredRemove > totalImpact) {
                pw.println(-1);
                continue;
            }

            long[] dp = new long[totalCost + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;

            for (int i = 0; i < N; i++) {
                if (impact[i] == 0)
                    continue;
                for (int j = totalCost; j >= cost[i]; j--) {
                    if (dp[j - cost[i]] != -1) {
                        dp[j] = Math.max(dp[j], dp[j - cost[i]] + impact[i]);
                    }
                }
            }

            int ans = -1;
            for (int j = 0; j <= totalCost; j++) {
                if (dp[j] >= requiredRemove) {
                    ans = j;
                    break;
                }
            }

            pw.println(ans);
        }

        pw.flush();
        pw.close();
        br.close();
    }
}