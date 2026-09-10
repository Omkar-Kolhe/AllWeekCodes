import java.io.*;
import java.util.*;

public class gemini1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long d = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (k > n) {
            System.out.println("-1");
            return;
        }

        long[] h = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Long.parseLong(st.nextToken());
        }

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        long[] actualReq = new long[n];
        int[] depth = new int[n];
        boolean[] vis = new boolean[n];
        
        // Simulating queue with an array for max speed
        int[] q = new int[n];
        int head = 0, tail = 0;

        q[tail++] = 0;
        vis[0] = true;
        actualReq[0] = h[0];

        while (head < tail) {
            int u = q[head++];

            for (int v : adj[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    depth[v] = depth[u] + 1;
                    
                    long curReq = h[v] + (long) depth[v] * d;
                    actualReq[v] = Math.max(actualReq[u], curReq);
                    
                    q[tail++] = v;
                }
            }
        }

        Arrays.sort(actualReq);
        System.out.println(actualReq[k - 1]);
    }
}