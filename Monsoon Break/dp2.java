import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            String S = br.readLine().trim();
            int K = Integer.parseInt(br.readLine().trim());

            int left = 0, wCount = 0, best = 0;
            for (int right = 0; right < N; right++) {
                if (S.charAt(right) == 'W') {
                    wCount++;
                }
                while (wCount > K) {
                    if (S.charAt(left) == 'W') {
                        wCount--;
                    }
                    left++;
                }
                best = Math.max(best, right - left + 1);
            }
            sb.append(best).append('\n');
        }
        System.out.print(sb);
    }
}