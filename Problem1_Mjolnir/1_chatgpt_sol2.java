import java.io.*;
import java.util.*;

class Main {

    static int solve(int[] impact, int[] cost, int target) {
        int INF = Integer.MAX_VALUE / 4;

        int[] best = new int[target + 1];
        Arrays.fill(best, INF);
        best[0] = 0;

        for (int i = 0; i < impact.length; i++) {
            if (impact[i] == 0) {
                continue;
            }

            for (int amount = target; amount >= 0; amount--) {
                if (best[amount] == INF) {
                    continue;
                }

                int nextAmount = amount + impact[i];

                if (nextAmount > target) {
                    nextAmount = target;
                }

                int newCost = best[amount] + cost[i];

                if (newCost < best[nextAmount]) {
                    best[nextAmount] = newCost;
                }
            }
        }

        return best[target] == INF ? -1 : best[target];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            long resistance = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());

            long captainStrength = Long.parseLong(st.nextToken());

            long captainWorth = Long.parseLong(st.nextToken());

            long limit = captainStrength + captainWorth;

            int[] impact = new int[n];
            int[] cost = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int strength = Integer.parseInt(st.nextToken());
                int worth = Integer.parseInt(st.nextToken());

                cost[i] = Integer.parseInt(st.nextToken());
                impact[i] = Math.max(0, strength - worth);

                resistance += impact[i];
            }

            if (resistance <= limit) {
                output.append("0\n");
                continue;
            }

            int target = (int) (resistance - limit);

            output.append(solve(impact, cost, target))
                    .append('\n');
        }

        System.out.print(output);
    }
}