import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder output = new StringBuilder();
        
        final long INFINITY = Long.MAX_VALUE / 2;
        
        tokenizer.nextToken();
        int T = (int) tokenizer.nval;
        
        for (int testCase = 0; testCase < T; testCase++) {
            tokenizer.nextToken();
            int M = (int) tokenizer.nval;
            tokenizer.nextToken();
            int A = (int) tokenizer.nval;
            int aeolusIndex = A - 1;
            
            int[][] cost = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    tokenizer.nextToken();
                    cost[i][j] = (int) tokenizer.nval;
                }
            }
            
            int fullMask = 1 << M;
            long[][][] dp = new long[fullMask][M][2];
            
            for (long[][] maskArr : dp) {
                for (long[] nodeArr : maskArr) {
                    Arrays.fill(nodeArr, INFINITY);
                }
            }
            
            dp[1][0][0] = 0L;
            
            for (int mask = 1; mask < fullMask; mask++) {
                if ((mask & 1) == 0) {
                    continue;
                }
                
                boolean aeolusVisited = (mask & (1 << aeolusIndex)) != 0;
                
                for (int lastNode = 0; lastNode < M; lastNode++) {
                    if ((mask & (1 << lastNode)) == 0) {
                        continue;
                    }
                    
                    for (int bagState = 0; bagState < 2; bagState++) {
                        long currentCost = dp[mask][lastNode][bagState];
                        if (currentCost >= INFINITY) {
                            continue;
                        }
                        
                        for (int nextNode = 0; nextNode < M; nextNode++) {
                            if ((mask & (1 << nextNode)) != 0) {
                                continue;
                            }
                            
                            int newMask = mask | (1 << nextNode);
                            
                            long normalCost = currentCost + cost[lastNode][nextNode];
                            if (normalCost < dp[newMask][nextNode][bagState]) {
                                dp[newMask][nextNode][bagState] = normalCost;
                            }
                            
                            if (bagState == 0 && aeolusVisited) {
                                long halvedCost = currentCost + cost[lastNode][nextNode] / 2;
                                if (halvedCost < dp[newMask][nextNode][1]) {
                                    dp[newMask][nextNode][1] = halvedCost;
                                }
                            }
                        }
                    }
                }
            }
            
            int finalMask = fullMask - 1;
            long answer = Math.min(dp[finalMask][M-1][0], dp[finalMask][M-1][1]);
            output.append(answer).append("\n");
        }
        
        System.out.print(output);
    }
}