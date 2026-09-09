import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        StringBuilder sb = new StringBuilder();
        
        in.nextToken();
        int t = (int) in.nval;
        
        long INF = Long.MAX_VALUE / 2;
        
        while(t-- > 0){
            in.nextToken();
            int m = (int) in.nval;
            in.nextToken();
            int a = (int) in.nval;
            int aIdx = a - 1;
            
            int[][] c = new int[m][m];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < m; j++){
                    in.nextToken();
                    c[i][j] = (int) in.nval;
                }
            }
            
            int full = 1 << m;
            long[][][] dp = new long[full][m][2];
            for(long[][] row : dp)
                for(long[] cell : row)
                    Arrays.fill(cell, INF);
            
            dp[1][0][0] = 0;
            
            for(int mask = 1; mask < full; mask++){
                if((mask & 1) == 0) continue;
                boolean aReached = (mask & (1 << aIdx)) != 0;
                
                for(int last = 0; last < m; last++){
                    if((mask & (1 << last)) == 0) continue;
                    
                    for(int bag = 0; bag < 2; bag++){
                        long cur = dp[mask][last][bag];
                        if(cur >= INF) continue;
                        
                        for(int j = 0; j < m; j++){
                            if((mask & (1 << j)) != 0) continue;
                            int nmask = mask | (1 << j);
                            
                            long nc = cur + c[last][j];
                            if(nc < dp[nmask][j][bag])
                                dp[nmask][j][bag] = nc;
                            
                            if(bag == 0 && aReached){
                                long hc = cur + c[last][j] / 2;
                                if(hc < dp[nmask][j][1])
                                    dp[nmask][j][1] = hc;
                            }
                        }
                    }
                }
            }
            
            int fullmask = full - 1;
            long ans = Math.min(dp[fullmask][m-1][0], dp[fullmask][m-1][1]);
            sb.append(ans).append("\n");
        }
        
        System.out.print(sb);
    }
}