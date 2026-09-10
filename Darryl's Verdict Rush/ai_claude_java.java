import java.util.*;
import java.io.*;

public class Solution {
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
            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                long d = jobs[i][0];
                long p = jobs[i][1];
                if (!minHeap.isEmpty() && minHeap.peek() + p <= d) {
                    long top = minHeap.poll();
                    minHeap.add(top + p);
                } else {
                    minHeap.add(p);
                }
            }
            sb.append(minHeap.size()).append("\n");
        }
        System.out.print(sb);
    }
}
