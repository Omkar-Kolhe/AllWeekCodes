#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int main() {
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    long long d;
    if (!(cin >> n >> d >> k)) return 0;

    vector<long long> h(n);
    for (int i = 0; i < n; i++) {
        cin >> h[i];
    }

    vector<vector<int>> adj(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    if (k > n) {
        cout << -1 << "\n";
        return 0;
    }

    vector<long long> actual_req(n, 0);
    vector<int> depth(n, 0);
    vector<bool> vis(n, false);
    queue<int> q;

    // Initialize root
    q.push(0);
    vis[0] = true;
    actual_req[0] = h[0];

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v : adj[u]) {
            if (!vis[v]) {
                vis[v] = true;
                depth[v] = depth[u] + 1;
                
                // Calculate requirement for current node
                long long cur_req = h[v] + (long long)depth[v] * d;
                
                // Actual requirement ensures ancestors are also cooled
                actual_req[v] = max(actual_req[u], cur_req);
                
                q.push(v);
            }
        }
    }

    // Sort to easily pick the K-th smallest requirement
    sort(actual_req.begin(), actual_req.end());

    // 0-indexed, so the K-th room is at K-1
    cout << actual_req[k - 1] << "\n";

    return 0;
}