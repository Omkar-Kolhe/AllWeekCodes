#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, M;
        cin >> N >> M;

        vector<string> grid(N);
        queue<pair<int, int>> q;
        vector<vector<int>> dist(N, vector<int>(M, -1));

        for (int i = 0; i < N; i++) {
            cin >> grid[i];

            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'B') {
                    q.push({i, j});
                    dist[i][j] = 0;
                }
            }
        }

        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};

        while (!q.empty()) {
            auto [r, c] = q.front();
            q.pop();

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (grid[nr][nc] == '#')
                    continue;

                if (dist[nr][nc] != -1)
                    continue;

                dist[nr][nc] = dist[r][c] + 1;
                q.push({nr, nc});
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'S') {
                    answer = max(answer, dist[i][j]);
                }
            }
        }

        cout << answer << '\n';
    }

    return 0;
}