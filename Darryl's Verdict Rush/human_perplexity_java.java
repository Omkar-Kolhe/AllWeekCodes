import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Use BufferedReader for fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        // Process each test case
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // Store jobs as [processing_time, deadline]
            long[][] jobs = new long[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                jobs[i][0] = Long.parseLong(st.nextToken());
                jobs[i][1] = Long.parseLong(st.nextToken());
            }

            // Sort by deadline (earliest first)
            Arrays.sort(jobs, (a, b) -> Long.compare(a[1], b[1]));

            // Min-heap to track when containers become free
            PriorityQueue<Long> containers = new PriorityQueue<>();

            // Greedy assignment
            for (long[] job : jobs) {
                long p = job[0];  // processing time
                long d = job[1];  // deadline

                // Latest time this job can start
                long latest_start = d - p;

                // Check if earliest finishing container is available in time
                if (!containers.isEmpty() && containers.peek() <= latest_start) {
                    // Reuse this container
                    containers.poll();
                    containers.add(latest_start + p);
                } else {
                    // Need a new container
                    containers.add(latest_start + p);
                }
            }

            sb.append(containers.size()).append("\n");
        }

        // Output all results
        System.out.print(sb);
    }
}
