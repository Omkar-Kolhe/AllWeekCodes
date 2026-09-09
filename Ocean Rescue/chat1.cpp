#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    const int INF = 1e9;

    while (T--) {
        int N, M;
        cin >> N >> M;

        vector<string> grid(N);
        queue<int> q;

        for (int r = 0; r < N; r++) {
            cin >> grid[r];

            for (int c = 0; c < M; c++) {
                if (grid[r][c] == 'B') {
                    q.push(r * M + c);
                }
            }
        }

        // Multi-source BFS:
        // all bases start at time 0.
        vector<int> dist(N * M, INF);

        // Mark every base as a starting point.
        queue<int> bfs;
        while (!q.empty()) {
            int id = q.front();
            q.pop();

            dist[id] = 0;
            bfs.push(id);
        }

        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};

        int lastShip = 0;

        while (!bfs.empty()) {
            int id = bfs.front();
            bfs.pop();

            int r = id / M;
            int c = id % M;

            if (grid[r][c] == 'S') {
                lastShip = max(lastShip, dist[id]);
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (grid[nr][nc] == '#')
                    continue;

                int nid = nr * M + nc;

                if (dist[nid] != INF)
                    continue;

                dist[nid] = dist[id] + 1;
                bfs.push(nid);
            }
        }

        cout << lastShip << '\n';
    }

    return 0;
}