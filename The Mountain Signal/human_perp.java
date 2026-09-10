import java.io.*;
import java.util.*;

public class human_perp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(parts[i]);
            }
            int[] result = solve(N, A);
            for (int i = 0; i < N; i++) {
                out.print(result[i] + (i == N - 1 ? "" : " "));
            }
            out.println();
        }
        out.flush();
    }

    static int[] solve(int N, int[] A) {
        int[] ans = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && stack.peek() <= A[i]) {
                stack.pop();
                count++;
            }
            if (!stack.isEmpty()) {
                count++;
            }
            ans[i] = count;
            stack.push(A[i]);
        }
        return ans;
    }
}