import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        st.nextToken();
        int T = (int) st.nval;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st.nextToken();
            int N = (int) st.nval;

            long[] prefix = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                st.nextToken();
                String dir = st.sval;
                st.nextToken();
                long x = (long) st.nval;
                prefix[i] = prefix[i - 1] + (dir.equals("L") ? x : -x);
            }

            Integer[] order = new Integer[N + 1];
            for (int i = 0; i <= N; i++)
                order[i] = i;

            final long[] pf = prefix;
            Arrays.sort(order, (a, b) -> {
                if (pf[a] != pf[b])
                    return Long.compare(pf[a], pf[b]);
                return Integer.compare(a, b);
            });

            long bestLen = 0;
            int bestS = 0, bestE = 0;

            int i = 0;
            while (i <= N) {
                int j = i;
                long val = pf[order[i]];
                while (j <= N && pf[order[j]] == val)
                    j++;

                int minIdx = order[i];
                int maxIdx = order[j - 1];
                long len = (long) maxIdx - minIdx;

                if (len > 0) {
                    if (len > bestLen || (len == bestLen && (minIdx + 1) < bestS)) {
                        bestLen = len;
                        bestS = minIdx + 1;
                        bestE = maxIdx;
                    }
                }
                i = j;
            }

            if (bestLen == 0) {
                sb.append("0 0 0\n");
            } else {
                sb.append(bestLen).append(' ').append(bestS).append(' ').append(bestE).append('\n');
            }
        }

        System.out.print(sb);
    }
}