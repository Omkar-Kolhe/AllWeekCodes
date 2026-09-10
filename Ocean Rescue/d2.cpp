#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};
    
    while (T--) {
        int N, M;
        cin >> N >> M;
        
        vector<string> grid(N);
        for (int i = 0; i < N; i++) {
            cin >> grid[i];
        }
        
        vector<vector<int>> dist(N, vector<int>(M, -1));
        queue<pair<int, int>> q;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'B') {
                    dist[i][j] = 0;
                    q.push({i, j});
                }
            }
        }
        
        while (!q.empty()) {
            auto [r, c] = q.front();
            q.pop();
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] != '#' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.push({nr, nc});
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'S') {
                    ans = max(ans, dist[i][j]);
                }
            }
        }
        
        cout << ans << '\n';
    }
    
    return 0;
}