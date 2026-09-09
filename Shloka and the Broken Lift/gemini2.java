import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class gemini2 {
    static final int MAXH = 100000;
    static ArrayList<Integer>[] divisors = new ArrayList[MAXH + 1];

    static {
        for (int i = 0; i <= MAXH; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= MAXH; i++) {
            for (int j = i; j <= MAXH; j += i) {
                divisors[j].add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] h = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            boolean[] dp = new boolean[n + 1];
            dp[1] = true;
            for (int j = 2; j <= n; j++) {
                for (int d : divisors[h[j]]) {
                    if (d >= 2 && d < j) {
                        if (dp[j - d]) {
                            dp[j] = true;
                            break;
                        }
                    } else if (d >= j) {
                        break;
                    }
                }
            }

            if (dp[n]) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }
}