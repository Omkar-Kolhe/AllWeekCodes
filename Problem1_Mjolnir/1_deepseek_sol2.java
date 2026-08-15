import java.io.*;
import java.util.*;

class Main {
    static class Avenger {
        int impact;
        int cost;

        Avenger(int impact, int cost) {
            this.impact = impact;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 0; testCase < T; testCase++) {
            String line;
            while ((line = br.readLine()) != null && line.trim().isEmpty()) {
                // Skip empty lines
            }

            if (line == null)
                break;

            String[] parts = line.trim().split("\\s+");
            int N = Integer.parseInt(parts[0]);
            int R = Integer.parseInt(parts[1]);

            parts = br.readLine().trim().split("\\s+");
            int Scap = Integer.parseInt(parts[0]);
            int Wcap = Integer.parseInt(parts[1]);

            List<Avenger> avengers = new ArrayList<>();
            long totalImpact = 0;

            for (int i = 0; i < N; i++) {
                parts = br.readLine().trim().split("\\s+");
                int S = Integer.parseInt(parts[0]);
                int W = Integer.parseInt(parts[1]);
                int C = Integer.parseInt(parts[2]);
                int imp = Math.max(0, S - W);
                avengers.add(new Avenger(imp, C));
                totalImpact += imp;
            }

            long threshold = (long) R + totalImpact - (long) (Scap + Wcap);

            if (threshold <= 0) {
                pw.println(0);
                continue;
            }

            if (threshold > totalImpact) {
                pw.println(-1);
                continue;
            }

            // Use HashMap for sparse DP
            Map<Integer, Long> dp = new HashMap<>();
            dp.put(0, 0L);

            for (Avenger av : avengers) {
                if (av.impact == 0)
                    continue;
                Map<Integer, Long> additions = new HashMap<>();

                for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
                    int newCost = entry.getKey() + av.cost;
                    long newImpact = entry.getValue() + av.impact;
                    additions.put(newCost, additions.getOrDefault(newCost, 0L));
                    additions.put(newCost, Math.max(additions.get(newCost), newImpact));
                }

                for (Map.Entry<Integer, Long> entry : additions.entrySet()) {
                    int key = entry.getKey();
                    long val = entry.getValue();
                    dp.put(key, Math.max(dp.getOrDefault(key, 0L), val));
                }
            }

            int ans = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
                if (entry.getValue() >= threshold) {
                    ans = Math.min(ans, entry.getKey());
                }
            }

            pw.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }

        pw.flush();
        pw.close();
        br.close();
    }
}