import java.util.*;
import java.io.*;

public class claude1 {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1<<16));
        int n = nextInt(in);
        long d = nextLong(in);
        int k = nextInt(in);

        long[] h = new long[n];
        for(int i=0;i<n;i++) h[i] = nextLong(in);

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            int u = nextInt(in), v = nextInt(in);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        if(k > n){
            System.out.println(-1);
            return;
        }

        long[] M = new long[n];
        int[] depth = new int[n];
        boolean[] vis = new boolean[n];

        M[0] = h[0];
        vis[0] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);

        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj.get(u)){
                if(!vis[v]){
                    vis[v] = true;
                    depth[v] = depth[u]+1;
                    long val = h[v] + d*(long)depth[v];
                    M[v] = Math.max(M[u], val);
                    q.add(v);
                }
            }
        }

        Arrays.sort(M);
        System.out.println(M[k-1]);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0, b;
        boolean neg = false;
        do { b = in.read(); } while (b != '-' && (b < '0' || b > '9'));
        if(b=='-'){ neg = true; b = in.read(); }
        while(b >= '0' && b <= '9'){ ret = ret*10 + b - '0'; b = in.read(); }
        return neg ? -ret : ret;
    }

    private static long nextLong(DataInputStream in) throws IOException {
        long ret = 0; int b;
        boolean neg = false;
        do { b = in.read(); } while (b != '-' && (b < '0' || b > '9'));
        if(b=='-'){ neg = true; b = in.read(); }
        while(b >= '0' && b <= '9'){ ret = ret*10 + b - '0'; b = in.read(); }
        return neg ? -ret : ret;
    }
}