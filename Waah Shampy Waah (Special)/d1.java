import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            int N = sc.nextInt();
            long M = sc.nextLong();
            
            long start = M / 2 + 1;
            long count = M - start + 1;
            
            if (count < N) {
                System.out.println(-1);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < N; i++) {
                    sb.append(start + i);
                    if (i < N - 1) sb.append(" ");
                }
                System.out.println(sb);
            }
        }
    }
}