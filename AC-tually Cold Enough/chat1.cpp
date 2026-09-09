#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    ll D;
    cin >> N >> D >> K;

    vector<ll> H(N);
    for (auto &x : H) cin >> x;

    vector<vector<int>> g(N);
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        g[u].push_back(v);
        g[v].push_back(u);
    }

    if (K > N) {
        cout << -1 << '\n';
        return 0;
    }

    // need[v] = minimum starting temperature required
    // to make the whole path root -> v passable.
    vector<ll> need(N);
    vector<int> parent(N, -1);
    vector<int> depth(N);

    queue<int> q;
    q.push(0);
    parent[0] = 0;

    need[0] = H[0];

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v : g[u]) {
            if (v == parent[u]) continue;

            parent[v] = u;
            depth[v] = depth[u] + 1;

            ll here = H[v] + 1LL * depth[v] * D;

            need[v] = max(need[u], here);

            q.push(v);
        }
    }

    sort(need.begin(), need.end());

    // K-th smallest required temperature.
    cout << need[K - 1] << '\n';

    return 0;
}