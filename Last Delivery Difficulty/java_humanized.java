import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder ans = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());

            long[][] jobs = new long[n][2];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                long p = Long.parseLong(st.nextToken());
                long d = Long.parseLong(st.nextToken());

                jobs[i][0] = p;
                jobs[i][1] = d;
            }

            Arrays.sort(jobs, (a, b) -> Long.compare(a[1], b[1]));

            PriorityQueue<Long> longest =
                    new PriorityQueue<>(Collections.reverseOrder());

            long time = 0;

            for (long[] job : jobs) {
                time += job[0];
                longest.add(job[0]);

                if (time > job[1]) {
                    time -= longest.poll();
                }
            }

            ans.append(longest.size()).append('\n');
        }

        System.out.print(ans);
    }
}
