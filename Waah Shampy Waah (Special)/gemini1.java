import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        while (t-- > 0) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            long maxPossible = (m + 1) / 2;
            if (n > maxPossible) {
                pw.println("-1");
            } else {
                long start = m - n + 1;
                for (int i = 0; i < n; i++) {
                    pw.print(start + i);
                    if (i < n - 1) {
                        pw.print(" ");
                    }
                }
                pw.println();
            }
        }
        pw.flush();
    }
}