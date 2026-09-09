import java.util.*;

public class chat2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            long d = sc.nextLong();

            if (d < b) {
                System.out.println(-1);
                continue;
            }

            long diagonal = d - b;
            long x = a + diagonal;

            if (x < c) {
                System.out.println(-1);
            } else {
                long left = x - c;
                System.out.println(diagonal + left);
            }
        }

        sc.close();
    }
}