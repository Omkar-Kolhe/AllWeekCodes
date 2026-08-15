import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt(), D = sc.nextInt();
            int[] W = new int[N];
            int lo = 0, hi = 0;
            for (int i = 0; i < N; i++) {
                W[i] = sc.nextInt();
                lo = Math.max(lo, W[i]);
                hi += W[i];
            }
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int cnt = 1, cur = 0;
                for (int w : W) {
                    if (cur + w > mid) { cnt++; cur = 0; }
                    cur += w;
                }
                if (cnt <= D) hi = mid;
                else lo = mid + 1;
            }
            System.out.println(lo);
        }
        sc.close();
    }
}