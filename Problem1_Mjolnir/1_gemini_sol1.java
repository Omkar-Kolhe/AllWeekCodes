
// 1_gemini_sol1.java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null)
            return;

        StringTokenizer st = new StringTokenizer(line);
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long R = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            long Scap = Long.parseLong(st.nextToken());
            long Wcap = Long.parseLong(st.nextToken());

            long maxWeight = Scap + Wcap - R;

            long totalCost = 0;
            long[] impacts = new long[N];
            long[] costs = new long[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                long S = Long.parseLong(st.nextToken());
                long W = Long.parseLong(st.nextToken());
                long C = Long.parseLong(st.nextToken());

                impacts[i] = Math.max(0, S - W);
                costs[i] = C;
                totalCost += C;
            }

            if (maxWeight < 0) {
                sb.append("-1\n");
                continue;
            }

            int W_max = (int) maxWeight;
            long[] dp = new long[W_max + 1];

            for (int i = 0; i < N; i++) {
                int weight = (int) impacts[i];
                long value = costs[i];

                for (int j = W_max; j >= weight; j--) {
                    if (dp[j - weight] + value > dp[j]) {
                        dp[j] = dp[j - weight] + value;
                    }
                }
            }

            sb.append((totalCost - dp[W_max])).append("\n");
        }
        System.out.print(sb.toString());
    }
}