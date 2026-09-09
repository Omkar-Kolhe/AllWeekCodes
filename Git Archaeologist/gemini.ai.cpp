#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, Q;
        cin >> N >> Q;

        vector<long long> risk(N);
        for (int i = 0; i < N; ++i) cin >> risk[i];

        vector<vector<int>> children(N);
        for (int i = 0; i < N - 1; ++i) {
            int p, v;
            cin >> p >> v;
            children[p].push_back(v);
        }

        int LOG = 1;
        while ((1LL << LOG) <= N) ++LOG;

        vector<int> depth(N);
        vector<vector<int>> ancestor(LOG, vector<int>(N));
        vector<vector<long long>> pathMax(LOG, vector<long long>(N));

        ancestor[0][0] = 0;
        pathMax[0][0] = risk[0];

        queue<int> bfs;
        bfs.push(0);

        while (!bfs.empty()) {
            int u = bfs.front();
            bfs.pop();

            for (int v : children[u]) {
                depth[v] = depth[u] + 1;
                ancestor[0][v] = u;
                pathMax[0][v] = max(risk[v], risk[u]);
                bfs.push(v);
            }
        }

        for (int j = 1; j < LOG; ++j) {
            for (int v = 0; v < N; ++v) {
                int mid = ancestor[j - 1][v];
                ancestor[j][v] = ancestor[j - 1][mid];
                pathMax[j][v] = max(pathMax[j - 1][v], pathMax[j - 1][mid]);
            }
        }

        auto query = [&](int a, int b) {
            long long answerMax = max(risk[a], risk[b]);
            int u = a, v = b;

            if (depth[u] < depth[v]) swap(u, v);

            int gap = depth[u] - depth[v];

            for (int j = LOG - 1; j >= 0; --j) {
                if ((gap >> j) & 1) {
                    answerMax = max(answerMax, pathMax[j][u]);
                    u = ancestor[j][u];
                }
            }

            if (u == v)
                return tuple<int, int, long long>{u, depth[a] + depth[b] - 2 * depth[u], answerMax};

            for (int j = LOG - 1; j >= 0; --j) {
                if (ancestor[j][u] != ancestor[j][v]) {
                    answerMax = max(answerMax, pathMax[j][u]);
                    answerMax = max(answerMax, pathMax[j][v]);
                    u = ancestor[j][u];
                    v = ancestor[j][v];
                }
            }

            answerMax = max(answerMax, risk[u]);
            answerMax = max(answerMax, risk[v]);
            answerMax = max(answerMax, risk[ancestor[0][u]]);

            int lca = ancestor[0][u];
            int dist = depth[a] + depth[b] - 2 * depth[lca];
            return tuple<int, int, long long>{lca, dist, answerMax};
        };

        while (Q--) {
            int a, b;
            cin >> a >> b;
            auto [lca, dist, mx] = query(a, b);
            cout << lca << ' ' << dist << ' ' << mx << '\n';
        }
    }
}
