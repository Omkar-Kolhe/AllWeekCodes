import java.io.*;
import java.util.*;

public class gemini1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        StringBuilder out = new StringBuilder();
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            int[] h = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }
            
            boolean[] dp = new boolean[n + 1];
            dp[1] = true;
            
            for (int j = 2; j <= n; j++) {
                for (int d = 1; d * d <= h[j]; d++) {
                    if (h[j] % d == 0) {
                        if (d >= 2 && j - d >= 1 && dp[j - d]) {
                            dp[j] = true;
                            break;
                        }
                        
                        int paired_d = h[j] / d;
                        if (paired_d >= 2 && j - paired_d >= 1 && dp[j - paired_d]) {
                            dp[j] = true;
                            break;
                        }
                    }
                }
            }
            
            if (dp[n]) out.append("YES\n");
            else out.append("NO\n");
        }
        System.out.print(out);
    }
}