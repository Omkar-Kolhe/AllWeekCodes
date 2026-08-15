import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] a = new long[n];

            long min = Long.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
                min = Math.min(min, a[i]);
            }

            int count = 0;
            boolean good = false;

            for (long x : a) {
                if (x == min) {
                    count++;
                }

                if (x % min != 0) {
                    good = true;
                }
            }

            if (count == 1 || good) {
                ans.append("YES\n");
            } else {
                ans.append("NO\n");
            }
        }

        System.out.print(ans);
    }
}