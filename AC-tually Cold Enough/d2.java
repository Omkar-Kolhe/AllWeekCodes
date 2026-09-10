import java.util.*;

public class d2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        long D = sc.nextLong();
        int K = sc.nextInt();
        
        long[] H = new long[N];
        for (int i = 0; i < N; i++) {
            H[i] = sc.nextLong();
        }
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        int[] depth = new int[N];
        
        List<Integer> order = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        st.push(0);
        parent[0] = -2;
        
        while (!st.isEmpty()) {
            int u = st.pop();
            order.add(u);
            
            for (int v : adj.get(u)) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    st.push(v);
                }
            }
        }
        
        for (int i = 1; i < N; i++) {
            int u = order.get(i);
            depth[u] = depth[parent[u]] + 1;
        }
        
        long[] req = new long[N];
        for (int i = 0; i < N; i++) {
            req[i] = H[i] + 1L * depth[i] * D;
        }
        
        Arrays.sort(req);
        
        System.out.println(req[K - 1]);
        
        sc.close();
    }
}