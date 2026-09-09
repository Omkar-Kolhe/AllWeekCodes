import java.io.*;
import java.util.*;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        int t;
        try {
            t = in.nextInt();
        } catch (Exception e) {
            return;
        }
        StringBuilder out = new StringBuilder();
        while (t-- > 0) {
            int n = in.nextInt();
            long[][] a = new long[n][2];
            for (int i = 0; i < n; i++) {
                a[i][1] = in.nextLong();
                a[i][0] = in.nextLong();
            }
            Arrays.sort(a, Comparator.comparingLong(o -> o[0]));
            long c = 0;
            PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < n; i++) {
                c += a[i][1];
                pq.add(a[i][1]);
                if (c > a[i][0]) {
                    c -= pq.poll();
                }
            }
            out.append(pq.size()).append("\n");
        }
        System.out.print(out);
    }
}
