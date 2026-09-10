import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static class Task implements Comparable<Task> {
        long p, d;
        public Task(long p, long d) {
            this.p = p;
            this.d = d;
        }
        public int compareTo(Task o) {
            if (this.d != o.d) {
                return Long.compare(this.d, o.d);
            }
            return Long.compare(o.p, this.p);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);
        if (!st.hasMoreTokens()) return;
        int t = Integer.parseInt(st.nextToken());
        
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            Task[] tasks = new Task[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                long p = Long.parseLong(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                tasks[i] = new Task(p, d);
            }
            Arrays.sort(tasks);
            
            int l = 1, r = n, ans = n;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (check(mid, tasks)) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    
    static boolean check(int k, Task[] tasks) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(0L);
        }
        for (Task task : tasks) {
            long time = pq.poll();
            if (time + task.p > task.d) {
                return false;
            }
            pq.add(time + task.p);
        }
        return true;
    }
}
