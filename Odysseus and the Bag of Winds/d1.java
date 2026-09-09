import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 1000000007;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            int M = sc.nextInt();
            int A = sc.nextInt();
            
            int[][] C = new int[M + 1][M + 1];
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= M; j++) {
                    C[i][j] = sc.nextInt();
                }
            }
            
            int fullMask = (1 << M) - 1;
            int[][] dp = new int[fullMask + 1][M + 1];
            int[][] dpBag = new int[fullMask + 1][M + 1];
            
            for (int mask = 0; mask <= fullMask; mask++) {
                Arrays.fill(dp[mask], INF);
                Arrays.fill(dpBag[mask], INF);
            }
            
            dp[1][1] = 0;
            
            for (int mask = 1; mask <= fullMask; mask++) {
                for (int last = 1; last <= M; last++) {
                    if ((mask & (1 << (last - 1))) == 0) continue;
                    
                    if (dp[mask][last] < INF) {
                        for (int next = 1; next <= M; next++) {
                            if ((mask & (1 << (next - 1))) != 0) continue;
                            
                            int newMask = mask | (1 << (next - 1));
                            
                            if (last == A) {
                                int newCostBag = dp[mask][last] + C[last][next] / 2;
                                dpBag[newMask][next] = Math.min(dpBag[newMask][next], newCostBag);
                            }
                            
                            int newCost = dp[mask][last] + C[last][next];
                            dp[newMask][next] = Math.min(dp[newMask][next], newCost);
                        }
                    }
                    
                    if (dpBag[mask][last] < INF) {
                        for (int next = 1; next <= M; next++) {
                            if ((mask & (1 << (next - 1))) != 0) continue;
                            
                            int newMask = mask | (1 << (next - 1));
                            int newCost = dpBag[mask][last] + C[last][next];
                            dpBag[newMask][next] = Math.min(dpBag[newMask][next], newCost);
                        }
                    }
                }
            }
            
            System.out.println(dpBag[fullMask][M]);
        }
    }
}