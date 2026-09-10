import java.io.*;
import java.util.*;

public class human_gemi {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] ans = new int[n];
            int[] stack = new int[n];
            int top = -1;
            
            for (int i = n - 1; i >= 0; i--) {
                while (top >= 0 && a[stack[top]] <= a[i]) {
                    top--;
                }
                if (top == -1) {
                    ans[i] = n - 1 - i;
                } else {
                    ans[i] = stack[top] - i;
                }
                stack[++top] = i;
            }
            
            for (int i = 0; i < n; i++) {
                out.append(ans[i]).append(i == n - 1 ? "" : " ");
            }
            out.append("\n");
        }
        System.out.print(out);
    }
}
