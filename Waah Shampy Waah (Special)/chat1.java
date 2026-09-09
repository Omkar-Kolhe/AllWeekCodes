import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            long start = m / 2 + 1;

            if (m - start + 1 < n) {
                out.append("-1\n");
                continue;
            }

            for (long i = 0; i < n; i++) {
                if (i > 0) out.append(' ');
                out.append(start + i);
            }
            out.append('\n');
        }

        System.out.print(out);
    }
}