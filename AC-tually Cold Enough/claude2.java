import java.util.*;
import java.io.*;

public class claude2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long D = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] H = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) H[i] = Long.parseLong(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        if (K > N) {
            System.out.println(-1);
            return;
        }

        long[] V = new long[N];
        int[] depth = new int[N];
        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[N];

        int[] order = new int[N];
        int orderSize = 0;
        int[] stack = new int[N];
        int sp = 0;
        stack[sp++] = 0;
        visited[0] = true;

        while (sp > 0) {
            int u = stack[--sp];
            order[orderSize++] = u;
            for (int w : adj.get(u)) {
                if (!visited[w]) {
                    visited[w] = true;
                    parent[w] = u;
                    depth[w] = depth[u] + 1;
                    stack[sp++] = w;
                }
            }
        }

        V[0] = H[0];
        for (int idx = 1; idx < orderSize; idx++) {
            int u = order[idx];
            long val = H[u] + (long) depth[u] * D;
            V[u] = Math.max(V[parent[u]], val);
        }

        Arrays.sort(V);
        System.out.println(V[K - 1]);
    }
}