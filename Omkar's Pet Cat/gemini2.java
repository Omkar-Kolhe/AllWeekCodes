import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt()) return;
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int mn = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (a[i] < mn) mn = a[i];
            }
            
            int c = 0;
            for (int x : a) {
                if (x == mn) c++;
            }
            
            if (c == 1) {
                System.out.println("YES");
            } else {
                boolean ok = false;
                for (int x : a) {
                    if (x % mn != 0) {
                        ok = true;
                        break;
                    }
                }
                System.out.println(ok ? "YES" : "NO");
            }
        }
    }
}