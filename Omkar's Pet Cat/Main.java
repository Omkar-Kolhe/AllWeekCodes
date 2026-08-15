import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            long[] A = new long[N];
            long mn = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextLong();
                mn = Math.min(mn, A[i]);
            }

            boolean hasNonDiv = false;
            int cntMin = 0;

            for (long x : A) {
                if (x % mn != 0) hasNonDiv = true;
                if (x == mn) cntMin++;
            }

            System.out.println(hasNonDiv || cntMin == 1 ? "YES" : "NO");
        }

        sc.close();
    }
}