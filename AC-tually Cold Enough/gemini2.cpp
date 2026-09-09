#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

void solve() {
    int n;
    long long d;
    int k;
    if (!(cin >> n >> d >> k)) return;
    
    vector<long long> h(n);
    for (int i = 0; i < n; ++i) {
        cin >> h[i];
    }
    
    vector<vector<int>> adj(n);
    for (int i = 0; i < n - 1; ++i) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    if (k > n) {
        cout << -1 << "\n";
        return;
    }
    
    vector<long long> req(n);
    req[0] = h[0];
    queue<pair<int, int>> q; 
    q.push({0, -1});
    vector<long long> depth(n, 0);
    
    while (!q.empty()) {
        auto [u, p] = q.front();
        q.pop();
        for (int v : adj[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                req[v] = max(req[u], h[v] + depth[v] * d);
                q.push({v, u});
            }
        }
    }
    
    sort(req.begin(), req.end());
    cout << req[k - 1] << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}