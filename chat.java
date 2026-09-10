import java.util.*;

public class chat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] ans = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {

                while (!stack.isEmpty() && a[stack.peek()] <= a[i]) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    ans[i] = n - 1 - i;
                } else {
                    ans[i] = stack.peek() - i;
                }

                stack.push(i);
            }

            for (int i = 0; i < n; i++) {
                System.out.print(ans[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        sc.close();
    }
}
