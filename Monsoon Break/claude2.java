import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();
            int k = Integer.parseInt(br.readLine().trim());

            int left = 0, wCount = 0, best = 0;
            for (int right = 0; right < n; right++) {
                if (s.charAt(right) == 'W') wCount++;
                while (wCount > k) {
                    if (s.charAt(left) == 'W') wCount--;
                    left++;
                }
                best = Math.max(best, right - left + 1);
            }
            sb.append(best).append('\n');
        }
        System.out.print(sb);
    }
}