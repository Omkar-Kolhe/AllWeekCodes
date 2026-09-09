import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(reader);
        StringBuilder output = new StringBuilder();
        
        in.nextToken();
        int T = (int) in.nval;
        
        for (int tc = 0; tc < T; tc++) {
            in.nextToken();
            long N = (long) in.nval;
            in.nextToken();
            long M = (long) in.nval;
            
            long maxAntichainSize = (M + 1) / 2;
            
            if (N > maxAntichainSize) {
                output.append(-1).append('\n');
            } else {
                long start = M - N + 1;
                for (long i = start; i <= M; i++) {
                    output.append(i);
                    if (i != M) {
                        output.append(' ');
                    }
                }
                output.append('\n');
            }
        }
        
        System.out.print(output);
    }
}