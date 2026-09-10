#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N, D, K;
    cin >> N >> D >> K;
    
    vector<long long> H(N);
    for (int i = 0; i < N; i++) {
        cin >> H[i];
    }
    
    vector<vector<int>> adj(N);
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    vector<int> parent(N, -1);
    vector<int> order;
    order.reserve(N);
    
    stack<int> st;
    st.push(0);
    parent[0] = -2;
    
    while (!st.empty()) {
        int u = st.top();
        st.pop();
        order.push_back(u);
        
        for (int v : adj[u]) {
            if (parent[v] == -1) {
                parent[v] = u;
                st.push(v);
            }
        }
    }
    
    vector<int> depth(N, 0);
    for (int i = 1; i < N; i++) {
        int u = order[i];
        depth[u] = depth[parent[u]] + 1;
    }
    
    vector<long long> req(N);
    for (int i = 0; i < N; i++) {
        req[i] = H[i] + 1LL * depth[i] * D;
    }
    
    sort(req.begin(), req.end());
    
    long long ans = req[K - 1];
    cout << ans << '\n';
    
    return 0;
}