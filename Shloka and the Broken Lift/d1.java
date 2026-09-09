import java.io.*;
import java.util.*;

public class d1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] H = new int[N + 1];
            
            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                H[i] = Integer.parseInt(input[i - 1]);
            }
            
            boolean[] reachable = new boolean[N + 1];
            reachable[1] = true;
            
            for (int i = 1; i <= N; i++) {
                if (!reachable[i]) continue;
                
                // Get divisors of H[i] - these are our possible jump lengths
                for (int d = 1; d * d <= H[i]; d++) {
                    if (H[i] % d == 0) {
                        if (d >= 2) {
                            int j = i + d;
                            if (j <= N) reachable[j] = true;
                        }
                        
                        int other = H[i] / d;
                        if (other >= 2 && other != d) {
                            int j = i + other;
                            if (j <= N) reachable[j] = true;
                        }
                    }
                }
            }
            
            System.out.println(reachable[N] ? "YES" : "NO");
        }
    }
}