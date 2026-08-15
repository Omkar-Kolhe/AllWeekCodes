import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int T = (int) st.nval;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st.nextToken();
            int N = (int) st.nval;
            st.nextToken();
            long R = (long) st.nval;
            st.nextToken();
            long sCap = (long) st.nval;
            st.nextToken();
            long wCap = (long) st.nval;

            int[] impact = new int[N];
            int[] cost = new int[N];

            for (int i = 0; i < N; i++) {
                st.nextToken();
                long s = (long) st.nval;
                st.nextToken();
                long w = (long) st.nval;
                st.nextToken();
                int c = (int) st.nval;

                long im = s - w;
                if (im < 0)
                    im = 0;
                impact[i] = (int) im;
                cost[i] = c;
            }

            long capTotal = sCap + wCap;
            if (R > capTotal) {
                sb.append(-1).append('\n');
                continue;
            }

            int allowed = (int) (capTotal - R);
            final long INF = Long.MAX_VALUE / 4;

            // dp[w] = minimum convincing cost so that avengers left
            // un-convinced have combined impact <= w
            long[] dp = new long[allowed + 1];

            for (int i = 0; i < N; i++) {
                int im = impact[i];
                int c = cost[i];
                for (int w = allowed; w >= 0; w--) {
                    long convinceOpt = dp[w] + c;
                    long keepOpt = (w >= im) ? dp[w - im] : INF;
                    dp[w] = Math.min(convinceOpt, keepOpt);
                }
            }

            sb.append(dp[allowed]).append('\n');
        }

        System.out.print(sb);
    }
}