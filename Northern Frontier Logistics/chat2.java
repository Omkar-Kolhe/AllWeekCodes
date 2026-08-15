import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            long[] w = new long[n];

            st = new StringTokenizer(br.readLine());

            long low = 0;
            long high = 0;

            for (int i = 0; i < n; i++) {
                w[i] = Long.parseLong(st.nextToken());
                low = Math.max(low, w[i]);
                high += w[i];
            }

            while (low <= high) {
                long capacity = low + (high - low) / 2;

                int days = 1;
                long sum = 0;

                for (int i = 0; i < n; i++) {
                    if (sum + w[i] <= capacity) {
                        sum += w[i];
                    } else {
                        days++;
                        sum = w[i];
                    }
                }

                if (days <= d) {
                    high = capacity - 1;
                } else {
                    low = capacity + 1;
                }
            }

            ans.append(low).append('\n');
        }

        System.out.print(ans);
    }
}