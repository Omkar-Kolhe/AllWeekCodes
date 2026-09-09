import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder output = new StringBuilder();
        
        tokenizer.nextToken();
        int T = (int) tokenizer.nval;
        
        for (int testCase = 0; testCase < T; testCase++) {
            tokenizer.nextToken();
            int N = (int) tokenizer.nval;
            tokenizer.nextToken();
            long D = (long) tokenizer.nval;
            tokenizer.nextToken();
            long F = (long) tokenizer.nval;
            
            long[][] depots = new long[N][2];
            for (int i = 0; i < N; i++) {
                tokenizer.nextToken();
                depots[i][0] = (long) tokenizer.nval;
                tokenizer.nextToken();
                depots[i][1] = (long) tokenizer.nval;
            }
            
            Arrays.sort(depots, (a, b) -> Long.compare(a[0], b[0]));
            
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            long currentFuel = F;
            long currentPosition = 0L;
            int stopCount = 0;
            boolean possible = true;
            
            for (int i = 0; i <= N; i++) {
                long targetPosition = (i == N) ? D : depots[i][0];
                long distanceNeeded = targetPosition - currentPosition;
                
                while (currentFuel < distanceNeeded) {
                    if (maxHeap.isEmpty()) {
                        possible = false;
                        break;
                    }
                    currentFuel += maxHeap.poll();
                    stopCount++;
                }
                
                if (!possible) {
                    break;
                }
                
                currentFuel -= distanceNeeded;
                currentPosition = targetPosition;
                
                if (i < N) {
                    maxHeap.add(depots[i][1]);
                }
            }
            
            output.append(possible ? stopCount : -1).append("\n");
        }
        
        System.out.print(output);
    }
}