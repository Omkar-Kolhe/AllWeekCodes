import java.util.*;
import java.io.*;

public class humanized_java {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int t = (int) st.nval;

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st.nextToken();
            int n = (int) st.nval;

            long[][] jobs = new long[n][2];
            for (int i = 0; i < n; i++) {
                st.nextToken();
                long p = (long) st.nval;
                st.nextToken();
                long d = (long) st.nval;
                jobs[i][0] = d;
                jobs[i][1] = p;
            }

            Arrays.sort(jobs, (a, b) -> Long.compare(a[0], b[0]));

            PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
            long curTime = 0;

            for (int i = 0; i < n; i++) {
                long d = jobs[i][0];
                long p = jobs[i][1];

                pq.add(p);
                curTime += p;

                if (curTime > d) {
                    long biggest = pq.poll();
                    curTime -= biggest;
                }
            }

            sb.append(pq.size()).append("\n");
        }

        System.out.print(sb);
    }
}
