import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int INF = 1000000007;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        
        for (int t = 0; t < testCases; t++) {
            int M = scanner.nextInt();
            int A = scanner.nextInt();
            
            int[][] cost = new int[M + 1][M + 1];
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= M; j++) {
                    cost[i][j] = scanner.nextInt();
                }
            }
            
            int fullMask = (1 << M) - 1;
            int[][] dpWithoutBag = new int[fullMask + 1][M + 1];
            int[][] dpWithBag = new int[fullMask + 1][M + 1];
            
            for (int mask = 0; mask <= fullMask; mask++) {
                Arrays.fill(dpWithoutBag[mask], INF);
                Arrays.fill(dpWithBag[mask], INF);
            }
            
            dpWithoutBag[1][1] = 0;
            
            for (int mask = 1; mask <= fullMask; mask++) {
                for (int current = 1; current <= M; current++) {
                    if (((mask >> (current - 1)) & 1) == 0) {
                        continue;
                    }
                    
                    if (dpWithoutBag[mask][current] < INF) {
                        for (int next = 1; next <= M; next++) {
                            if (((mask >> (next - 1)) & 1) == 1) {
                                continue;
                            }
                            
                            int newMask = mask | (1 << (next - 1));
                            
                            if (current == A) {
                                int costWithBag = dpWithoutBag[mask][current] + cost[current][next] / 2;
                                dpWithBag[newMask][next] = Math.min(dpWithBag[newMask][next], costWithBag);
                            }
                            
                            int costWithout = dpWithoutBag[mask][current] + cost[current][next];
                            dpWithoutBag[newMask][next] = Math.min(dpWithoutBag[newMask][next], costWithout);
                        }
                    }
                    
                    if (dpWithBag[mask][current] < INF) {
                        for (int next = 1; next <= M; next++) {
                            if (((mask >> (next - 1)) & 1) == 1) {
                                continue;
                            }
                            
                            int newMask = mask | (1 << (next - 1));
                            int newCost = dpWithBag[mask][current] + cost[current][next];
                            dpWithBag[newMask][next] = Math.min(dpWithBag[newMask][next], newCost);
                        }
                    }
                }
            }
            
            System.out.println(dpWithBag[fullMask][M]);
        }
        
        scanner.close();
    }
}