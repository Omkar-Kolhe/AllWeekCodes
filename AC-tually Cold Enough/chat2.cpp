#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    long long D;
    cin >> N >> D >> K;

    vector<long long> H(N);
    for (auto &x : H) cin >> x;

    vector<vector<int>> adj(N);

    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<int> parent(N, -1);
    vector<int> depth(N);
    vector<long long> need(N);

    vector<int> order;
    order.reserve(N);
    order.push_back(0);
    parent[0] = -2;

    for (int i = 0; i < (int)order.size(); i++) {
        int u = order[i];

        for (int v : adj[u]) {
            if (v == parent[u]) continue;

            parent[v] = u;
            depth[v] = depth[u] + 1;
            order.push_back(v);
        }
    }

    need[0] = H[0];

    for (int i = 1; i < N; i++) {
        int u = order[i];

        long long current = H[u] + 1LL * depth[u] * D;
        need[u] = max(need[parent[u]], current);
    }

    sort(need.begin(), need.end());

    cout << need[K - 1] << '\n';

    return 0;
}