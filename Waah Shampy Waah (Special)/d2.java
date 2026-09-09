import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        
        for (int t = 0; t < testCases; t++) {
            int N = scanner.nextInt();
            long M = scanner.nextLong();
            
            ArrayList<Long> result = solve(N, M);
            
            if (result == null) {
                System.out.println(-1);
            } else {
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < result.size(); i++) {
                    output.append(result.get(i));
                    if (i < result.size() - 1) {
                        output.append(" ");
                    }
                }
                System.out.println(output.toString());
            }
        }
        
        scanner.close();
    }
    
    private static ArrayList<Long> solve(int N, long M) {
        long lowerBound = M / 2 + 1;
        long availableCount = M - lowerBound + 1;
        
        if (availableCount < N) {
            return null;
        }
        
        ArrayList<Long> squad = new ArrayList<>();
        for (long i = 0; i < N; i++) {
            squad.add(lowerBound + i);
        }
        
        return squad;
    }
}