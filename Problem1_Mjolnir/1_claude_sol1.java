import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int T = nextInt(in);
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = nextInt(in);
            long R = nextInt(in);
            long scap = nextInt(in);
            long wcap = nextInt(in);

            long[] impact = new long[N];
            long[] cost = new long[N];
            long totalCost = 0;

            for (int i = 0; i < N; i++) {
                long s = nextInt(in);
                long w = nextInt(in);
                long c = nextInt(in);
                long im = s - w;
                if (im < 0)
                    im = 0;
                impact[i] = im;
                cost[i] = c;
                totalCost += c;
            }

            long capTotal = scap + wcap;
            if (R > capTotal) {
                sb.append(-1).append('\n');
                continue;
            }

            int allowed = (int) (capTotal - R);

            // dp[w] = maximum cost of avengers we can keep (not convince)
            // whose combined impact does not exceed w
            long[] dp = new long[allowed + 1];

            for (int i = 0; i < N; i++) {
                long wtL = impact[i];
                if (wtL > allowed)
                    continue;
                int wt = (int) wtL;
                long val = cost[i];
                for (int w = allowed; w >= wt; w--) {
                    long cand = dp[w - wt] + val;
                    if (cand > dp[w])
                        dp[w] = cand;
                }
            }

            long bestKept = dp[allowed];
            sb.append(totalCost - bestKept).append('\n');
        }

        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') {
            if (b == -1)
                return -1;
            b = in.read();
        }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return ret;
    }
}