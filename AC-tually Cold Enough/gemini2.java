import java.io.*;
import java.util.*;

public class gemini2 {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        String nStr = fs.next();
        if (nStr == null) return;
        int n = Integer.parseInt(nStr);
        long d = fs.nextLong();
        int k = fs.nextInt();
        
        long[] h = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = fs.nextLong();
        }
        
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        if (k > n) {
            System.out.println("-1");
            return;
        }
        
        long[] req = new long[n];
        req[0] = h[0];
        
        int[] queue = new int[n];
        int head = 0, tail = 0;
        queue[tail++] = 0;
        
        long[] depth = new long[n];
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        while (head < tail) {
            int u = queue[head++];
            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    depth[v] = depth[u] + 1;
                    req[v] = Math.max(req[u], h[v] + depth[v] * d);
                    queue[tail++] = v;
                }
            }
        }
        
        Arrays.sort(req);
        System.out.println(req[k - 1]);
    }
}