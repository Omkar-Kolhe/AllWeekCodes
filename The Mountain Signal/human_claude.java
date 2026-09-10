import java.util.*;
import java.io.*;

public class human_claude {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int t = (int) st.nval;

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st.nextToken();
            int n = (int) st.nval;

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                st.nextToken();
                a[i] = (int) st.nval;
            }

            int[] nextGreater = new int[n];
            Arrays.fill(nextGreater, n);

            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                    nextGreater[stack.pop()] = i;
                }
                stack.push(i);
            }

            for (int i = 0; i < n; i++) {
                int ans;
                if (nextGreater[i] != n) {
                    ans = nextGreater[i] - i;
                } else {
                    ans = n - 1 - i;
                }
                sb.append(ans);
                if (i != n - 1) sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
