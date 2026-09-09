import java.util.*;

public class d2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] H = new int[N + 1];
            
            for (int i = 1; i <= N; i++) {
                H[i] = sc.nextInt();
            }
            
            boolean[] reachable = new boolean[N + 1];
            reachable[1] = true;
            
            for (int j = 3; j <= N; j++) {
                int h = H[j];
                for (int d = 1; d * d <= h; d++) {
                    if (h % d == 0) {
                        int dist1 = d;
                        int dist2 = h / d;
                        
                        if (dist1 >= 2 && j - dist1 >= 1 && reachable[j - dist1]) {
                            reachable[j] = true;
                            break;
                        }
                        if (dist2 >= 2 && dist2 != dist1 && j - dist2 >= 1 && reachable[j - dist2]) {
                            reachable[j] = true;
                            break;
                        }
                    }
                }
            }
            
            System.out.println(reachable[N] ? "YES" : "NO");
        }
        
        sc.close();
    }
}