import java.io.*;
import java.util.*;

public class d1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        
        int N = Integer.parseInt(first[0]);
        int D = Integer.parseInt(first[1]);
        int K = Integer.parseInt(first[2]);
        
        long[] H = new long[N];
        String[] hVals = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            H[i] = Long.parseLong(hVals[i]);
        }
        
        ArrayList<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            adj[u].add(v);
            adj[v].add(u);
        }
        
        // Build parent-child relationships
        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        ArrayList<Integer>[] children = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        parent[0] = 0;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    children[u].add(v);
                    q.add(v);
                }
            }
        }
        
        if (K > N) {
            System.out.println(-1);
            return;
        }
        
        // Check if possible at all
        long maxH = 0;
        for (long h : H) {
            maxH = Math.max(maxH, h);
        }
        long high = maxH + D * N + 5;
        
        // Binary search
        long low = 0;
        long ans = -1;
        
        // Check with max possible T first
        if (!canCool(high, N, D, K, H, children)) {
            System.out.println(-1);
            return;
        }
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (canCool(mid, N, D, K, H, children)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        System.out.println(ans);
    }
    
    static boolean canCool(long T, int N, int D, int K, long[] H, ArrayList<Integer>[] children) {
        int cooled = 0;
        
        // Stack for DFS: pairs of (node, temperature)
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(0, T));
        
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            int u = p.node;
            long temp = p.temp;
            
            if (temp >= H[u]) {
                cooled++;
                if (cooled >= K) return true;
                
                for (int v : children[u]) {
                    stack.push(new Pair(v, temp - D));
                }
            }
            // If not cooled, don't propagate
        }
        
        return cooled >= K;
    }
    
    static class Pair {
        int node;
        long temp;
        Pair(int node, long temp) {
            this.node = node;
            this.temp = temp;
        }
    }
}