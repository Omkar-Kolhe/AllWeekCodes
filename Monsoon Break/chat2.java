import java.util.*;

public class chat2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            String S = sc.next();
            int K = sc.nextInt();

            int left = 0, work = 0, ans = 0;

            for (int right = 0; right < N; right++) {
                if (S.charAt(right) == 'W') {
                    work++;
                }

                while (work > K) {
                    if (S.charAt(left) == 'W') {
                        work--;
                    }
                    left++;
                }

                ans = Math.max(ans, right - left + 1);
            }

            System.out.println(ans);
        }

        sc.close();
    }
}