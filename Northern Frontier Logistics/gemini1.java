import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static boolean canShip(int[] weights, int d, long capacity) {
        int days = 1;
        long currentWeight = 0;
        for (int w : weights) {
            if (currentWeight + w > capacity) {
                days++;
                currentWeight = 0;
            }
            currentWeight += w;
        }
        return days <= d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[] weights = new int[n];
            st = new StringTokenizer(br.readLine());
            long low = 0, high = 0;
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
                low = Math.max(low, weights[i]);
                high += weights[i];
            }

            long ans = high;
            while (low <= high) {
                long mid = low + (high - low) / 2;
                if (canShip(weights, d, mid)) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}