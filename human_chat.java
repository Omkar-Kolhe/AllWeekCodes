import java.io.*;
import java.util.*;

public class human_chat {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder outputBuilder = new StringBuilder();
        
        String firstLine = reader.readLine();
        if (firstLine == null) return;
        
        StringTokenizer tokenizer = new StringTokenizer(firstLine);
        int totalTestCases = Integer.parseInt(tokenizer.nextToken());
        
        while (totalTestCases-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int numTowers = Integer.parseInt(tokenizer.nextToken());
            
            int[] signalStrengths = new int[numTowers];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < numTowers; i++) {
                signalStrengths[i] = Integer.parseInt(tokenizer.nextToken());
            }
            
            int[] visibleCounts = new int[numTowers];
            int[] strongerTowerStack = new int[numTowers];
            int stackTopIndex = -1;
            
            for (int currentTower = numTowers - 1; currentTower >= 0; currentTower--) {
                while (stackTopIndex >= 0 && signalStrengths[strongerTowerStack[stackTopIndex]] <= signalStrengths[currentTower]) {
                    stackTopIndex--;
                }
                
                if (stackTopIndex == -1) {
                    visibleCounts[currentTower] = numTowers - 1 - currentTower;
                } else {
                    visibleCounts[currentTower] = strongerTowerStack[stackTopIndex] - currentTower;
                }
                
                stackTopIndex++;
                strongerTowerStack[stackTopIndex] = currentTower;
            }
            
            for (int i = 0; i < numTowers; i++) {
                outputBuilder.append(visibleCounts[i]).append(i == numTowers - 1 ? "" : " ");
            }
            outputBuilder.append("\n");
        }
        
        System.out.print(outputBuilder);
    }
}