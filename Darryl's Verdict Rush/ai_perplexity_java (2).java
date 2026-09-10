import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long[][] jobs = new long[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                jobs[i][0] = Long.parseLong(st.nextToken());
                jobs[i][1] = Long.parseLong(st.nextToken());
            }
            Arrays.sort(jobs, (a, b) -> Long.compare(a[1], b[1]));
            PriorityQueue<Long> containers = new PriorityQueue<>();
            for (long[] job : jobs) {
                long p = job[0];
                long d = job[1];
                long latest_start = d - p;
                if (!containers.isEmpty() && containers.peek() <= latest_start) {
                    containers.poll();
                    containers.add(latest_start + p);
                } else {
                    containers.add(latest_start + p);
                }
            }
            sb.append(containers.size()).append("\n");
        }
        System.out.print(sb);
    }
}
