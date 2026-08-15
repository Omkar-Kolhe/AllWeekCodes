import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] prefix = new long[N + 1];

            for (int i = 1; i <= N; i++) {
                String[] parts = br.readLine().trim().split("\\s+");
                char C = parts[0].charAt(0);
                long X = Long.parseLong(parts[1]);

                if (C == 'L') {
                    prefix[i] = prefix[i - 1] + X;
                } else {
                    prefix[i] = prefix[i - 1] - X;
                }
            }

            HashMap<Long, Integer> firstPos = new HashMap<>();
            firstPos.put(0L, 0);

            int bestLen = 0;
            int bestStart = 1;

            for (int i = 1; i <= N; i++) {
                long curr = prefix[i];

                if (firstPos.containsKey(curr)) {
                    int start = firstPos.get(curr) + 1;
                    int len = i - firstPos.get(curr);

                    if (len > bestLen || (len == bestLen && start < bestStart)) {
                        bestLen = len;
                        bestStart = start;
                    }
                } else {
                    firstPos.put(curr, i);
                }
            }

            if (bestLen == 0) {
                pw.println("0 0 0");
            } else {
                pw.println(bestLen + " " + bestStart + " " + (bestStart + bestLen - 1));
            }
        }

        pw.flush();
        pw.close();
        br.close();
    }
}