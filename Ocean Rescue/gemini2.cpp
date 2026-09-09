#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

void solve() {
    int t;
    if (!(cin >> t)) return;
    while (t--) {
        int n, m;
        cin >> n >> m;
        vector<string> grid(n);
        queue<pair<int, int>> q;
        vector<vector<int>> dist(n, vector<int>(m, -1));
        
        for (int i = 0; i < n; ++i) {
            cin >> grid[i];
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 'B') {
                    q.push({i, j});
                    dist[i][j] = 0;
                }
            }
        }
        
        int max_time = 0;
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        
        while (!q.empty()) {
            auto [r, c] = q.front();
            q.pop();
            
            if (grid[r][c] == 'S') {
                max_time = max(max_time, dist[r][c]);
            }
            
            for (int i = 0; i < 4; ++i) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.push({nr, nc});
                }
            }
        }
        cout << max_time << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    solve();
    return 0;
}