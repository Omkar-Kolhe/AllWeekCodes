import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            long[][] arr = new long[n][2];
            for (int i = 0; i < n; i++) {
                StringTokenizer tok = new StringTokenizer(br.readLine());
                long p = Long.parseLong(tok.nextToken());
                long d = Long.parseLong(tok.nextToken());
                arr[i][0] = d;
                arr[i][1] = p;
            }

            Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (long[] job : arr) {
                long d = job[0], p = job[1];
                if (!pq.isEmpty() && pq.peek() + p <= d) {
                    long x = pq.poll();
                    pq.offer(x + p);
                } else {
                    pq.offer(p);
                }
            }

            sb.append(pq.size()).append('\n');
        }

        System.out.print(sb);
    }
}
