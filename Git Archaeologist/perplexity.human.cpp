#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n, q;
        cin >> n >> q;

        vector<long long> risk(n);
        for (auto &x : risk) cin >> x;

        vector<vector<int>> g(n);
        vector<int> parent(n, 0), depth(n, 0);

        for (int i = 0; i < n - 1; ++i) {
            int p, v;
            cin >> p >> v;
            parent[v] = p;
            g[p].push_back(v);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) ++LOG;

        vector<vector<int>> up(LOG, vector<int>(n));
        vector<vector<long long>> mx(LOG, vector<long long>(n));

        queue<int> qu;
        qu.push(0);
        up[0][0] = 0;
        mx[0][0] = risk[0];

        while (!qu.empty()) {
            int u = qu.front();
            qu.pop();

            for (int v : g[u]) {
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                mx[0][v] = max(risk[v], risk[u]);
                qu.push(v);
            }
        }

        for (int k = 1; k < LOG; ++k) {
            for (int v = 0; v < n; ++v) {
                int mid = up[k - 1][v];
                up[k][v] = up[k - 1][mid];
                mx[k][v] = max(mx[k - 1][v], mx[k - 1][mid]);
            }
        }

        while (q--) {
            int a, b;
            cin >> a >> b;

            long long best = max(risk[a], risk[b]);
            int x = a, y = b;

            if (depth[x] < depth[y]) swap(x, y);

            int diff = depth[x] - depth[y];
            for (int k = LOG - 1; k >= 0; --k) {
                if (diff & (1 << k)) {
                    best = max(best, mx[k][x]);
                    x = up[k][x];
                }
            }

            if (x != y) {
                for (int k = LOG - 1; k >= 0; --k) {
                    if (up[k][x] != up[k][y]) {
                        best = max(best, mx[k][x]);
                        best = max(best, mx[k][y]);
                        x = up[k][x];
                        y = up[k][y];
                    }
                }
                best = max(best, risk[x]);
                best = max(best, risk[y]);
                best = max(best, risk[up[0][x]]);
                x = up[0][x];
            }

            int lca = x;
            int dist = depth[a] + depth[b] - 2 * depth[lca];

            cout << lca << ' ' << dist << ' ' << best << '\n';
        }
    }
}
