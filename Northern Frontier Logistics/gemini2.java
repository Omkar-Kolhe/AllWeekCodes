import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt()) return;
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int d = in.nextInt();
            int[] w = new int[n];
            int l = 0, r = 0;
            for (int i = 0; i < n; i++) {
                w[i] = in.nextInt();
                if (w[i] > l) l = w[i];
                r += w[i];
            }
            int ans = r;
            while (l <= r) {
                int mid = (l + r) / 2;
                int count = 1, cur = 0;
                for (int x : w) {
                    if (cur + x > mid) {
                        count++;
                        cur = 0;
                    }
                    cur += x;
                }
                if (count <= d) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            System.out.println(ans);
        }
    }
}